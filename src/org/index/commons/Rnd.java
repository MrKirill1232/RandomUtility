package org.index.commons;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Rnd
{
    private final static SecureRandom _secureRandom = new SecureRandom(String.valueOf(getSeed()).getBytes());

    private static Random rnd()
    {
        return ThreadLocalRandom.current();
    }

    public static byte nextByte()
    {
        return (byte) get(Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    public static int nextInt()
    {
        return rnd().nextInt();
    }

    public static long nextLong()
    {
        return rnd().nextLong();
    }

    public static int get(int min, int max)
    {
        if (min == max)
        {
            return min;
        }
        return min + get(max - min + 1);
    }

    public static int get(int n)
    {
        return n < 1 ? 0 : rnd().nextInt(n);
    }

    public static int getSecureInt()
    {
        return _secureRandom.nextInt();
    }

    public static byte[] getSecureBytes(int length)
    {
        final byte[] output = new byte[Math.max(0, length)];
        _secureRandom.nextBytes(output);
        return output;
    }

    private static long getSeed()
    {
        return System.nanoTime() + System.currentTimeMillis() + Thread.currentThread().getId();
    }
}
