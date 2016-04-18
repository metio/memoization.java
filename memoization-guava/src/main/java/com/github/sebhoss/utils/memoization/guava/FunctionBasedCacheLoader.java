package com.github.sebhoss.utils.memoization.guava;

import com.google.common.cache.CacheLoader;

import java.util.Objects;
import java.util.function.Function;

final class FunctionBasedCacheLoader<KEY, VALUE> extends CacheLoader<KEY, VALUE> {

    private final Function<KEY, VALUE> function;

    FunctionBasedCacheLoader(final Function<KEY, VALUE> function) {
        this.function = Objects.requireNonNull(function, "Provide a function to load values into the cache!");
    }

    @Override
    public VALUE load(final KEY key) throws Exception {
        return function.apply(key);
    }

}
