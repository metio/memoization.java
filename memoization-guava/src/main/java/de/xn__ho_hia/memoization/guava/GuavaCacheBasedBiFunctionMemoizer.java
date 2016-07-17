package de.xn__ho_hia.memoization.guava;

import java.util.function.BiFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends AbstractGuavaCacheBasedMemoizer<KEY, VALUE>
        implements BiFunction<FIRST, SECOND, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY>   keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    GuavaCacheBasedBiFunctionMemoizer(
            final Cache<KEY, VALUE> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public VALUE apply(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return get(key, input -> biFunction.apply(first, second));
    }

}
