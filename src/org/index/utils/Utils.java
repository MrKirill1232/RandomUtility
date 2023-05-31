package org.index.utils;

/**
 * @author Index
 */
public class Utils
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
