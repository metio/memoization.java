package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongUnaryOperator;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongUnaryOperatorMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Long>
        implements LongUnaryOperator {

    private final LongUnaryOperator operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedLongUnaryOperatorMemoizer(
            final Map<Long, Long> preComputedValues,
            final LongUnaryOperator operator) {
        super(preComputedValues);
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL LongUnaryOperator - provide an actual LongUnaryOperator to fix this.");
    }

    @Override
    public long applyAsLong(final long operand) {
        final Long key = Long.valueOf(operand);
        return computeIfAbsent(key, givenKey -> Long.valueOf(operator.applyAsLong(operand)))
                .intValue();
    }

}
