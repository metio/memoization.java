package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongToDoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongToDoubleFunctionMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Double>
        implements LongToDoubleFunction {

    private final LongToDoubleFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(
            final Map<Long, Double> preComputedValues,
            final LongToDoubleFunction function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToDoubleFunction - provide an actual LongToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final long value) {
        final Long key = Long.valueOf(value);
        return computeIfAbsent(key, givenKey -> Double.valueOf(function.applyAsDouble(value)))
                .doubleValue();
    }

}
