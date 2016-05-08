package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Function;

final class ConcurrentHashMapBasedFunctionMemoizer<KEY, VALUE>
        extends ConcurrentHashMapBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    private final Function<KEY, VALUE> function;

    @SuppressWarnings("nls")
    ConcurrentHashMapBasedFunctionMemoizer(
            final Map<KEY, VALUE> preComputedValues,
            final Function<KEY, VALUE> function) {
        super(requireNonNull(preComputedValues,
                "Provide an empty map instead of NULL in case you don't have any precomputed values."));
        this.function = requireNonNull(function,
                "Cannot memoize a NULL function - provide an actual function to fix this.");
    }

    @Override
    public VALUE apply(final KEY input) {
        return computeIfAbsent(input, function);
    }

}
