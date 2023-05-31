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

    public Cryptor311()
    {
        _decryptor = new DesCrypter(_key.getBytes(StandardCharsets.UTF_8), true);

    }

    public byte[] decrypt(byte[] inputArray)
    {
        return _decryptor.updateSequence(inputArray);
    }

    public byte[] encrypt(byte[] inputArray)
    {
        System.err.println("You cannot encode it without private key!");
        return new byte[0];
    }
}
