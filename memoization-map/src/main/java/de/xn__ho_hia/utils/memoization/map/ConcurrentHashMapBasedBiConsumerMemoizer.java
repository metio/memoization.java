package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

final class ConcurrentHashMapBasedBiConsumerMemoizer<FIRST, SECOND, KEY>
        implements BiConsumer<FIRST, SECOND> {

    private final Map<KEY, KEY>                  cache;
    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiConsumer<FIRST, SECOND>      biConsumer;

    @SuppressWarnings("nls")
    public ConcurrentHashMapBasedBiConsumerMemoizer(
            final Map<KEY, KEY> preComputedValues,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiConsumer<FIRST, SECOND> biConsumer) {
        this.cache = requireNonNull(preComputedValues,
                "Provide an empty map instead of NULL in case you don't have any precomputed values.");
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biConsumer = requireNonNull(biConsumer,
                "Cannot memoize a NULL BiConsumer - provide an actual BiConsumer to fix this.");
    }

    @Override
    public void accept(final FIRST second, final SECOND first) {
        final KEY key = keyFunction.apply(second, first);
        cache.computeIfAbsent(key, givenKey -> {
            biConsumer.accept(second, first);
            return givenKey;
        });
    }

    final Map<KEY, KEY> viewCacheForTest() {
        return Collections.unmodifiableMap(cache);
    }

}
