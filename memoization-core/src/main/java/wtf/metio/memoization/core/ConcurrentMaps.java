/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Utility class that helps with handling {@link ConcurrentMap}s.
 */
public final class ConcurrentMaps {

    private ConcurrentMaps() {
        // utility class
    }

    /**
     * Converts a given {@link Map} to a {@link ConcurrentMap}.
     *
     * @param map     The given map.
     * @param <KEY>   The key type of the map.
     * @param <VALUE> The value type of the map.
     * @return The converted map which may be the given one if it is already a {@link ConcurrentMap}.
     */
    @CheckReturnValue
    public static <KEY, VALUE> ConcurrentMap<KEY, VALUE> asConcurrentMap(final Map<KEY, VALUE> map) {
        Objects.requireNonNull(map, "Provide a non-null map or remove your custom cache entirely");
        if (ConcurrentMap.class.isAssignableFrom(map.getClass())) {
            return (ConcurrentMap<KEY, VALUE>) map;
        }
        return new ConcurrentHashMap<>(map);
    }

}
