package org.index.crypt.dat;

import org.index.crypt.XorCrypter;

/**
 * @author Index
 */
public class Cryptor111
{
    public static int _key = 172;

    private final XorCrypter _cryptor;

    public Cryptor111()
    {
        _cryptor = new XorCrypter(_key);
    }

    public byte[] decrypt(byte[] inputArray)
    {
        return _cryptor.updateSequence(inputArray);
    }

    public byte[] encrypt(byte[] inputArray)
    {
        return _cryptor.updateSequence(inputArray);
    }
}
