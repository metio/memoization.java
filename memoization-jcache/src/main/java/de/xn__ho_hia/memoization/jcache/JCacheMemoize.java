/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.objIntConsumerHashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.objLongConsumerHashCodeKeyFunction;
import static java.util.function.Function.identity;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.memoization.shared.IntBinaryFunction;
import de.xn__ho_hia.memoization.shared.LongBinaryFunction;
import de.xn__ho_hia.memoization.shared.MemoizationDefaults;
import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.memoization.shared.ObjIntFunction;
import de.xn__ho_hia.memoization.shared.ObjLongFunction;

/**
 * <p>
 * Factory for lightweight wrappers that store the result of a potentially expensive function call. Each method of this
 * class exposes zero or more of the following features:
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
 * @see BiConsumer
 * @see BiFunction
 * @see BiPredicate
 * @see BooleanSupplier
 * @see Consumer
 * @see DoubleBinaryOperator
 * @see DoubleConsumer
 * @see DoubleFunction
 * @see DoublePredicate
 * @see DoubleSupplier
 * @see DoubleToIntFunction
 * @see DoubleToLongFunction
 * @see DoubleUnaryOperator
 * @see Function
 * @see IntBinaryOperator
 * @see IntConsumer
 * @see IntFunction
 * @see IntPredicate
 * @see IntSupplier
 * @see LongBinaryOperator
 * @see LongConsumer
 * @see LongFunction
 * @see LongPredicate
 * @see LongSupplier
 * @see ObjDoubleConsumer
 * @see ObjIntConsumer
 * @see ObjLongConsumer
 * @see Predicate
 * @see Supplier
 * @see ToDoubleBiFunction
 * @see ToDoubleFunction
 * @see ToIntBiFunction
 * @see ToLongBiFunction
 * @see ToLongFunction
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class JCacheMemoize {

    private static final AtomicLong   CACHE_COUNTER = new AtomicLong(0);
    private static final CacheManager CACHE_MANAGER = Caching.getCachingProvider().getCacheManager();

    private JCacheMemoize() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param biConsumer
     *            The {@link BiConsumer} to memoize.
     * @return The wrapped {@link BiConsumer}.
     */
    public static final <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer) {
        return biConsumer(biConsumer, createCache(BiConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param biConsumer
     *            The {@link BiConsumer} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiConsumer}.
     */
    public static final <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biConsumer(biConsumer, keyFunction, createCache(BiConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param biConsumer
     *            The {@link BiConsumer} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiConsumer}.
     */
    public static final <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, KEY> cache) {
        return new JCacheBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param biConsumer
     *            The {@link BiConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiConsumer}.
     */
    public static final <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final Cache<String, String> cache) {
        return biConsumer(biConsumer, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a JCache {@link Cache}.
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
        return biFunction(biFunction, createCache(BiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a JCache {@link Cache}.
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
        return biFunction(biFunction, keyFunction, createCache(BiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a JCache {@link Cache}.
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
        return new JCacheBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a JCache {@link Cache}.
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
     * Memoizes a {@link BiPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param biPredicate
     *            The {@link BiPredicate} to memoize.
     * @return The wrapped {@link BiPredicate}.
     */
    public static final <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate) {
        return biPredicate(biPredicate, createCache(BiPredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param biPredicate
     *            The {@link BiPredicate} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiPredicate}.
     */
    public static final <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biPredicate(biPredicate, keyFunction, createCache(BiPredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param biPredicate
     *            The {@link BiPredicate} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static final <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return new JCacheBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param biPredicate
     *            The {@link BiPredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static final <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate,
            final Cache<String, Boolean> cache) {
        return biPredicate(biPredicate, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param booleanSupplier
     *            The {@link BooleanSupplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static final BooleanSupplier booleanSupplier(final BooleanSupplier booleanSupplier) {
        return booleanSupplier(booleanSupplier, createCache(BooleanSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param booleanSupplier
     *            The {@link BooleanSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static final BooleanSupplier booleanSupplier(
            final BooleanSupplier booleanSupplier,
            final Cache<String, Boolean> cache) {
        return booleanSupplier(booleanSupplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a JCache {@link Cache}.
     * </p>
     * BooleanSupplier
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param booleanSupplier
     *            The {@link BooleanSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static final <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier booleanSupplier,
            final Supplier<KEY> keySupplier) {
        return booleanSupplier(booleanSupplier, keySupplier, createCache(BooleanSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param booleanSupplier
     *            The {@link BooleanSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static final <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier booleanSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Boolean> cache) {
        return new JCacheBasedBooleanSupplierMemoizer<>(cache, keySupplier, booleanSupplier);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a JCache {@link Cache}.
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
    public static final <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return consumer(consumer, createCache(Consumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a JCache {@link Cache}.
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
        return consumer(consumer, Function.identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a JCache {@link Cache}.
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
     * @return The wrapped {@link Consumer}.
     */
    public static final <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction) {
        return consumer(consumer, keyFunction, createCache(Consumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a JCache {@link Cache}.
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
        return new JCacheBasedConsumerMemoizer<>(cache, keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleBinaryOperator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static final DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator doubleBinaryOperator) {
        return doubleBinaryOperator(doubleBinaryOperator, createCache(DoubleBinaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleBinaryOperator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static final DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator,
            final Cache<String, Double> cache) {
        return doubleBinaryOperator(doubleBinaryOperator, doubleBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleBinaryOperator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction
     *            The {@link DoubleBinaryFunction} to compute the cache key.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static final <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator,
            final DoubleBinaryFunction<KEY> keyFunction) {
        return doubleBinaryOperator(doubleBinaryOperator, keyFunction, createCache(DoubleBinaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleBinaryOperator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction
     *            The {@link DoubleBinaryFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static final <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator,
            final DoubleBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return new JCacheBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, doubleBinaryOperator);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleConsumer
     *            The {@link DoubleConsumer} to memoize.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static final DoubleConsumer doubleConsumer(final DoubleConsumer doubleConsumer) {
        return doubleConsumer(doubleConsumer, createCache(DoubleConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleConsumer
     *            The {@link DoubleConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static final DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer,
            final Cache<Double, Double> cache) {
        return doubleConsumer(doubleConsumer, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleConsumer
     *            The {@link DoubleConsumer} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static final <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer,
            final DoubleFunction<KEY> keyFunction) {
        return doubleConsumer(doubleConsumer, keyFunction, createCache(DoubleConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleConsumer
     *            The {@link DoubleConsumer} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static final <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return new JCacheBasedDoubleConsumerMemoizer<>(cache, keyFunction, doubleConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return doubleFunction(function, createCache(DoubleFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a JCache {@link Cache}.
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
     * Memoizes a {@link DoubleFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT, KEY> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction) {
        return doubleFunction(function, keyFunction, createCache(DoubleFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT, KEY> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new JCacheBasedDoubleFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doublePredicate
     *            The {@link DoublePredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static final DoublePredicate doublePredicate(final DoublePredicate doublePredicate) {
        return doublePredicate(doublePredicate, createCache(DoublePredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doublePredicate
     *            The {@link DoublePredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static final DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate,
            final Cache<Double, Boolean> cache) {
        return doublePredicate(doublePredicate, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doublePredicate
     *            The {@link DoublePredicate} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static final <KEY> DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate,
            final DoubleFunction<KEY> keyFunction) {
        return doublePredicate(doublePredicate, keyFunction, createCache(DoublePredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doublePredicate
     *            The {@link DoublePredicate} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static final <KEY> DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return new JCacheBasedDoublePredicateMemoizer<>(cache, keyFunction, doublePredicate);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleSupplier
     *            The {@link DoubleSupplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static final DoubleSupplier doubleSupplier(final DoubleSupplier doubleSupplier) {
        return doubleSupplier(doubleSupplier, createCache(DoubleSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleSupplier
     *            The {@link DoubleSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static final DoubleSupplier doubleSupplier(
            final DoubleSupplier doubleSupplier,
            final Cache<String, Double> cache) {
        return doubleSupplier(doubleSupplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a JCache {@link Cache}.
     * </p>
     * BooleanSupplier
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleSupplier
     *            The {@link DoubleSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static final <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier doubleSupplier,
            final Supplier<KEY> keySupplier) {
        return doubleSupplier(doubleSupplier, keySupplier, createCache(DoubleSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleSupplier
     *            The {@link DoubleSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static final <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier doubleSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Double> cache) {
        return new JCacheBasedDoubleSupplierMemoizer<>(cache, keySupplier, doubleSupplier);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToIntFunction
     *            The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static final DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction doubleToIntFunction) {
        return doubleToIntFunction(doubleToIntFunction, createCache(DoubleToIntFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToIntFunction
     *            The {@link DoubleToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static final DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction,
            final Cache<Double, Integer> cache) {
        return doubleToIntFunction(doubleToIntFunction, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleToIntFunction
     *            The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static final <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction,
            final DoubleFunction<KEY> keyFunction) {
        return doubleToIntFunction(doubleToIntFunction, keyFunction, createCache(DoubleToIntFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleToIntFunction
     *            The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static final <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return new JCacheBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, doubleToIntFunction);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToLongFunction
     *            The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static final DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction doubleToLongFunction) {
        return doubleToLongFunction(doubleToLongFunction, createCache(DoubleToLongFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToLongFunction
     *            The {@link DoubleToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static final DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction,
            final Cache<Double, Long> cache) {
        return doubleToLongFunction(doubleToLongFunction, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleToLongFunction
     *            The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static final <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction,
            final DoubleFunction<KEY> keyFunction) {
        return doubleToLongFunction(doubleToLongFunction, keyFunction, createCache(DoubleToLongFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleToLongFunction
     *            The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static final <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return new JCacheBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, doubleToLongFunction);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleUnaryOperator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static final DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator doubleUnaryOperator) {
        return doubleUnaryOperator(doubleUnaryOperator, createCache(DoubleUnaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleUnaryOperator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static final DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator,
            final Cache<Double, Double> cache) {
        return doubleUnaryOperator(doubleUnaryOperator, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleUnaryOperator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static final <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator,
            final DoubleFunction<KEY> keyFunction) {
        return doubleUnaryOperator(doubleUnaryOperator, keyFunction, createCache(DoubleUnaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param doubleUnaryOperator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction
     *            The {@link DoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static final <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return new JCacheBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, doubleUnaryOperator);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a JCache {@link Cache}.
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
    public static final <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return function(function, createCache(Function.class));
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a JCache {@link Cache}.
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
     * Memoizes a {@link Function} in a JCache {@link Cache}.
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
    public static final <INPUT, OUTPUT, KEY> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return function(function, keyFunction, createCache(Function.class));
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a JCache {@link Cache}.
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
    public static final <INPUT, OUTPUT, KEY> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new JCacheBasedFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intBinaryOperator
     *            The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static final IntBinaryOperator intBinaryOperator(final IntBinaryOperator intBinaryOperator) {
        return intBinaryOperator(intBinaryOperator, createCache(IntBinaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intBinaryOperator
     *            The {@link IntBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static final IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator,
            final Cache<String, Integer> cache) {
        return intBinaryOperator(intBinaryOperator, intBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intBinaryOperator
     *            The {@link IntBinaryOperator} to memoize.
     * @param keyFunction
     *            The {@link IntBinaryFunction} to compute the cache key.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static final <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator,
            final IntBinaryFunction<KEY> keyFunction) {
        return intBinaryOperator(intBinaryOperator, keyFunction, createCache(IntBinaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intBinaryOperator
     *            The {@link IntBinaryOperator} to memoize.
     * @param keyFunction
     *            The {@link IntBinaryFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static final <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator,
            final IntBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return new JCacheBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, intBinaryOperator);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intConsumer
     *            The {@link IntConsumer} to memoize.
     * @return The wrapped {@link IntConsumer}.
     */
    public static final IntConsumer intConsumer(final IntConsumer intConsumer) {
        return intConsumer(intConsumer, createCache(IntConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intConsumer
     *            The {@link IntConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static final IntConsumer intConsumer(
            final IntConsumer intConsumer,
            final Cache<Integer, Integer> cache) {
        return intConsumer(intConsumer, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intConsumer
     *            The {@link IntConsumer} to memoize.
     * @param keyFunction
     *            The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntConsumer}.
     */
    public static final <KEY> IntConsumer intConsumer(
            final IntConsumer intConsumer,
            final IntFunction<KEY> keyFunction) {
        return intConsumer(intConsumer, keyFunction, createCache(IntConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intConsumer
     *            The {@link IntConsumer} to memoize.
     * @param keyFunction
     *            The {@link IntFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static final <KEY> IntConsumer intConsumer(
            final IntConsumer intConsumer,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return new JCacheBasedIntConsumerMemoizer<>(cache, keyFunction, intConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return intFunction(function, createCache(IntFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a JCache {@link Cache}.
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
     * Memoizes a {@link IntFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT, KEY> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction) {
        return intFunction(function, keyFunction, createCache(IntFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT, KEY> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new JCacheBasedIntFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intPredicate
     *            The {@link IntPredicate} to memoize.
     * @return The wrapped {@link IntPredicate}.
     */
    public static final IntPredicate intPredicate(final IntPredicate intPredicate) {
        return intPredicate(intPredicate, createCache(IntPredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intPredicate
     *            The {@link IntPredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static final IntPredicate intPredicate(
            final IntPredicate intPredicate,
            final Cache<Integer, Boolean> cache) {
        return intPredicate(intPredicate, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intPredicate
     *            The {@link IntPredicate} to memoize.
     * @param keyFunction
     *            The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntPredicate}.
     */
    public static final <KEY> IntPredicate intPredicate(
            final IntPredicate intPredicate,
            final IntFunction<KEY> keyFunction) {
        return intPredicate(intPredicate, keyFunction, createCache(IntPredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intPredicate
     *            The {@link IntPredicate} to memoize.
     * @param keyFunction
     *            The {@link IntFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static final <KEY> IntPredicate intPredicate(
            final IntPredicate intPredicate,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return new JCacheBasedIntPredicateMemoizer<>(cache, keyFunction, intPredicate);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intSupplier
     *            The {@link IntSupplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    public static final IntSupplier intSupplier(final IntSupplier intSupplier) {
        return intSupplier(intSupplier, createCache(IntSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intSupplier
     *            The {@link IntSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static final IntSupplier intSupplier(
            final IntSupplier intSupplier,
            final Cache<String, Integer> cache) {
        return intSupplier(intSupplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a JCache {@link Cache}.
     * </p>
     * BooleanSupplier
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intSupplier
     *            The {@link IntSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link IntSupplier}.
     */
    public static final <KEY> IntSupplier intSupplier(
            final IntSupplier intSupplier,
            final Supplier<KEY> keySupplier) {
        return intSupplier(intSupplier, keySupplier, createCache(IntSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param intSupplier
     *            The {@link IntSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static final <KEY> IntSupplier intSupplier(
            final IntSupplier intSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Integer> cache) {
        return new JCacheBasedIntSupplierMemoizer<>(cache, keySupplier, intSupplier);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longBinaryOperator
     *            The {@link LongBinaryOperator} to memoize.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static final LongBinaryOperator longBinaryOperator(final LongBinaryOperator longBinaryOperator) {
        return longBinaryOperator(longBinaryOperator, createCache(LongBinaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longBinaryOperator
     *            The {@link LongBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static final LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator,
            final Cache<String, Long> cache) {
        return longBinaryOperator(longBinaryOperator, longBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longBinaryOperator
     *            The {@link LongBinaryOperator} to memoize.
     * @param keyFunction
     *            The {@link LongBinaryFunction} to compute the cache key.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static final <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator,
            final LongBinaryFunction<KEY> keyFunction) {
        return longBinaryOperator(longBinaryOperator, keyFunction, createCache(LongBinaryOperator.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longBinaryOperator
     *            The {@link LongBinaryOperator} to memoize.
     * @param keyFunction
     *            The {@link LongBinaryFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static final <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator,
            final LongBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return new JCacheBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, longBinaryOperator);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longConsumer
     *            The {@link LongConsumer} to memoize.
     * @return The wrapped {@link LongConsumer}.
     */
    public static final LongConsumer longConsumer(final LongConsumer longConsumer) {
        return longConsumer(longConsumer, createCache(LongConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longConsumer
     *            The {@link LongConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static final LongConsumer longConsumer(
            final LongConsumer longConsumer,
            final Cache<Long, Long> cache) {
        return longConsumer(longConsumer, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longConsumer
     *            The {@link LongConsumer} to memoize.
     * @param keyFunction
     *            The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongConsumer}.
     */
    public static final <KEY> LongConsumer longConsumer(
            final LongConsumer longConsumer,
            final LongFunction<KEY> keyFunction) {
        return longConsumer(longConsumer, keyFunction, createCache(LongConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longConsumer
     *            The {@link LongConsumer} to memoize.
     * @param keyFunction
     *            The {@link LongFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static final <KEY> LongConsumer longConsumer(
            final LongConsumer longConsumer,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return new JCacheBasedLongConsumerMemoizer<>(cache, keyFunction, longConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return longFunction(function, createCache(LongFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a JCache {@link Cache}.
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
     * Memoizes a {@link LongFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT, KEY> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction) {
        return longFunction(function, keyFunction, createCache(LongFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a JCache {@link Cache}.
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
    public static final <OUTPUT, KEY> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return new JCacheBasedLongFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longPredicate
     *            The {@link LongPredicate} to memoize.
     * @return The wrapped {@link LongPredicate}.
     */
    public static final LongPredicate longPredicate(final LongPredicate longPredicate) {
        return longPredicate(longPredicate, createCache(LongPredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longPredicate
     *            The {@link LongPredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static final LongPredicate longPredicate(
            final LongPredicate longPredicate,
            final Cache<Long, Boolean> cache) {
        return longPredicate(longPredicate, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longPredicate
     *            The {@link LongPredicate} to memoize.
     * @param keyFunction
     *            The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongPredicate}.
     */
    public static final <KEY> LongPredicate longPredicate(
            final LongPredicate longPredicate,
            final LongFunction<KEY> keyFunction) {
        return longPredicate(longPredicate, keyFunction, createCache(LongPredicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longPredicate
     *            The {@link LongPredicate} to memoize.
     * @param keyFunction
     *            The {@link LongFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static final <KEY> LongPredicate longPredicate(
            final LongPredicate longPredicate,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return new JCacheBasedLongPredicateMemoizer<>(cache, keyFunction, longPredicate);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longSupplier
     *            The {@link LongSupplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    public static final LongSupplier longSupplier(final LongSupplier longSupplier) {
        return longSupplier(longSupplier, createCache(LongSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longSupplier
     *            The {@link LongSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static final LongSupplier longSupplier(
            final LongSupplier longSupplier,
            final Cache<String, Long> cache) {
        return longSupplier(longSupplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a JCache {@link Cache}.
     * </p>
     * BooleanSupplier
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longSupplier
     *            The {@link LongSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link LongSupplier}.
     */
    public static final <KEY> LongSupplier longSupplier(
            final LongSupplier longSupplier,
            final Supplier<KEY> keySupplier) {
        return longSupplier(longSupplier, keySupplier, createCache(LongSupplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param longSupplier
     *            The {@link LongSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static final <KEY> LongSupplier longSupplier(
            final LongSupplier longSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Long> cache) {
        return new JCacheBasedLongSupplierMemoizer<>(cache, keySupplier, longSupplier);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param objDoubleConsumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static final <FIRST> ObjDoubleConsumer<FIRST> objDoubleConsumer(
            final ObjDoubleConsumer<FIRST> objDoubleConsumer) {
        return objDoubleConsumer(objDoubleConsumer, createCache(ObjDoubleConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param objDoubleConsumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction
     *            The {@link ObjDoubleFunction} to compute the cache key.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static final <FIRST, KEY> ObjDoubleConsumer<FIRST> objDoubleConsumer(
            final ObjDoubleConsumer<FIRST> objDoubleConsumer,
            final ObjDoubleFunction<FIRST, KEY> keyFunction) {
        return objDoubleConsumer(objDoubleConsumer, keyFunction, createCache(ObjDoubleConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param objDoubleConsumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction
     *            The {@link ObjDoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static final <FIRST, KEY> ObjDoubleConsumer<FIRST> objDoubleConsumer(
            final ObjDoubleConsumer<FIRST> objDoubleConsumer,
            final ObjDoubleFunction<FIRST, KEY> keyFunction,
            final Cache<KEY, KEY> cache) {
        return new JCacheBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, objDoubleConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param objDoubleConsumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static final <FIRST> ObjDoubleConsumer<FIRST> objDoubleConsumer(
            final ObjDoubleConsumer<FIRST> objDoubleConsumer,
            final Cache<String, String> cache) {
        return objDoubleConsumer(objDoubleConsumer, objDoubleConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param objIntConsumer
     *            The {@link ObjIntConsumer} to memoize.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static final <FIRST> ObjIntConsumer<FIRST> objIntConsumer(
            final ObjIntConsumer<FIRST> objIntConsumer) {
        return objIntConsumer(objIntConsumer, createCache(ObjIntConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param objIntConsumer
     *            The {@link ObjIntConsumer} to memoize.
     * @param keyFunction
     *            The {@link ObjIntFunction} to compute the cache key.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static final <FIRST, KEY> ObjIntConsumer<FIRST> objIntConsumer(
            final ObjIntConsumer<FIRST> objIntConsumer,
            final ObjIntFunction<FIRST, KEY> keyFunction) {
        return objIntConsumer(objIntConsumer, keyFunction, createCache(ObjIntConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param objIntConsumer
     *            The {@link ObjIntConsumer} to memoize.
     * @param keyFunction
     *            The {@link ObjIntFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static final <FIRST, KEY> ObjIntConsumer<FIRST> objIntConsumer(
            final ObjIntConsumer<FIRST> objIntConsumer,
            final ObjIntFunction<FIRST, KEY> keyFunction,
            final Cache<KEY, KEY> cache) {
        return new JCacheBasedObjIntConsumerMemoizer<>(cache, keyFunction, objIntConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param objIntConsumer
     *            The {@link ObjIntConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static final <FIRST> ObjIntConsumer<FIRST> objIntConsumer(
            final ObjIntConsumer<FIRST> objIntConsumer,
            final Cache<String, String> cache) {
        return objIntConsumer(objIntConsumer, objIntConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param objLongConsumer
     *            The {@link ObjLongConsumer} to memoize.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static final <FIRST> ObjLongConsumer<FIRST> objLongConsumer(
            final ObjLongConsumer<FIRST> objLongConsumer) {
        return objLongConsumer(objLongConsumer, createCache(ObjLongConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param objLongConsumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction
     *            The {@link ObjDoubleFunction} to compute the cache key.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static final <FIRST, KEY> ObjLongConsumer<FIRST> objLongConsumer(
            final ObjLongConsumer<FIRST> objLongConsumer,
            final ObjLongFunction<FIRST, KEY> keyFunction) {
        return objLongConsumer(objLongConsumer, keyFunction, createCache(ObjLongConsumer.class));
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param objLongConsumer
     *            The {@link ObjLongConsumer} to memoize.
     * @param keyFunction
     *            The {@link ObjDoubleFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static final <FIRST, KEY> ObjLongConsumer<FIRST> objLongConsumer(
            final ObjLongConsumer<FIRST> objLongConsumer,
            final ObjLongFunction<FIRST, KEY> keyFunction,
            final Cache<KEY, KEY> cache) {
        return new JCacheBasedObjLongConsumerMemoizer<>(cache, keyFunction, objLongConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param objLongConsumer
     *            The {@link ObjLongConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static final <FIRST> ObjLongConsumer<FIRST> objLongConsumer(
            final ObjLongConsumer<FIRST> objLongConsumer,
            final Cache<String, String> cache) {
        return objLongConsumer(objLongConsumer, objLongConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a JCache {@link Cache}.
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
    public static final <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return predicate(predicate, createCache(Predicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a JCache {@link Cache}.
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
        return predicate(predicate, Function.identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a JCache {@link Cache}.
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
        return predicate(predicate, keyFunction, createCache(Predicate.class));
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a JCache {@link Cache}.
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
        return new JCacheBasedPredicateMemoizer<>(cache, keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a JCache {@link Cache}.
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
        return supplier(supplier, createCache(Supplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a JCache {@link Cache}.
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
     * Memoizes a {@link Supplier} in a JCache {@link Cache}.
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
        return supplier(supplier, keySupplier, createCache(Supplier.class));
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a JCache {@link Cache}.
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
        return new JCacheBasedSupplierMemoizer<>(cache, keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toDoubleBiFunction
     *            The {@link ToDoubleBiFunction} to memoize.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static final <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction) {
        return toDoubleBiFunction(toDoubleBiFunction, createCache(ToDoubleBiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toDoubleBiFunction
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static final <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toDoubleBiFunction(toDoubleBiFunction, keyFunction, createCache(ToDoubleBiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toDoubleBiFunction
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static final <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return new JCacheBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, toDoubleBiFunction);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toDoubleBiFunction
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static final <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction,
            final Cache<String, Double> cache) {
        return toDoubleBiFunction(toDoubleBiFunction, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toDoubleFunction
     *            The {@link ToDoubleFunction} to memoize.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static final <FIRST> ToDoubleFunction<FIRST> toDoubleFunction(
            final ToDoubleFunction<FIRST> toDoubleFunction) {
        return toDoubleFunction(toDoubleFunction, createCache(ToDoubleFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toDoubleFunction
     *            The {@link ToDoubleFunction} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static final <FIRST, KEY> ToDoubleFunction<FIRST> toDoubleFunction(
            final ToDoubleFunction<FIRST> toDoubleFunction,
            final Function<FIRST, KEY> keyFunction) {
        return toDoubleFunction(toDoubleFunction, keyFunction, createCache(ToDoubleFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toDoubleFunction
     *            The {@link ToDoubleFunction} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static final <FIRST, KEY> ToDoubleFunction<FIRST> toDoubleFunction(
            final ToDoubleFunction<FIRST> toDoubleFunction,
            final Function<FIRST, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return new JCacheBasedToDoubleFunctionMemoizer<>(cache, keyFunction, toDoubleFunction);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toDoubleFunction
     *            The {@link ToDoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static final <FIRST> ToDoubleFunction<FIRST> toDoubleFunction(
            final ToDoubleFunction<FIRST> toDoubleFunction,
            final Cache<FIRST, Double> cache) {
        return toDoubleFunction(toDoubleFunction, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toIntBiFunction
     *            The {@link ToIntBiFunction} to memoize.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static final <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction) {
        return toIntBiFunction(toIntBiFunction, createCache(ToIntBiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toIntBiFunction
     *            The {@link ToIntBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static final <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toIntBiFunction(toIntBiFunction, keyFunction, createCache(ToIntBiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toIntBiFunction
     *            The {@link ToIntBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static final <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return new JCacheBasedToIntBiFunctionMemoizer<>(cache, keyFunction, toIntBiFunction);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toIntBiFunction
     *            The {@link ToIntBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static final <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction,
            final Cache<String, Integer> cache) {
        return toIntBiFunction(toIntBiFunction, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toIntFunction
     *            The {@link ToIntFunction} to memoize.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static final <FIRST> ToIntFunction<FIRST> toIntFunction(
            final ToIntFunction<FIRST> toIntFunction) {
        return toIntFunction(toIntFunction, createCache(ToIntFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toIntFunction
     *            The {@link ToIntFunction} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static final <FIRST, KEY> ToIntFunction<FIRST> toIntFunction(
            final ToIntFunction<FIRST> toIntFunction,
            final Function<FIRST, KEY> keyFunction) {
        return toIntFunction(toIntFunction, keyFunction, createCache(ToIntFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toIntFunction
     *            The {@link ToIntFunction} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static final <FIRST, KEY> ToIntFunction<FIRST> toIntFunction(
            final ToIntFunction<FIRST> toIntFunction,
            final Function<FIRST, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return new JCacheBasedToIntFunctionMemoizer<>(cache, keyFunction, toIntFunction);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toIntFunction
     *            The {@link ToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static final <FIRST> ToIntFunction<FIRST> toIntFunction(
            final ToIntFunction<FIRST> toIntFunction,
            final Cache<FIRST, Integer> cache) {
        return toIntFunction(toIntFunction, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toLongBiFunction
     *            The {@link ToLongBiFunction} to memoize.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static final <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction) {
        return toLongBiFunction(toLongBiFunction, createCache(ToLongBiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toLongBiFunction
     *            The {@link ToLongBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static final <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toLongBiFunction(toLongBiFunction, keyFunction, createCache(ToLongBiFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toLongBiFunction
     *            The {@link ToLongBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static final <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return new JCacheBasedToLongBiFunctionMemoizer<>(cache, keyFunction, toLongBiFunction);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toLongBiFunction
     *            The {@link ToLongBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static final <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction,
            final Cache<String, Long> cache) {
        return toLongBiFunction(toLongBiFunction, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toLongFunction
     *            The {@link ToLongFunction} to memoize.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static final <FIRST> ToLongFunction<FIRST> toLongFunction(
            final ToLongFunction<FIRST> toLongFunction) {
        return toLongFunction(toLongFunction, createCache(ToLongFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toLongFunction
     *            The {@link ToLongFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static final <FIRST, KEY> ToLongFunction<FIRST> toLongFunction(
            final ToLongFunction<FIRST> toLongFunction,
            final Function<FIRST, KEY> keyFunction) {
        return toLongFunction(toLongFunction, keyFunction, createCache(ToLongFunction.class));
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param toLongFunction
     *            The {@link ToLongFunction} to memoize.
     * @param keyFunction
     *            The {@link Function} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static final <FIRST, KEY> ToLongFunction<FIRST> toLongFunction(
            final ToLongFunction<FIRST> toLongFunction,
            final Function<FIRST, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return new JCacheBasedToLongFunctionMemoizer<>(cache, keyFunction, toLongFunction);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a JCache {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param toLongFunction
     *            The {@link ToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static final <FIRST> ToLongFunction<FIRST> toLongFunction(
            final ToLongFunction<FIRST> toLongFunction,
            final Cache<FIRST, Long> cache) {
        return toLongFunction(toLongFunction, identity(), cache);
    }

    static <KEY, VALUE> Cache<KEY, VALUE> createCache(final Type type) {
        final MutableConfiguration<KEY, VALUE> configuration = new MutableConfiguration<>();
        return CACHE_MANAGER.createCache(generateCacheName(type), configuration);
    }

    static String generateCacheName(final Type type) {
        return JCacheMemoize.class.getSimpleName() + type.getTypeName() + CACHE_COUNTER.getAndIncrement();
    }

}
