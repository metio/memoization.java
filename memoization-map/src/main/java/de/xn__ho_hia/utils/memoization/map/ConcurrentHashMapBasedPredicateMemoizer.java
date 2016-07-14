package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Predicate;

final class ConcurrentHashMapBasedPredicateMemoizer<VALUE>
        extends ConcurrentHashMapBasedMemoizer<VALUE, Boolean>
        implements Predicate<VALUE> {

    private final Predicate<VALUE> predicate;

    @SuppressWarnings("nls")
    public ConcurrentHashMapBasedPredicateMemoizer(
            final Map<VALUE, Boolean> preComputedValues,
            final Predicate<VALUE> predicate) {
        super(preComputedValues);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final VALUE input) {
        return computeIfAbsent(input, predicate::test).booleanValue();
    }

}
