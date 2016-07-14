package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoublePredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoublePredicateMemoizer
        extends ConcurrentHashMapBasedMemoizer<Double, Boolean>
        implements DoublePredicate {

    private final DoublePredicate predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedDoublePredicateMemoizer(
            final Map<Double, Boolean> cache,
            final DoublePredicate predicate) {
        super(cache);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final double value) {
        return computeIfAbsent(Double.valueOf(value), key -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
