/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

abstract class ConcurrentMapBasedMemoizer<KEY, VALUE> {

    private final ConcurrentMap<KEY, VALUE> cache;

    protected ConcurrentMapBasedMemoizer(final ConcurrentMap<KEY, VALUE> cache) {
        this.cache = ConcurrentMaps.nullsafe(cache);
    }

    protected final VALUE computeIfAbsent(final KEY key, final Function<KEY, VALUE> mappingFunction) {
        return cache.computeIfAbsent(key, mappingFunction);
    }

    final Map<KEY, VALUE> viewCacheForTest() {
        return Collections.unmodifiableMap(cache);
    }

}
