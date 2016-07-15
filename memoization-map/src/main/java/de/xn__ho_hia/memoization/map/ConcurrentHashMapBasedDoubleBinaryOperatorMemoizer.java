package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoubleBinaryOperator;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Double>
        implements DoubleBinaryOperator {

    private final DoubleBinaryFunction<KEY> keyFunction;
    private final DoubleBinaryOperator      operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer(
            final Map<KEY, Double> preComputedValues,
            final DoubleBinaryFunction<KEY> keyFunction,
            final DoubleBinaryOperator operator) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleBinaryOperator - provide an actual DoubleBinaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double left, final double right) {
        final KEY key = keyFunction.apply(left, right);
        return computeIfAbsent(key, givenKey -> Double.valueOf(operator.applyAsDouble(left, right)))
                .doubleValue();
    }

}
