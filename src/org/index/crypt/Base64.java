package org.index.crypt;

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
}
