package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.IntUnaryOperator;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedIntUnaryOperatorMemoizer
        extends ConcurrentHashMapBasedMemoizer<Integer, Integer>
        implements IntUnaryOperator {

    private final IntUnaryOperator operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedIntUnaryOperatorMemoizer(
            final Map<Integer, Integer> preComputedValues,
            final IntUnaryOperator operator) {
        super(preComputedValues);
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntUnaryOperator - provide an actual IntUnaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int operand) {
        final Integer key = Integer.valueOf(operand);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(operator.applyAsInt(operand)))
                .intValue();
    }

}
