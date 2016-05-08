package de.xn__ho_hia.utils.memoization.map;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class ConcurrentHashMapBasedMemoizer<KEY, VALUE> {

    private final Map<KEY, VALUE> cache;

    ConcurrentHashMapBasedMemoizer(final Map<KEY, VALUE> preComputedValues) {
        this.cache = new ConcurrentHashMap<>(preComputedValues);
    }

    protected final VALUE computeIfAbsent(final KEY key, final Function<KEY, VALUE> mappingFunction) {
        return cache.computeIfAbsent(key, mappingFunction);
    }

    final Map<KEY, VALUE> viewCacheForTest() {
        return Collections.unmodifiableMap(cache);
    }

}
