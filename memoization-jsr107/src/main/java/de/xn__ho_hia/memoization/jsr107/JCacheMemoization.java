/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jsr107;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.defaultKeySupplier;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.integration.CacheLoader;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 *
 * @see Supplier
 * @see Function
 * @see BiFunction
 * @see Consumer
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class JCacheMemoization {

    private static final AtomicLong   CACHE_COUNTER = new AtomicLong(0);
    private static final CacheManager CACHE_MANAGER = Caching.getCachingProvider().getCacheManager();

    /**
     * Memoizes a {@link Supplier} in a JCache {@link Cache}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link Supplier}.
     */
    public static final <VALUE> Supplier<VALUE> memoize(final Supplier<VALUE> supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * Memoizes a {@link Supplier} in a JCache {@link Cache}. Saves the result under a specific cache key provided by a
     * key-supplier.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link Supplier}.
     */
    public static final <KEY, VALUE> Supplier<VALUE> memoize(
            final Supplier<VALUE> supplier,
            final Supplier<KEY> keySupplier) {
        return memoize(keySupplier, createCache(Supplier.class.getSimpleName(), supplierFactory(supplier)));
    }

    /**
     * Memoizes a {@link Supplier} in a previously constructed JCache {@link Cache}. Saves the result under a specific
     * cache key provided by a key-supplier.
     *
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static final <KEY, VALUE> Supplier<VALUE> memoize(
            final Supplier<KEY> keySupplier,
            final Cache<KEY, VALUE> cache) {
        return new JCacheBasedSupplierMemoizer<>(cache, keySupplier);
    }

    /**
     * Memoizes a {@link Function} in a JCache {@link Cache}.
     *
     * @param function
     *            The {@link Function} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static final <KEY, VALUE> Function<KEY, VALUE> memoize(final Function<KEY, VALUE> function) {
        return memoize(createCache(Function.class.getSimpleName(), functionFactory(function)));
    }

    /**
     * Memoizes a {@link Function} in a previously constructed JCache {@link Cache}.
     *
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Function}.
     */
    public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
            final Cache<KEY, VALUE> cache) {
        return new JCacheBasedFunctionMemoizer<>(cache);
    }

    static <KEY, VALUE> Cache<KEY, VALUE> createCache(
            final String typeName,
            final Factory<? extends CacheLoader<KEY, VALUE>> factory) {
        final MutableConfiguration<KEY, VALUE> configuration = new MutableConfiguration<>();
        configuration.setCacheLoaderFactory(factory);
        configuration.setReadThrough(true);
        return CACHE_MANAGER.createCache(generateCacheName(typeName), configuration);
    }

    static String generateCacheName(final String type) {
        return JCacheMemoization.class.getSimpleName() + type + CACHE_COUNTER.getAndIncrement();
    }

    static <KEY, VALUE> Factory<CacheLoader<KEY, VALUE>> supplierFactory(final Supplier<VALUE> supplier) {
        return () -> new JCacheSupplierBasedCacheLoader<>(supplier);
    }

    static <KEY, VALUE> Factory<CacheLoader<KEY, VALUE>> functionFactory(final Function<KEY, VALUE> function) {
        return () -> new JCacheFunctionBasedCacheLoader<>(function);
    }

    private JCacheMemoization() {
        // factory class
    }

}
