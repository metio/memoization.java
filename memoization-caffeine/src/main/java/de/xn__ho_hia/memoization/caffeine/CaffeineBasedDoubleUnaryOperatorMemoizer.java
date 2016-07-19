package de.xn__ho_hia.memoization.caffeine;

import static java.util.Objects.requireNonNull;

import java.util.function.DoubleUnaryOperator;

import com.github.benmanes.caffeine.cache.Cache;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class CaffeineBasedDoubleUnaryOperatorMemoizer
        extends CaffeineBasedMemoizer<Double, Double>
        implements DoubleUnaryOperator {

    private final DoubleUnaryOperator operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    CaffeineBasedDoubleUnaryOperatorMemoizer(
            final Cache<Double, Double> cache,
            final DoubleUnaryOperator operator) {
        super(cache);
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleUnaryOperator - provide an actual DoubleUnaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double operand) {
        final Double key = Double.valueOf(operand);
        return get(key, givenKey -> Double.valueOf(operator.applyAsDouble(operand))).doubleValue();
    }

}
