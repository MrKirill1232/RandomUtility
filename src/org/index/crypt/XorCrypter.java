package org.index.crypt;

import java.io.ByteArrayOutputStream;

public class XorCrypter
{
    private final int _key;

    public XorCrypter(int xorKey)
    {
        _key = xorKey;
    }

    public byte[] updateSequence(final byte[] inputArray)
    {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(inputArray.length);
        for (byte inputByte : inputArray)
        {
            output.write(inputByte ^ _key);
        }
        return output.toByteArray();
    }
}
