package org.index.crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.ByteArrayOutputStream;

public class DesCrypter
{
    private final byte[] _key;
    private final boolean _decrypt;

    private Cipher _cipher;

    public DesCrypter(byte[] key, boolean decrypt)
    {
        _key = key;
        _decrypt = decrypt;
        try
        {
            _cipher = Cipher.getInstance("DES/ECB/NoPadding");
            final byte[] xorKey = initXORKey();
            final SecretKey desKey = generateDESKey(xorKey);
            if (_decrypt)
            {
                initDecryptor(desKey);
            }
            else
            {
                initCryptor(desKey);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            _cipher = null;
        }
    }

    private void initCryptor(SecretKey desKey) throws Exception
    {
        _cipher.init(Cipher.ENCRYPT_MODE, desKey);
    }

    private void initDecryptor(SecretKey desKey) throws Exception
    {
        _cipher.init(Cipher.DECRYPT_MODE, desKey);
    }

    public Cipher getCipher()
    {
        return _cipher;
    }

    public byte[] updateSequence(byte[] inputArray)
    {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(inputArray.length);
        try
        {
            if (_decrypt)
            {
                final byte[] decoded = new byte[8];
                int position = 0;
                while (position < inputArray.length)
                {
                    int size = Math.min(8, inputArray.length - position);
                    System.arraycopy(inputArray, position, decoded, 0, size);
                    output.write(size == 8 ? _cipher.doFinal(decoded) : decoded, 0, size);
                    position += size;
                }
            }
            else
            {
                System.err.println("You cannot encode it without private key!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    private byte[] initXORKey()
    {
        final byte[] keyAsByteSequence = _key;
        final byte[] xorKey = new byte[keyAsByteSequence.length];
        for (int index = 0; index < xorKey.length; index++)
        {
            xorKey[index % 8] ^= keyAsByteSequence[index];
        }
        return xorKey;
    }

    private SecretKey generateDESKey(byte[] xorKey) throws Exception
    {
        final DESKeySpec desGenerator = new DESKeySpec(xorKey);
        final SecretKeyFactory desAlgorithm = SecretKeyFactory.getInstance("DES");
        return desAlgorithm.generateSecret(desGenerator);
    }
}
