package org.index.model.replay;

/**
 * @author Index
 */
public class ReplayHolder
{
    private final String _replayName;
    private final int _cryptVer;
    private final int _replayVersion;
    private NetworkPacket[] _readPacket = new NetworkPacket[0];

    public ReplayHolder(String replayName, int cryptVer, int replayVersion)
    {
        _replayName = replayName;
        _cryptVer = cryptVer;
        _replayVersion = replayVersion;
    }

    public void addPacket(NetworkPacket packet)
    {
        // can be replaced by org.apache.commons.lang3.ArrayUtils
        // _readPacket = ArrayUtils.add(_readPacket, packet);
        NetworkPacket[] result = new NetworkPacket[_readPacket.length + 1];

        System.arraycopy(_readPacket, 0, result, 0, _readPacket.length);
        System.arraycopy(new NetworkPacket[] { packet }, 0, result, _readPacket.length, 1);

        _readPacket = result;
    }

    public NetworkPacket[] getAllReadablePackets()
    {
        return _readPacket;
    }

    public String getReplayName()
    {
        return _replayName;
        // return _replayName.contains("\\") ? _replayName.split("\\\\")[_replayName.split("\\\\").length - 1] : _replayName;
    }

}
