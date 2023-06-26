package org.index.crypt.dat;

import org.index.crypt.DesCrypter;

import java.nio.charset.StandardCharsets;

/**
 * @author Index
 */
public class Cryptor311
{
    public static String _key = "HIhiHIYoMan~";

    private final DesCrypter _decryptor;
    private final DesCrypter _encryptor;

    public Cryptor311()
    {
        _decryptor = new DesCrypter(_key.getBytes(StandardCharsets.UTF_8), true);
        _encryptor = new DesCrypter(_key.getBytes(StandardCharsets.UTF_8), false);
    }

    public byte[] decrypt(byte[] inputArray)
    {
        return _decryptor.updateSequence(inputArray);
    }

    public byte[] encrypt(byte[] inputArray)
    {
        return _encryptor.updateSequence(inputArray);
    }
}
