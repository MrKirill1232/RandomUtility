package org.index.commons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatSet
{
    public static final StatSet EMPTY_SET = new StatSet((String) null);

    private final Map<String, Object> _availableStatSet;

    private StatSet(String dummy)
    {
        _availableStatSet = Collections.unmodifiableMap(new HashMap<>());
    }

    public StatSet()
    {
        _availableStatSet = new HashMap<>();
    }

    public StatSet(Map<?, ?> initialMap)
    {
        _availableStatSet = new HashMap<>();
        for (Map.Entry<?, ?> entry : initialMap.entrySet())
        {
            final String key = String.valueOf(entry.getKey());
            final Object value = (Object) entry.getValue();
            _availableStatSet.put(key, value);
        }
    }

}
