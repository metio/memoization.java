package de.xn__ho_hia.memoization.caffeine;

import static java.util.Objects.requireNonNull;

import java.util.function.DoubleToLongFunction;

import com.github.benmanes.caffeine.cache.Cache;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class CaffeineBasedDoubleToLongFunctionMemoizer
        extends AbstractCaffeineBasedMemoizer<Double, Long>
        implements DoubleToLongFunction {

    private final DoubleToLongFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    CaffeineBasedDoubleToLongFunctionMemoizer(
            final Cache<Double, Long> cache,
            final DoubleToLongFunction function) {
        super(cache);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToIntFunction - provide an actual DoubleToIntFunction to fix this.");
    }

    @Override
    public long applyAsLong(final double value) {
        final Double key = Double.valueOf(value);
        return get(key, givenKey -> Long.valueOf(function.applyAsLong(value))).longValue();
    }

}
