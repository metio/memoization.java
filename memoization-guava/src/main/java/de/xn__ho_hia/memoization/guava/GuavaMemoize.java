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

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.xn__ho_hia.memoization.shared.MemoizationDefaults;

/**
 * <p>
 * Factory for lightweight wrappers that store the result of a potentially expensive function call. Each method of this
 * class exposes two of the following features:
 * </p>
 * <strong>Default cache</strong>
 * <p>
 * The memoizer uses the default cache of this factory. Current implementation creates a new {@link Cache} per memoizer.
 * </p>
 * <strong>Default cache key</strong>
 * <p>
 * The memoizer uses the default {@link BiFunction} or {@link Supplier} to calculate the cache key for each call. Either
 * uses the natural key (e.g. the input itself) or one of the methods in {@link MemoizationDefaults}.
 * </p>
 * <strong>Custom cache</strong>
 * <p>
 * The memoizer uses a user-provided {@link Cache} as its cache. It is possible to add values to the cache both before
 * and after the memoizer was created.
 * </p>
 * <strong>Custom cache key</strong>
 * <p>
 * The memoizer uses a user-defined {@link BiFunction} or {@link Supplier} to calculate the cache key for each call.
 * Take a look at {@link MemoizationDefaults} for a possible key functions and suppliers.
 * </p>
 *
 * @see BiFunction
 * @see Consumer
 * @see DoubleFunction
 * @see Function
 * @see IntFunction
 * @see LongFunction
 * @see Predicate
 * @see Supplier
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class GuavaMemoize {

    private GuavaMemoize() {
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
    public static final <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return supplier(supplier, CacheBuilder.newBuilder().build());
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
    public static final <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier) {
        return supplier(supplier, keySupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static final <OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Cache<String, OUTPUT> cache) {
        return supplier(supplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static final <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, OUTPUT> cache) {
        return new GuavaCacheBasedSupplierMemoizer<>(cache, keySupplier, supplier);
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
    public static final <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function) {
        return function(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link Function} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @return The wrapped {@link Function}.
     */
    public static final <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return function(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link Function} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Function}.
     */
    public static final <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Cache<INPUT, OUTPUT> cache) {
        return function(function, Function.identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link Function} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Function}.
     */
    public static final <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new GuavaCacheBasedFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntFunction} to memoize.
     * @return The wrapped {@link IntFunction}.
     */
    public static final <OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function) {
        return intFunction(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntFunction} to memoize.
     * @param keyFunction
     *            The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntFunction}.
     */
    public static final <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction) {
        return intFunction(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntFunction}.
     */
    public static final <OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final Cache<Integer, OUTPUT> cache) {
        return intFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntFunction} to memoize.
     * @param keyFunction
     *            The {@link IntFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntFunction}.
     */
    public static final <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new GuavaCacheBasedIntFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongFunction} to memoize.
     * @return The wrapped {@link LongFunction}.
     */
    public static final <OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function) {
        return longFunction(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongFunction} to memoize.
     * @param keyFunction
     *            The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongFunction}.
     */
    public static final <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction) {
        return longFunction(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongFunction}.
     */
    public static final <OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final Cache<Long, OUTPUT> cache) {
        return longFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongFunction} to memoize.
     * @param keyFunction
     *            The {@link LongFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongFunction}.
     */
    public static final <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new GuavaCacheBasedLongFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleFunction} to memoize.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static final <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function) {
        return doubleFunction(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleFunction} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static final <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction) {
        return doubleFunction(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static final <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final Cache<Double, OUTPUT> cache) {
        return doubleFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleFunction} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static final <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new GuavaCacheBasedDoubleFunctionMemoizer<>(cache, keyFunction, function);
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
    public static final <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return biFunction(biFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiFunction}.
     */
    public static final <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biFunction(biFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
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
    public static final <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final Cache<String, OUTPUT> cache) {
        return biFunction(biFunction, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
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
    public static final <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
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
    public static final <INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer) {
        return consumer(consumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link Consumer} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @return The wrapped {@link Consumer}.
     */
    public static final <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction) {
        return consumer(consumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
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
    public static final <INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Cache<INPUT, INPUT> cache) {
        return consumer(consumer, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link Consumer} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Consumer}.
     */
    public static final <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return new GuavaCacheBasedConsumerMemoizer<>(cache, keyFunction, consumer);
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
    public static final <INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate) {
        return predicate(predicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link Predicate} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @return The wrapped {@link Predicate}.
     */
    public static final <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction) {
        return predicate(predicate, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
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
    public static final <INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Cache<INPUT, Boolean> cache) {
        return predicate(predicate, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link Predicate} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Predicate}.
     */
    public static final <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return new GuavaCacheBasedPredicateMemoizer<>(cache, keyFunction, predicate);
    }

}
