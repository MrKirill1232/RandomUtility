package org.index.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

public class ArrayUtils
{
    public static boolean oContains(Object array, Object lookingObject)
    {
        if (array == null || lookingObject == null)
        {
            return false;
        }
        if (array.getClass().isArray() && array.getClass().componentType().isPrimitive())
        {
            try
            {
                switch (array.getClass().componentType().toGenericString().toLowerCase(Locale.ROOT))
                {
                    case "int":
                    {
                        return Arrays.binarySearch((int[]) array, (int) lookingObject) > 0;
                    }
                    case "char":
                    {
                        return Arrays.binarySearch((char[]) array, (char) lookingObject) > 0;
                    }
                    case "long":
                    {
                        return Arrays.binarySearch((long[]) array, (long) lookingObject) > 0;
                    }
                    case "float":
                    {
                        return Arrays.binarySearch((float[]) array, (float) lookingObject) > 0;
                    }
                    case "double":
                    {
                        return Arrays.binarySearch((double[]) array, (double) lookingObject) > 0;
                    }
                    case "object":
                    {
                        return Arrays.binarySearch((Object[]) array, (Object) lookingObject) > 0;
                    }
                }
            }
            catch (Exception ignored)
            {
            }
        }
        try
        {
            for (int index = 0; index < Array.getLength(array); index++)
            {
                final Object arrayObject = Array.get(array, index);
                if (arrayObject != null && arrayObject.equals(lookingObject))
                {
                    return true;
                }
            }
        }
        catch (Exception ignored)
        {
        }
        return false;
    }

    public static <T> boolean gContains(T[] array, T lookingObject)
    {
        if(array == null || lookingObject == null)
        {
            return false;
        }
        return Arrays.binarySearch(array, lookingObject) > 0;
//        for (T arrayObject : array)
//        {
//            if (arrayObject.equals(lookingObject))
//            {
//                return true;
//            }
//        }
//        return false;
    }

    public static String oReplace(Object array, String inputString, String replaceString, String leftAppend, String rightAppend, String replaceCounter)
    {
        try
        {
            int arrayLength = Array.getLength(array);
            if (array == null || arrayLength == 0 || inputString == null || replaceString == null || replaceString.isEmpty() || replaceString.isBlank())
            {
                return inputString == null ? Utils.EMPTY_STRING : inputString;
            }
            leftAppend = leftAppend == null ? Utils.EMPTY_STRING : leftAppend;
            rightAppend = rightAppend == null ? Utils.EMPTY_STRING : rightAppend;
            if (replaceCounter != null && replaceString.toLowerCase(Locale.ROOT).contains(replaceCounter.toLowerCase(Locale.ROOT)))
            {
                String string = inputString;
                for (int index = 1; index <= arrayLength; index++)
                {
                    final String arrayValue = String.valueOf(Array.get(array, (index - 1)));
                    string = string.replace(replaceString.replaceAll(replaceCounter, String.valueOf(index)), leftAppend + arrayValue + rightAppend);
                }
                return string;
            }
            else
            {
                final StringBuilder stringForReplace = new StringBuilder();
                for (int index = 1; index <= arrayLength; index++)
                {
                    final String arrayValue = String.valueOf(Array.get(array, (index - 1)));
                    stringForReplace.append(leftAppend).append(String.valueOf(arrayValue)).append(rightAppend);
                }
                return inputString.replaceAll(replaceString, stringForReplace.toString());
            }
        }
        catch (Exception ignored)
        {
        }
        return inputString == null ? Utils.EMPTY_STRING : inputString;
    }

    public static <T> String gReplace(T[] array, String inputString, String replaceString, String leftAppend, String rightAppend, String replaceCounter)
    {
        if (array == null || array.length == 0 || inputString == null || replaceString == null || replaceString.isEmpty() || replaceString.isBlank())
        {
            return inputString == null ? Utils.EMPTY_STRING : inputString;
        }
        leftAppend = leftAppend == null ? Utils.EMPTY_STRING : leftAppend;
        rightAppend = rightAppend == null ? Utils.EMPTY_STRING : rightAppend;
        if (replaceCounter != null && replaceString.toLowerCase(Locale.ROOT).contains(replaceCounter.toLowerCase(Locale.ROOT)))
        {
            String string = inputString;
            for (int index = 1; index <= array.length; index++)
            {
                final String arrayValue = String.valueOf(array[index - 1]);
                string = string.replace(replaceString.replaceAll(replaceCounter, String.valueOf(index)), leftAppend + arrayValue + rightAppend);
            }
            return string;
        }
        else
        {
            final StringBuilder stringForReplace = new StringBuilder();
            for (T arrayObject : array)
            {
                stringForReplace.append(leftAppend).append(String.valueOf(arrayObject)).append(rightAppend);
            }
            return inputString.replaceAll(replaceString, stringForReplace.toString());
        }
    }
}
