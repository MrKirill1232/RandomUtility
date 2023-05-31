package org.index.crypt.dat;

import org.index.crypt.XorCrypter;

/**
 * @author Index
 */
public class Cryptor120
{
    public static int _key = 230;

    private final XorCrypter _cryptor;

    public Cryptor120()
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
