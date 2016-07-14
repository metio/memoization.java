package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Function;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedFunctionMemoizer<KEY, VALUE>
        extends ConcurrentHashMapBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    private final Function<KEY, VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedFunctionMemoizer(
            final Map<KEY, VALUE> preComputedValues,
            final Function<KEY, VALUE> function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function - provide an actual Function to fix this.");
    }

    @Override
    public VALUE apply(final KEY input) {
        return computeIfAbsent(input, function);
    }

}
