package org.index.network;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Index
 */
public class NetworkReader
{
    private final byte[] _byteArray;
    private final AtomicInteger _position = new AtomicInteger(0);

    public NetworkReader(byte[] inputArray)
    {
        _byteArray = new byte[inputArray.length];
        System.arraycopy(inputArray, 0, _byteArray, 0, _byteArray.length);
    }

    public NetworkReader(byte[] inputArray, int offset)
    {
        _byteArray = new byte[inputArray.length - offset];
        System.arraycopy(inputArray, offset, _byteArray, 0, _byteArray.length);
    }

    public int readByte()
    {
        return _byteArray[_position.getAndIncrement()] & 0xf;
    }

    public int readChar()
    {
        final ByteBuffer buf = ByteBuffer.allocate(Character.BYTES);
        buf.put(_byteArray[_position.getAndIncrement()]);
        buf.rewind();
        return buf.getChar();
    }

    public int readShort()
    {
        final ByteBuffer buf = ByteBuffer.allocate(Short.BYTES);
        buf.put(1, _byteArray[_position.getAndIncrement()]);
        buf.put(0, _byteArray[_position.getAndIncrement()]);
        buf.rewind();
        return buf.getShort();
    }

    public int readInt()
    {
        final ByteBuffer buf = ByteBuffer.allocate(Integer.BYTES);
        buf.put(3, _byteArray[_position.getAndIncrement()]);
        buf.put(2, _byteArray[_position.getAndIncrement()]);
        buf.put(1, _byteArray[_position.getAndIncrement()]);
        buf.put(0, _byteArray[_position.getAndIncrement()]);
        buf.rewind();
        return buf.getInt();
    }

    public long readLong()
    {
        final ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
        buf.put(7, _byteArray[_position.getAndIncrement()]);
        buf.put(6, _byteArray[_position.getAndIncrement()]);
        buf.put(5, _byteArray[_position.getAndIncrement()]);
        buf.put(4, _byteArray[_position.getAndIncrement()]);
        buf.put(3, _byteArray[_position.getAndIncrement()]);
        buf.put(2, _byteArray[_position.getAndIncrement()]);
        buf.put(1, _byteArray[_position.getAndIncrement()]);
        buf.put(0, _byteArray[_position.getAndIncrement()]);
        buf.rewind();
        return buf.getLong();
    }

    public float readFloat()
    {
        final ByteBuffer buf = ByteBuffer.allocate(Float.BYTES);
        buf.put(3, _byteArray[_position.getAndIncrement()]);
        buf.put(2, _byteArray[_position.getAndIncrement()]);
        buf.put(1, _byteArray[_position.getAndIncrement()]);
        buf.put(0, _byteArray[_position.getAndIncrement()]);
        buf.rewind();
        return buf.getFloat();
    }

    public double readDouble()
    {
        final ByteBuffer buf = ByteBuffer.allocate(Double.BYTES);
        buf.put(7, _byteArray[_position.getAndIncrement()]);
        buf.put(6, _byteArray[_position.getAndIncrement()]);
        buf.put(5, _byteArray[_position.getAndIncrement()]);
        buf.put(4, _byteArray[_position.getAndIncrement()]);
        buf.put(3, _byteArray[_position.getAndIncrement()]);
        buf.put(2, _byteArray[_position.getAndIncrement()]);
        buf.put(1, _byteArray[_position.getAndIncrement()]);
        buf.put(0, _byteArray[_position.getAndIncrement()]);
        buf.rewind();
        return buf.getDouble();
    }

    public byte[] readBytes(int length)
    {
        final byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
        {
            result[i] = _byteArray[_position.getAndIncrement()];
        }
        return result;
    }

    public void readBytes(byte[] inputBytes)
    {
        for (int i = 0; i < inputBytes.length; i++)
        {
            inputBytes[i] = _byteArray[_position.getAndIncrement()];
        }
    }

    public byte[] readBytesWithHeader(Class<?> headerClass)
    {
        int arraySize = 0;
        if (headerClass.equals(byte.class))
        {
            arraySize = readByte();
        }
        else if (headerClass.equals(char.class))
        {
            arraySize = readChar();
        }
        else if (headerClass.equals(short.class))
        {
            arraySize = readShort();
        }
        else if (headerClass.equals(int.class))
        {
            arraySize = readInt();
        }
        else
        {
            throw new IllegalArgumentException("No presented class for header size! Presented size is " + headerClass.getSimpleName() + ";");
        }
        // total byte array will include this "byte" to array
        return readBytes(arraySize - 1);
    }

    public int getAvailableBytes()
    {
        return _byteArray.length - _position.get();
    }

    public byte[] getUnusedBytes()
    {
        final byte[] unusedByte = new byte[getAvailableBytes()];
        System.arraycopy(_byteArray, _position.get(), unusedByte, 0, unusedByte.length);
        return unusedByte;
    }
}
