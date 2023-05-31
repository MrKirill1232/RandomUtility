package org.index.utils;

/**
 * @author Index
 */
public class Utils
{
    public static String EMPTY_STRING = new String("");

    public static String getValueOrEmpty(String inputValue)
    {
        return inputValue == null ? EMPTY_STRING : inputValue;
    }

    public static <T> T getValueOrNull(T[] inputValue, int requiredPosition)
    {
        return getValueOrDefault(inputValue, requiredPosition, null);
    }

    public static <T> T getValueOrDefault(T[] inputValue, int requiredPosition, T defaultValue)
    {
        try
        {
            return inputValue[requiredPosition];
        //  if (inputValue != null && requiredPosition > 0 && requiredPosition < inputValue.length)
        //  {
        //      return inputValue[requiredPosition];
        //  }
        //  return defaultValue;
        }
        catch (Exception ignored)
        {
            return defaultValue;
        }
    }
}
