package org.index.commons;

/**
 * @author Index
 * // you can use apache commons-lang instead of this
 */
public class CommonPair<K, V>
{
    private final K _key;
    private final V _value;

    public CommonPair(K key, V value)
    {
        _key = key;
        _value = value;
    }

    public K getKey()
    {
        return _key;
    }

    public V getValue()
    {
        return _value;
    }

    public boolean isNull()
    {
        return getKey() == null && getValue() == null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj.getClass() != this.getClass())
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        final CommonPair<?, ?> requestedPair = (CommonPair<?, ?>) obj;
        return requestedPair.getKey() == this.getKey() || requestedPair.getValue() == this.getValue();
    }
}
