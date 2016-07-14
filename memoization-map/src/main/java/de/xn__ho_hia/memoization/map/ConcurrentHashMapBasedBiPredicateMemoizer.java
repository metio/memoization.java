package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedBiPredicateMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Boolean>
        implements BiPredicate<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND>     biPredicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedBiPredicateMemoizer(
            final Map<KEY, Boolean> preComputedValues,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiPredicate<FIRST, SECOND> biPredicate) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biPredicate = requireNonNull(biPredicate,
                "Cannot memoize a NULL BiPredicate - provide an actual BiPredicate to fix this.");
    }

    @Override
    public boolean test(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> Boolean.valueOf(biPredicate.test(t, u))).booleanValue();
    }

}
