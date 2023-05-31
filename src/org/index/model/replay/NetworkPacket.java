package org.index.model.replay;

import java.io.ByteArrayOutputStream;
import java.util.HexFormat;

/**
 * @author Index
 */
public class NetworkPacket
{
    private final int _opCode01;
    private final int _opCode02;
    private final byte[] _packetData;

    public NetworkPacket(int opCode01, byte[] packetData)
    {
        _opCode01 = opCode01;
        _opCode02 = 0;
        _packetData = packetData;
    }

    public NetworkPacket(int opCode01, int opCode02, byte[] packetData)
    {
        _opCode01 = opCode01;
        _opCode02 = opCode02;
        _packetData = packetData;
    }

    public int getOp01()
    {
        return _opCode01;
    }

    public int getOp02()
    {
        return _opCode02;
    }

    public byte[] getPacketDataAsByteArray()
    {
        return _packetData;
    }

    public String getPacketDataAsHexString()
    {
        StringBuilder builder = new StringBuilder();
        for (byte currentByte : getPacketDataAsByteArray())
        {
            final String tempHex = Integer.toHexString(currentByte);
            final String writeHex = tempHex.length() > 2 ? tempHex.substring(tempHex.length() - 2) : tempHex;
            builder.append(writeHex.length() == 1 ? "0" + writeHex : writeHex).append(" ");
        }
        return builder.toString();
    }

    public static byte[] getRawPacketFromHexString(String hexString)
    {
        final ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        for (String byteValue : hexString.split("-"))
        {
            byteArray.write((byte) HexFormat.fromHexDigits(byteValue));
        }
        return byteArray.toByteArray();
    }
}
