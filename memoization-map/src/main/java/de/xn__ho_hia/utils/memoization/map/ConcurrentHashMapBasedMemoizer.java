package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class ConcurrentHashMapBasedMemoizer<KEY, VALUE> {

    private final Map<KEY, VALUE> cache;

    @SuppressWarnings("nls")
    ConcurrentHashMapBasedMemoizer(final Map<KEY, VALUE> preComputedValues) {
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
