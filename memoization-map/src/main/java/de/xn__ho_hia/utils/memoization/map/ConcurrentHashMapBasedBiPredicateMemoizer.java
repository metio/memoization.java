package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class ConcurrentHashMapBasedBiPredicateMemoizer<FIRST, SECOND, KEY>
        implements BiPredicate<FIRST, SECOND> {

    private final Map<KEY, Boolean>              cache;
    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND>     biPredicate;

    @SuppressWarnings("nls")
    public ConcurrentHashMapBasedBiPredicateMemoizer(
            final Map<KEY, Boolean> preComputedValues,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiPredicate<FIRST, SECOND> biPredicate) {
        this.cache = requireNonNull(preComputedValues,
                "Provide an empty map instead of NULL in case you don't have any precomputed values.");
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biPredicate = requireNonNull(biPredicate,
                "Cannot memoize a NULL BiPredicate - provide an actual BiPredicate to fix this.");
    }

    @Override
    public boolean test(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return cache.computeIfAbsent(key, givenKey -> Boolean.valueOf(biPredicate.test(t, u))).booleanValue();
    }

    final Map<KEY, Boolean> viewCacheForTest() {
        return Collections.unmodifiableMap(cache);
    }

}
