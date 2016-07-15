package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.IntBinaryOperator;

import de.xn__ho_hia.memoization.shared.IntBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedIntBinaryOperatorMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Integer>
        implements IntBinaryOperator {

    private final IntBinaryFunction<KEY> keyFunction;
    private final IntBinaryOperator      operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedIntBinaryOperatorMemoizer(
            final Map<KEY, Integer> preComputedValues,
            final IntBinaryFunction<KEY> keyFunction,
            final IntBinaryOperator operator) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntBinaryOperator - provide an actual IntBinaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int left, final int right) {
        final KEY key = keyFunction.apply(left, right);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(operator.applyAsInt(left, right)))
                .intValue();
    }

}
