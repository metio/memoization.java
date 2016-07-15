package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ToIntFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedToIntFunctionMemoizer<VALUE>
        extends ConcurrentHashMapBasedMemoizer<VALUE, Integer>
        implements ToIntFunction<VALUE> {

    private final ToIntFunction<VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedToIntFunctionMemoizer(
            final Map<VALUE, Integer> preComputedValues,
            final ToIntFunction<VALUE> function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToIntFunction - provide an actual ToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final VALUE value) {
        return computeIfAbsent(value, givenKey -> Integer.valueOf(function.applyAsInt(value)))
                .intValue();
    }

}
