package org.index.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    public static long roundByNormal(double value)
    {
        return Math.round(value);
    }

    public static long roundToLowest(double value)
    {
        return Integer.parseInt(String.valueOf(value).split(",")[0]);
    }

    public static long roundToHigher(double value)
    {
        return (long) Math.ceil(value);
    }

    public static String joinStrings(String glueStr, Object array)
    {
        return joinStrings(glueStr, array, 0, -1);
    }

    public static String joinStrings(String glueStr, Object array, int startIdx)
    {
        return joinStrings(glueStr, array, startIdx, -1);
    }

    public static String joinStrings(String glueStr, Object array, int startIdx, int maxCount)
    {
        try
        {
            int length = Array.getLength(array);
            StringBuilder result = new StringBuilder();
            if (startIdx < 0)
            {
                startIdx += length;
                if (startIdx < 0)
                {
                    return EMPTY_STRING;
                }
            }
            while(startIdx < length && maxCount != 0)
            {
                if(!result.isEmpty() && glueStr != null && !glueStr.isEmpty())
                {
                    result.append(glueStr);
                }
                result.append(Array.get(array, startIdx++));
                maxCount--;
            }
            return result.toString();
        }
        catch (Exception ignored)
        {
        }
        return EMPTY_STRING;
    }

    public static <T> boolean removeValueFromMap(Map<?, T> inputMap, T lookingObject)
    {
        boolean isRemoved = false;
        for (Map.Entry<?, T> entry : Map.copyOf(inputMap).entrySet())
        {
            if (lookingObject.equals(entry.getValue()))
            {
                isRemoved = true;
                inputMap.remove(entry.getKey());
            }
        }
        return isRemoved;
    }

    public static <T> boolean removeListValueFromCollection(Collection<List<T>> inputCollection, T lookingObject)
    {
        boolean isRemoved = false;
        for (List<T> values : inputCollection)
        {
            for (T value : List.copyOf(values))
            {
                if (lookingObject.equals(value))
                {
                    isRemoved = true;
                    values.remove(value);
                }
            }
        }
        return isRemoved;
    }

}
