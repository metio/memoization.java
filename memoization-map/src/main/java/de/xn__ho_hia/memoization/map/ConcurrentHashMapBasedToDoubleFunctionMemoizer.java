package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ToDoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedToDoubleFunctionMemoizer<VALUE>
        extends ConcurrentHashMapBasedMemoizer<VALUE, Double>
        implements ToDoubleFunction<VALUE> {

    private final ToDoubleFunction<VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedToDoubleFunctionMemoizer(
            final Map<VALUE, Double> preComputedValues,
            final ToDoubleFunction<VALUE> function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final VALUE value) {
        return computeIfAbsent(value, givenKey -> Double.valueOf(function.applyAsDouble(value)))
                .doubleValue();
    }

}
