package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.IntToLongFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedIntToLongFunctionMemoizer
        extends ConcurrentHashMapBasedMemoizer<Integer, Long>
        implements IntToLongFunction {

    private final IntToLongFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedIntToLongFunctionMemoizer(
            final Map<Integer, Long> preComputedValues,
            final IntToLongFunction function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntToLongFunction - provide an actual IntToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final int value) {
        final Integer key = Integer.valueOf(value);
        return computeIfAbsent(key, givenKey -> Long.valueOf(function.applyAsLong(value)))
                .longValue();
    }

}
