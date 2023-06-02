package org.index.crypt;

import org.index.utils.Utils;

/**
 * @author Index
 */
public class Base32
{
    private static final String BASE32_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    public static String encode(byte[] inputArray)
    {
        final StringBuilder enc = new StringBuilder();
        int countOfOffsetByte = 0;
        int currentOffsetByte = 0;
        for (byte input : inputArray)
        {
            currentOffsetByte = (currentOffsetByte << 8) | (input & 0xFF);
            countOfOffsetByte += 8;
            while (countOfOffsetByte >= 5)
            {
                int index = (currentOffsetByte >> (countOfOffsetByte - 5)) & 0x1F;
                enc.append(BASE32_ALPHABET.charAt(index));
                countOfOffsetByte -= 5;
            }
        }

        if (currentOffsetByte > 0)
        {
            int index = (currentOffsetByte << (5 - countOfOffsetByte)) & 0x1F;
            enc.append(BASE32_ALPHABET.charAt(index));
        }
        return enc.toString();
    }

    public static byte[] decode(String inputString)
    {
        byte[] data = new byte[inputString.length() * 5 / 8];
        int countOfOffsetByte = 0;
        int currentOffsetByte = 0;
        int dataIndex = 0;
        for (char c : inputString.toCharArray())
        {
            int index = BASE32_ALPHABET.indexOf(Character.toUpperCase(c));
            if (index >= 0)
            {
                currentOffsetByte = (currentOffsetByte << 5) | index;
                countOfOffsetByte += 5;

                if (countOfOffsetByte >= 8)
                {
                    data[dataIndex++] = (byte) ((currentOffsetByte >> (countOfOffsetByte - 8)) & 0xFF);
                    countOfOffsetByte -= 8;
                }
            }
        }
        return data;
    }

    public static int getByteLengthForBaseCrypt(int requiredLength)
    {
        return (int) Utils.roundToHigher((((double) requiredLength) * 5d) / 8d);
    }
}
