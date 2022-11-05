/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class ConcurrentMaps {

    private ConcurrentMaps() {
        // utility class
    }

    static <KEY, VALUE> ConcurrentMap<KEY, VALUE> asConcurrentMap(final Map<KEY, VALUE> map) {
        if (ConcurrentMap.class.isAssignableFrom(map.getClass())) {
            return (ConcurrentMap<KEY, VALUE>) nullsafe(map);
        }
        return new ConcurrentHashMap<>(nullsafe(map));
    }

    // TODO: remove?
    static <MAP extends Map<?, ?>> MAP nullsafe(final MAP map) {
        return requireNonNull(map, "Provide an empty map instead of NULL.");
    }

}
