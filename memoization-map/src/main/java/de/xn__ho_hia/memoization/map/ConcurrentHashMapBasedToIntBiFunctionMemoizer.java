package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedToIntBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Integer>
        implements ToIntBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToIntBiFunction<FIRST, SECOND> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedToIntBiFunctionMemoizer(
            final Map<KEY, Integer> preComputedValues,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToIntBiFunction<FIRST, SECOND> function) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToIntBiFunction - provide an actual ToIntBiFunction to fix this.");
    }

    @Override
    public int applyAsInt(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(function.applyAsInt(t, u))).intValue();
    }

}
