package de.xn__ho_hia.memoization.caffeine;

import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;

final class CaffeineBasedFunctionMemoizer<KEY, VALUE>
        extends AbstractCaffeineBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    private final Function<KEY, VALUE> function;

    CaffeineBasedFunctionMemoizer(
            final Cache<KEY, VALUE> cache,
            final Function<KEY, VALUE> function) {
        super(cache);
        this.function = function;
    }

    @Override
    public VALUE apply(final KEY key) {
        return get(key, function);
    }

}
