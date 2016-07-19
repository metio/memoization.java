package de.xn__ho_hia.memoization.caffeine;

import static java.util.Objects.requireNonNull;

import java.util.function.DoubleToIntFunction;

import com.github.benmanes.caffeine.cache.Cache;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class CaffeineBasedDoubleToIntFunctionMemoizer
        extends AbstractCaffeineBasedMemoizer<Double, Integer>
        implements DoubleToIntFunction {

    private final DoubleToIntFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    CaffeineBasedDoubleToIntFunctionMemoizer(
            final Cache<Double, Integer> cache,
            final DoubleToIntFunction function) {
        super(cache);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToIntFunction - provide an actual DoubleToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final double value) {
        final Double key = Double.valueOf(value);
        return get(key, givenKey -> Integer.valueOf(function.applyAsInt(value))).intValue();
    }

}
