/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.caffeine;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import de.xn__ho_hia.memoization.map.MapMemoize;
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
 * @see BiConsumer
 * @see BiFunction
 * @see BiPredicate
 * @see BooleanSupplier
 * @see Consumer
 * @see DoubleBinaryOperator
 * @see DoubleConsumer
 * @see DoublePredicate
 * @see DoubleSupplier
 * @see DoubleToIntFunction
 * @see DoubleToLongFunction
 * @see DoubleUnaryOperator
 * @see Function
 * @see IntBinaryOperator
 * @see IntConsumer
 * @see IntPredicate
 * @see IntSupplier
 * @see IntToDoubleFunction
 * @see IntToLongFunction
 * @see IntUnaryOperator
 * @see LongBinaryOperator
 * @see LongPredicate
 * @see LongSupplier
 * @see LongToDoubleFunction
 * @see LongToIntFunction
 * @see LongUnaryOperator
 * @see ObjDoubleConsumer
 * @see ObjIntConsumer
 * @see ObjLongConsumer
 * @see Predicate
 * @see Supplier
 * @see ToDoubleBiFunction
 * @see ToDoubleFunction
 * @see ToIntBiFunction
 * @see ToIntFunction
 * @see ToLongBiFunction
 * @see ToLongFunction
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class CaffeineMemoize {

    private CaffeineMemoize() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer) {
        return biConsumer(biConsumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biConsumer(biConsumer, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final Cache<String, String> cache) {
        return biConsumer(biConsumer, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, KEY> cache) {
        return MapMemoize.biConsumer(biConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <VALUE> ObjDoubleConsumer<VALUE> objDoubleConsumer(
            final ObjDoubleConsumer<VALUE> consumer) {
        return objDoubleConsumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <VALUE> ObjDoubleConsumer<VALUE> objDoubleConsumer(
            final ObjDoubleConsumer<VALUE> consumer,
            final Cache<String, String> cache) {
        return MapMemoize.objDoubleConsumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link ObjIntConsumer} to memoize.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <VALUE> ObjIntConsumer<VALUE> objIntConsumer(
            final ObjIntConsumer<VALUE> consumer) {
        return objIntConsumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link ObjIntConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <VALUE> ObjIntConsumer<VALUE> objIntConsumer(
            final ObjIntConsumer<VALUE> consumer,
            final Cache<String, String> cache) {
        return MapMemoize.objIntConsumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link ObjLongConsumer} to memoize.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <VALUE> ObjLongConsumer<VALUE> objLongConsumer(
            final ObjLongConsumer<VALUE> consumer) {
        return objLongConsumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link ObjLongConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <VALUE> ObjLongConsumer<VALUE> objLongConsumer(
            final ObjLongConsumer<VALUE> consumer,
            final Cache<String, String> cache) {
        return MapMemoize.objLongConsumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> biFunction(
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        return biFunction(biFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> biFunction(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biFunction(biFunction, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> biFunction(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final Cache<String, VALUE> cache) {
        return biFunction(biFunction, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Caffeine {@link Cache}.
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
    public static <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> biFunction(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, VALUE> cache) {
        return MapMemoize.biFunction(biFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link BiPredicate} to memoize.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate) {
        return biPredicate(predicate, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link BiPredicate} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biPredicate(predicate, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link BiPredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final Cache<String, Boolean> cache) {
        return biPredicate(predicate, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link BiPredicate} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.biPredicate(predicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link BooleanSupplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return booleanSupplier(supplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link BooleanSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Cache<String, Boolean> cache) {
        return booleanSupplier(supplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link BooleanSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return booleanSupplier(supplier, keySupplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link BooleanSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.booleanSupplier(supplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Caffeine {@link Cache}.
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
    public static <VALUE> Consumer<VALUE> consumer(final Consumer<VALUE> consumer) {
        return consumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Caffeine {@link Cache}.
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
    public static <VALUE> Consumer<VALUE> consumer(
            final Consumer<VALUE> consumer,
            final Cache<VALUE, VALUE> cache) {
        return MapMemoize.consumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link DoubleConsumer} to memoize.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return doubleConsumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link DoubleConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final Cache<Double, Double> cache) {
        return MapMemoize.doubleConsumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link IntConsumer} to memoize.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(final IntConsumer consumer) {
        return intConsumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link IntConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(
            final IntConsumer consumer,
            final Cache<Integer, Integer> cache) {
        return MapMemoize.intConsumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link LongConsumer} to memoize.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(final LongConsumer consumer) {
        return longConsumer(consumer, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer
     *            The {@link LongConsumer} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(
            final LongConsumer consumer,
            final Cache<Long, Long> cache) {
        return MapMemoize.longConsumer(consumer, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link DoubleSupplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return doubleSupplier(supplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link DoubleSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Cache<String, Double> cache) {
        return doubleSupplier(supplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link DoubleSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return doubleSupplier(supplier, keySupplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link DoubleSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleSupplier(supplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link IntSupplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(
            final IntSupplier supplier) {
        return intSupplier(supplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link IntSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(
            final IntSupplier supplier,
            final Cache<String, Integer> cache) {
        return intSupplier(supplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link IntSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return intSupplier(supplier, keySupplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link IntSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intSupplier(supplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link LongSupplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(
            final LongSupplier supplier) {
        return longSupplier(supplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link LongSupplier} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(
            final LongSupplier supplier,
            final Cache<String, Long> cache) {
        return longSupplier(supplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link LongSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return longSupplier(supplier, keySupplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link LongSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longSupplier(supplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Caffeine {@link Cache}.
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
    public static <KEY, VALUE> Function<KEY, VALUE> function(final Function<KEY, VALUE> function) {
        return function(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Caffeine {@link Cache}.
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
    public static <KEY, VALUE> Function<KEY, VALUE> function(
            final Function<KEY, VALUE> function,
            final Cache<KEY, VALUE> cache) {
        return MapMemoize.function(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return doubleToIntFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final Cache<Double, Integer> cache) {
        return MapMemoize.doubleToIntFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return doubleToLongFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final Cache<Double, Long> cache) {
        return MapMemoize.doubleToLongFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return doubleUnaryOperator(operator, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final Cache<Double, Double> cache) {
        return MapMemoize.doubleUnaryOperator(operator, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return doubleBinaryOperator(operator, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final Cache<String, Double> cache) {
        return MapMemoize.doubleBinaryOperator(operator, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return intBinaryOperator(operator, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link IntBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final Cache<String, Integer> cache) {
        return MapMemoize.intBinaryOperator(operator, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntToDoubleFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return intToDoubleFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntToDoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final Cache<Integer, Double> cache) {
        return MapMemoize.intToDoubleFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntToLongFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return intToLongFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final Cache<Integer, Long> cache) {
        return MapMemoize.intToLongFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link IntUnaryOperator} to memoize.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return intUnaryOperator(operator, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link IntUnaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final Cache<Integer, Integer> cache) {
        return MapMemoize.intUnaryOperator(operator, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link LongBinaryOperator} to memoize.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return longBinaryOperator(operator, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link LongBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final Cache<String, Long> cache) {
        return MapMemoize.longBinaryOperator(operator, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongToDoubleFunction} to memoize.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return longToDoubleFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongToDoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final Cache<Long, Double> cache) {
        return MapMemoize.longToDoubleFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongToIntFunction} to memoize.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return longToIntFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link LongToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final Cache<Long, Integer> cache) {
        return MapMemoize.longToIntFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link LongUnaryOperator} to memoize.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return longUnaryOperator(operator, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link LongUnaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final Cache<Long, Long> cache) {
        return MapMemoize.longUnaryOperator(operator, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Caffeine {@link Cache}.
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
    public static <VALUE> Predicate<VALUE> predicate(final Predicate<VALUE> predicate) {
        return predicate(predicate, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Caffeine {@link Cache}.
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
    public static <VALUE> Predicate<VALUE> predicate(
            final Predicate<VALUE> predicate,
            final Cache<VALUE, Boolean> cache) {
        return MapMemoize.predicate(predicate, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link LongPredicate} to memoize.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(final LongPredicate predicate) {
        return longPredicate(predicate, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link LongPredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(
            final LongPredicate predicate,
            final Cache<Long, Boolean> cache) {
        return MapMemoize.longPredicate(predicate, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link IntPredicate} to memoize.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(final IntPredicate predicate) {
        return intPredicate(predicate, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link IntPredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(
            final IntPredicate predicate,
            final Cache<Integer, Boolean> cache) {
        return MapMemoize.intPredicate(predicate, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link DoublePredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return doublePredicate(predicate, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link DoublePredicate} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final Cache<Double, Boolean> cache) {
        return MapMemoize.doublePredicate(predicate, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Caffeine {@link Cache}.
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
    public static <VALUE> Supplier<VALUE> supplier(
            final Supplier<VALUE> supplier) {
        return supplier(supplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Caffeine {@link Cache}.
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
    public static <VALUE> Supplier<VALUE> supplier(
            final Supplier<VALUE> supplier,
            final Cache<String, VALUE> cache) {
        return supplier(supplier, defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Caffeine {@link Cache}.
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
    public static <KEY, VALUE> Supplier<VALUE> supplier(
            final Supplier<VALUE> supplier,
            final Supplier<KEY> keySupplier) {
        return supplier(supplier, keySupplier, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Caffeine {@link Cache}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static <KEY, VALUE> Supplier<VALUE> supplier(
            final Supplier<VALUE> supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, VALUE> cache) {
        return MapMemoize.supplier(supplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return toDoubleBiFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toDoubleBiFunction(function, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final Cache<String, Double> cache) {
        return toDoubleBiFunction(function, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.toDoubleBiFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return toIntBiFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toIntBiFunction(function, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final Cache<String, Integer> cache) {
        return toIntBiFunction(function, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.toIntBiFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return toLongBiFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toLongBiFunction(function, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final Cache<String, Long> cache) {
        return toLongBiFunction(function, hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.toLongBiFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToDoubleFunction} to memoize.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <VALUE> ToDoubleFunction<VALUE> toDoubleFunction(final ToDoubleFunction<VALUE> function) {
        return toDoubleFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToDoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <VALUE> ToDoubleFunction<VALUE> toDoubleFunction(
            final ToDoubleFunction<VALUE> function,
            final Cache<VALUE, Double> cache) {
        return MapMemoize.toDoubleFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToIntFunction} to memoize.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <VALUE> ToIntFunction<VALUE> toIntFunction(final ToIntFunction<VALUE> function) {
        return toIntFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <VALUE> ToIntFunction<VALUE> toIntFunction(
            final ToIntFunction<VALUE> function,
            final Cache<VALUE, Integer> cache) {
        return MapMemoize.toIntFunction(function, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToLongFunction} to memoize.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <VALUE> ToLongFunction<VALUE> toLongFunction(final ToLongFunction<VALUE> function) {
        return toLongFunction(function, Caffeine.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a Caffeine {@link Cache}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <VALUE> ToLongFunction<VALUE> toLongFunction(
            final ToLongFunction<VALUE> function,
            final Cache<VALUE, Long> cache) {
        return MapMemoize.toLongFunction(function, cache.asMap());
    }

}
