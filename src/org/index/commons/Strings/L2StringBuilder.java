package org.index.commons.Strings;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class L2StringBuilder
{
    private StringBuilder currentBuilder;

    protected final ReadWriteLock _lock = new ReentrantReadWriteLock();
    protected final Lock _writeLock = _lock.writeLock();

    @Deprecated(forRemoval = true)
    private L2StringBuilder()
    {
        currentBuilder = new StringBuilder();
    }

    @Deprecated(forRemoval = true)
    private L2StringBuilder(String input)
    {
        currentBuilder = new StringBuilder((input == null ? "" : input));
    }

    public L2StringBuilder replace(String oldChar, String newChar)
    {
        if (currentBuilder.toString().contains(oldChar))
        {
            _writeLock.lock();
            try
            {
                currentBuilder.replace(0, currentBuilder.length() - 1, currentBuilder.toString().replace(oldChar, newChar));
            }
            finally
            {
                _writeLock.unlock();
            }
        }
        return this;
    }

    public L2StringBuilder replaceFirst(String oldChar, String newChar)
    {
        if (currentBuilder.toString().contains(oldChar))
        {
            _writeLock.lock();
            try
            {
                currentBuilder.replace(0, currentBuilder.length() - 1, currentBuilder.toString().replaceFirst(oldChar, newChar));
            }
            finally
            {
                _writeLock.unlock();
            }
        }
        return this;
    }

    public L2StringBuilder replaceAll(String oldChar, String newChar)
    {
        if (currentBuilder.toString().contains(oldChar))
        {
            _writeLock.lock();
            try
            {
                currentBuilder.replace(0, currentBuilder.length() - 1, currentBuilder.toString().replaceAll(oldChar, newChar));
            }
            finally
            {
                _writeLock.unlock();
            }
        }
        return this;
    }
}
