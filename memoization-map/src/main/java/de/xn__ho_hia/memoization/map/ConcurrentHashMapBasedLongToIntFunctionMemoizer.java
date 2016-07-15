package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongToIntFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongToIntFunctionMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Integer>
        implements LongToIntFunction {

    private final LongToIntFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedLongToIntFunctionMemoizer(
            final Map<Long, Integer> preComputedValues,
            final LongToIntFunction function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToIntFunction - provide an actual LongToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final long value) {
        final Long key = Long.valueOf(value);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(function.applyAsInt(value)))
                .intValue();
    }

}
