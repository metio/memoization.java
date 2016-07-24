/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

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
