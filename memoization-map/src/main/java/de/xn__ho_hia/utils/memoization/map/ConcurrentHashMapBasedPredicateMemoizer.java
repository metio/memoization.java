package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Predicate;

final class ConcurrentHashMapBasedPredicateMemoizer<VALUE>
        implements Predicate<VALUE> {

    private final Map<VALUE, Boolean> cache;
    private final Predicate<VALUE>    predicate;

    @SuppressWarnings("nls")
    public ConcurrentHashMapBasedPredicateMemoizer(
            final Map<VALUE, Boolean> preComputedValues,
            final Predicate<VALUE> predicate) {
        this.cache = requireNonNull(preComputedValues,
                "Provide an empty map instead of NULL in case you don't have any precomputed values.");
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final VALUE input) {
        return cache.computeIfAbsent(input, predicate::test).booleanValue();
    }

}
