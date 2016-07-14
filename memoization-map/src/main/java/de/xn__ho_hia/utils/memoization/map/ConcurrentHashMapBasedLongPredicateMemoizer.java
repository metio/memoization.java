package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongPredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongPredicateMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Boolean>
        implements LongPredicate {

    private final LongPredicate predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedLongPredicateMemoizer(
            final Map<Long, Boolean> cache,
            final LongPredicate predicate) {
        super(cache);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final long value) {
        return computeIfAbsent(Long.valueOf(value), key -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
