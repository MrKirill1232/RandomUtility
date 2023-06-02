package org.index.crypt;

import org.index.utils.Utils;

/**
 * @author Index
 */
public class Base64
{
    public static String encode(byte[] inputArray)
    {
        return java.util.Base64.getEncoder().encodeToString(inputArray);
    }

    public static byte[] decode(String inputString)
    {
        return java.util.Base64.getDecoder().decode(inputString);
    }

    public static int getByteLengthForBaseCrypt(int requiredLength)
    {
        return (int) Utils.roundToHigher((((double) requiredLength) * 5d) / 8d);
    }
}
