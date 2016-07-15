package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoubleToLongFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoubleToLongFunctionMemoizer
        extends ConcurrentHashMapBasedMemoizer<Double, Long>
        implements DoubleToLongFunction {

    private final DoubleToLongFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(
            final Map<Double, Long> preComputedValues,
            final DoubleToLongFunction function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToLongFunction - provide an actual DoubleToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final double value) {
        final Double key = Double.valueOf(value);
        return computeIfAbsent(key, givenKey -> Long.valueOf(function.applyAsLong(value)))
                .longValue();
    }

}
