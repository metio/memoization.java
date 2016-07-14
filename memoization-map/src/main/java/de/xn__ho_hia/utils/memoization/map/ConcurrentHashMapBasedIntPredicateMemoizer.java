package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.IntPredicate;

final class ConcurrentHashMapBasedIntPredicateMemoizer
        extends ConcurrentHashMapBasedMemoizer<Integer, Boolean>
        implements IntPredicate {

    private final IntPredicate predicate;

    @SuppressWarnings("nls")
    ConcurrentHashMapBasedIntPredicateMemoizer(
            final Map<Integer, Boolean> cache,
            final IntPredicate predicate) {
        super(cache);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final int value) {
        return computeIfAbsent(Integer.valueOf(value), key -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
