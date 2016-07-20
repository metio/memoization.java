package de.xn__ho_hia.memoization.jcache;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

final class JCacheFunctionBasedCacheLoader<KEY, VALUE> implements CacheLoader<KEY, VALUE> {

    private final Function<KEY, VALUE> function;

    JCacheFunctionBasedCacheLoader(final Function<KEY, VALUE> function) {
        this.function = function;
    }

    @Override
    public VALUE load(final KEY key) throws CacheLoaderException {
        return function.apply(key);
    }

    @Override
    public Map<KEY, VALUE> loadAll(final Iterable<? extends KEY> keys) throws CacheLoaderException {
        return StreamSupport.stream(keys.spliterator(), false)
                .collect(Collectors.toMap(Function.identity(), this::load));
    }

}
