package org.index.model;

import org.index.crypt.dat.Cryptor311;
import org.index.model.replay.NetworkPacket;
import org.index.model.replay.ReplayHolder;
import org.index.network.NetworkReader;
import org.index.utils.ParseUtils;
import org.index.utils.Utils;

import java.io.File;
import java.nio.file.Files;

/**
 * @author Index
 */
public class ReplayFile
{
    private final NetworkReader _replayContent;
    private ReplayHolder _replay;

    public ReplayFile(File inputFile)
    {
        final byte[] fileAsArray = readFileByte(inputFile);
        final int cryptVersion = getCryptVersion(fileAsArray);
        final byte[] decodedFile = decodeFile(fileAsArray, cryptVersion);
        _replayContent = new NetworkReader(decodedFile);

        readHeader(inputFile.getName(), cryptVersion);
        readPackets();
        readCameraMoving();
        readAdditionalResourcesData();

        final String bottom = new String(_replayContent.readBytesWithHeader(byte.class));   // Safe Package
    }

    public ReplayHolder getReplay()
    {
        return _replay;
    }

    private void readHeader(String fileName, int cryptVersion)
    {
        final String header = new String(_replayContent.readBytesWithHeader(byte.class));
        final int version = _replayContent.readInt();
        final int replayStartTime = _replayContent.readInt();
        final float x = _replayContent.readFloat();
        final float y = _replayContent.readFloat();
        final float z = _replayContent.readFloat();
        final float h = _replayContent.readFloat();
        final int unkInt01 = _replayContent.readInt();  // hour
        final int unkInt02 = _replayContent.readInt();  // min
        final int minutes = _replayContent.readByte();  // sec
        final int hours = _replayContent.readByte();    // in camera mode
        final int unkInt03 = _replayContent.readInt();  // is fp mode
        final long endReplayTime = _replayContent.readLong();
        final int unk11 = _replayContent.readInt();
        final int unk12 = _replayContent.readInt();
        final int unk13 = _replayContent.readInt();
        final int unk14 = _replayContent.readInt();
        final int unk15 = _replayContent.readInt();
        final int unk16 = _replayContent.readInt();

        _replay = new ReplayHolder(fileName, cryptVersion, version);
    }

    private void readPackets()
    {
        final int sizeOfRecordedPackets = _replayContent.readInt();
        for (int size = 0; size < sizeOfRecordedPackets; size++)
        {
            final long timeOfReceivePacket = _replayContent.readLong();
            final int opcode01 = _replayContent.readByte();
            final int opcode02 = _replayContent.readShort();
            final int packetSize = _replayContent.readInt();
            final byte[] packetData = _replayContent.readBytes(packetSize);
            final int packetUnk01 = _replayContent.readByte();
            _replay.addPacket(new NetworkPacket(opcode01, opcode02 == 65535 ? 0 : opcode02, packetData));
        }
    }

    private void readCameraMoving()
    {
        final int sizeOfCameraMoves = _replayContent.readInt();
        for (int i = 0; i < sizeOfCameraMoves; i++)
        {
            final long packetTime = _replayContent.readLong();
            final int yaw = _replayContent.readInt();
            final int pitch = _replayContent.readInt();
            final int distance = _replayContent.readInt();
        }
    }

    private void readAdditionalResourcesData()
    {
        final int size = _replayContent.readInt();
        for (int i = 0; i < size; i++)
        {
            final int unkBlockValue01 = _replayContent.readInt();
        }
    }

    private static byte[] readFileByte(File inputFile)
    {
        try
        {
            return Files.readAllBytes(inputFile.toPath());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] decodeFile(byte[] originalByteArray,int cryptVersion)
    {
        final byte[] replayContentByte;
        if (cryptVersion == 311)
        {
            final Cryptor311 cryptor = new Cryptor311();
            replayContentByte = cryptor.decrypt(originalByteArray);
            System.out.println(Utils.ANSI_YELLOW + ReplayFile.class.getSimpleName() + ": Replay file has " + cryptVersion + " crypt version;");
        }
        else if (cryptVersion == -1)
        {
            replayContentByte = originalByteArray;
            System.out.println(Utils.ANSI_YELLOW + ReplayFile.class.getSimpleName() + ": Replay file don't have crypt;");
        }
        else
        {
            replayContentByte = new byte[0];
            System.out.println(Utils.ANSI_YELLOW + ReplayFile.class.getSimpleName() + ": Replay file has unsupported " + cryptVersion + " crypt version;");
        }
        return replayContentByte;
    }

    private static int getCryptVersion(byte[] originalByteArray)
    {
        // LINEAGE311 with nulls
        final byte[] headerArray = new byte[28];
        System.arraycopy(originalByteArray, 0, headerArray, 0, headerArray.length);
        final String header = ParseUtils.normilizeString(new String(headerArray));
        int cryptVersion = -1;
        try
        {
            return Integer.parseInt(header.replaceAll("Lineage2Ver", ""));
        }
        catch (NumberFormatException e)
        {
            return cryptVersion;
        }
    }
}
