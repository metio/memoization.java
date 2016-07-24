/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
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
