package de.xn__ho_hia.memoization.caffeine;

import static java.util.Objects.requireNonNull;

import java.util.function.DoubleBinaryOperator;

import com.github.benmanes.caffeine.cache.Cache;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class CaffeineBasedDoubleBinaryOperatorMemoizer<KEY>
        extends AbstractCaffeineBasedMemoizer<KEY, Double>
        implements DoubleBinaryOperator {

    private final DoubleBinaryFunction<KEY> keyFunction;
    private final DoubleBinaryOperator      operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    CaffeineBasedDoubleBinaryOperatorMemoizer(
            final Cache<KEY, Double> cache,
            final DoubleBinaryFunction<KEY> keyFunction,
            final DoubleBinaryOperator operator) {
        super(cache);
        this.keyFunction = keyFunction;
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleBinaryOperator - provide an actual DoubleBinaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double left, final double right) {
        final KEY key = keyFunction.apply(left, right);
        return get(key, givenKey -> Double.valueOf(operator.applyAsDouble(left, right)))
                .doubleValue();
    }

}
