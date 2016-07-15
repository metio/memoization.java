package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ToLongFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedToLongFunctionMemoizer<VALUE>
        extends ConcurrentHashMapBasedMemoizer<VALUE, Long>
        implements ToLongFunction<VALUE> {

    private final ToLongFunction<VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedToLongFunctionMemoizer(
            final Map<VALUE, Long> preComputedValues,
            final ToLongFunction<VALUE> function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToLongFunction - provide an actual ToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final VALUE value) {
        return computeIfAbsent(value, givenKey -> Long.valueOf(function.applyAsLong(value)))
                .longValue();
    }

}
