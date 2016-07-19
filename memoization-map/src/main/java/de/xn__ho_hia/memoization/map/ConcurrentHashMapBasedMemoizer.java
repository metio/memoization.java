/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

abstract class ConcurrentHashMapBasedMemoizer<KEY, VALUE> {

    private final Map<KEY, VALUE> cache;

    @SuppressWarnings(CompilerWarnings.NLS)
    protected ConcurrentHashMapBasedMemoizer(final Map<KEY, VALUE> preComputedValues) {
        this.cache = new ConcurrentHashMap<>(requireNonNull(preComputedValues,
                "Provide an empty map instead of NULL in case you don't have any precomputed values."));
    }

    protected final VALUE computeIfAbsent(final KEY key, final Function<KEY, VALUE> mappingFunction) {
        return cache.computeIfAbsent(key, mappingFunction);
    }

    final Map<KEY, VALUE> viewCacheForTest() {
        return Collections.unmodifiableMap(cache);
    }

}
