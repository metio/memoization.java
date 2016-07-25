/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static java.util.function.Function.identity;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import de.xn__ho_hia.memoization.shared.MemoizationDefaults;

/**
 * <p>
 * Factory for lightweight wrappers that store the result of a potentially expensive function call. Each method of this
 * class exposes zero or more of the following features:
 * </p>
 * <strong>Default cache</strong>
 * <p>
 * The memoizer uses the default cache of this factory. Current implementation creates a new {@link ConcurrentMap} per
 * memoizer.
 * </p>
 * <strong>Default cache key</strong>
 * <p>
 * The memoizer uses the default {@link BiFunction} or {@link Supplier} to calculate the cache key for each call. Either
 * uses the natural key (e.g. the input itself) or one of the methods in {@link MemoizationDefaults}.
 * </p>
 * <strong>Custom cache</strong>
 * <p>
 * The memoizer uses a user-provided {@link ConcurrentMap} as its cache. It is possible to add values to the cache both
 * before and after the memoizer was created.
 * </p>
 * <strong>Custom cache key</strong>
 * <p>
 * The memoizer uses a user-defined {@link BiFunction} or {@link Supplier} to calculate the cache key for each call.
 * Take a look at {@link MemoizationDefaults} for a possible key functions and suppliers.
 * </p>
 * <strong>Limits entries</strong>
 * <p>
 * The memoizer limits the number of entries it will memoize. Adding more values, will lead to cache eviction of
 * previously calculated values.
 * </p>
 *
 * @see BiFunction
 * @see Consumer
 * @see Function
 * @see Supplier
 * @see Predicate
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class GuavaMemoization {

    private GuavaMemoization() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link Supplier}.
     */
    public static final <VALUE> Supplier<VALUE> memoize(final Supplier<VALUE> supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
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
        return memoize(keySupplier, CacheBuilder.newBuilder()
                .maximumSize(1)
                .build(new SupplierBasedCacheLoader<>(supplier)));
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a previously constructed Guava {@link LoadingCache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link LoadingCache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static final <KEY, VALUE> Supplier<VALUE> memoize(
            final Supplier<KEY> keySupplier,
            final LoadingCache<KEY, VALUE> cache) {
        return new GuavaCacheBasedSupplierMemoizer<>(cache, keySupplier);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link Function} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
            final Function<KEY, VALUE> function) {
        return memoize(new FunctionBasedCacheLoader<>(function));
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a previously constructed Guava {@link CacheLoader}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param loader
     *            The {@link CacheLoader} to use.
     * @return The wrapped {@link Function}.
     */
    public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
            final CacheLoader<KEY, VALUE> loader) {
        return memoize(CacheBuilder.newBuilder().build(loader));
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a previously constructed Guava {@link LoadingCache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param cache
     *            The {@link LoadingCache} to use.
     * @return The wrapped {@link Function}.
     */
    public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
            final LoadingCache<KEY, VALUE> cache) {
        return new GuavaCacheBasedFunctionMemoizer<>(cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @return The wrapped {@link BiFunction}.
     */
    public static final <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        return memoize(biFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a previously constructed Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiFunction}.
     */
    public static final <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final Cache<String, VALUE> cache) {
        return memoize(biFunction, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a previously constructed Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiFunction}.
     */
    public static final <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, VALUE> cache) {
        return new GuavaCacheBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link Consumer} to memoize.
     * @return The wrapped {@link Consumer}.
     */
    public static final <VALUE> Consumer<VALUE> memoize(
            final Consumer<VALUE> consumer) {
        return memoize(consumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a previously constructed {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link Consumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Consumer}.
     */
    public static final <VALUE> Consumer<VALUE> memoize(
            final Consumer<VALUE> consumer,
            final Cache<VALUE, VALUE> cache) {
        return new GuavaCacheBasedConsumerMemoizer<>(cache, identity(), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link Predicate} to memoize.
     * @return The wrapped {@link Predicate}.
     */
    public static final <VALUE> Predicate<VALUE> memoize(
            final Predicate<VALUE> predicate) {
        return memoize(predicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a previously constructed {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link Predicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Predicate}.
     */
    public static final <VALUE> Predicate<VALUE> memoize(
            final Predicate<VALUE> predicate,
            final Cache<VALUE, Boolean> cache) {
        return new GuavaCacheBasedPredicateMemoizer<>(cache, predicate);
    }

}
