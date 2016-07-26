/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static de.xn__ho_hia.memoization.map.ConcurrentMaps.asConcurrentMap;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;

import java.util.Map;
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

import de.xn__ho_hia.memoization.shared.MemoizationDefaults;

/**
 * <p>
 * Factory for lightweight wrappers that store the result of a potentially expensive function call. Each method of this
 * class exposes two of the following features:
 * </p>
 * <strong>Default cache</strong>
 * <p>
 * The memoizer uses the default cache of this factory. Current implementation creates a new
 * {@link java.util.concurrent.ConcurrentMap} per memoizer.
 * </p>
 * <strong>Default cache key</strong>
 * <p>
 * The memoizer uses the default {@link BiFunction} or {@link Supplier} to calculate the cache key for each call. Either
 * uses the natural key (e.g. the input itself) or one of the methods in {@link MemoizationDefaults}.
 * </p>
 * <strong>Custom cache</strong>
 * <p>
 * The memoizer uses a user-provided {@link java.util.concurrent.ConcurrentMap} as its cache. It is possible to add
 * values to the cache both before and after the memoizer was created. In case a {@link Map} subtype is provided that is
 * not a subclass of {@link java.util.concurrent.ConcurrentMap} as well, the map entries will be copied to a new
 * {@link java.util.concurrent.ConcurrentHashMap}.
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
 * @see LongConsumer
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
 * @see ToIntBiFunction
 * @see ToLongBiFunction
 * @see ToDoubleFunction
 * @see ToIntFunction
 * @see ToLongFunction
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class MapMemoize {

    private MapMemoize() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
    public static <VALUE> Supplier<VALUE> supplier(final Supplier<VALUE> supplier) {
        return supplier(supplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            {@link Map} of already computed values.
     * @return The wrapped {@link Supplier}.
     */
    public static <VALUE> Supplier<VALUE> supplier(
            final Supplier<VALUE> supplier,
            final Map<String, VALUE> cache) {
        return supplier(supplier, defaultKeySupplier(), asConcurrentMap(cache));
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return supplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache key</li>
     * <li>Custom cache</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param cache
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link Supplier}.
     */
    public static <KEY, VALUE> Supplier<VALUE> supplier(
            final Supplier<VALUE> supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, VALUE> cache) {
        return new ConcurrentMapBasedSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return booleanSupplier(supplier, defaultKeySupplier());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return booleanSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Boolean> cache) {
        return new ConcurrentMapBasedBooleanSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return doubleSupplier(supplier, defaultKeySupplier());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return doubleSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Double> cache) {
        return new ConcurrentMapBasedDoubleSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(final IntSupplier supplier) {
        return intSupplier(supplier, defaultKeySupplier());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return intSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Integer> cache) {
        return new ConcurrentMapBasedIntSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(final LongSupplier supplier) {
        return longSupplier(supplier, defaultKeySupplier());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return longSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Long> cache) {
        return new ConcurrentMapBasedLongSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return function(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link Function}.
     */
    public static <KEY, VALUE> Function<KEY, VALUE> function(
            final Function<KEY, VALUE> function,
            final Map<KEY, VALUE> cache) {
        return new ConcurrentMapBasedFunctionMemoizer<>(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return biFunction(biFunction, hashCodeKeyFunction());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return biFunction(biFunction, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> biFunction(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, VALUE> cache) {
        return new ConcurrentMapBasedBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, biFunction);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return consumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer}.
     */
    public static <VALUE> Consumer<VALUE> consumer(
            final Consumer<VALUE> consumer,
            final Map<VALUE, VALUE> cache) {
        return new ConcurrentMapBasedConsumerMemoizer<>(asConcurrentMap(cache), identity(), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return doubleConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final Map<Double, Double> cache) {
        return new ConcurrentMapBasedDoubleConsumerMemoizer(asConcurrentMap(cache), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return intConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(
            final IntConsumer consumer,
            final Map<Integer, Integer> cache) {
        return new ConcurrentMapBasedIntConsumerMemoizer(asConcurrentMap(cache), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return longConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(
            final LongConsumer consumer,
            final Map<Long, Long> cache) {
        return new ConcurrentMapBasedLongConsumerMemoizer(asConcurrentMap(cache), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
    public static <VALUE> ObjDoubleConsumer<VALUE> objDoubleConsumer(final ObjDoubleConsumer<VALUE> consumer) {
        return objDoubleConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <VALUE> ObjDoubleConsumer<VALUE> objDoubleConsumer(
            final ObjDoubleConsumer<VALUE> consumer,
            final Map<String, String> cache) {
        return new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(asConcurrentMap(cache),
                MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction(), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
    public static <VALUE> ObjIntConsumer<VALUE> objIntConsumer(final ObjIntConsumer<VALUE> consumer) {
        return objIntConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <VALUE> ObjIntConsumer<VALUE> objIntConsumer(
            final ObjIntConsumer<VALUE> consumer,
            final Map<String, String> cache) {
        return new ConcurrentMapBasedObjIntConsumerMemoizer<>(asConcurrentMap(cache),
                MemoizationDefaults.objIntConsumerHashCodeKeyFunction(), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
    public static <VALUE> ObjLongConsumer<VALUE> objLongConsumer(final ObjLongConsumer<VALUE> consumer) {
        return objLongConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <VALUE> ObjLongConsumer<VALUE> objLongConsumer(
            final ObjLongConsumer<VALUE> consumer,
            final Map<String, String> cache) {
        return new ConcurrentMapBasedObjLongConsumerMemoizer<>(asConcurrentMap(cache),
                MemoizationDefaults.objLongConsumerHashCodeKeyFunction(), consumer);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return biConsumer(biConsumer, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link BiConsumer}.
     */
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new ConcurrentMapBasedBiConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, biConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return predicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link Predicate}.
     */
    public static <VALUE> Predicate<VALUE> predicate(
            final Predicate<VALUE> predicate,
            final Map<VALUE, Boolean> cache) {
        return new ConcurrentMapBasedPredicateMemoizer<>(asConcurrentMap(cache), predicate);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return biPredicate(predicate, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Boolean> cache) {
        return new ConcurrentMapBasedBiPredicateMemoizer<>(asConcurrentMap(cache), keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return intPredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(
            final IntPredicate predicate,
            final Map<Integer, Boolean> cache) {
        return new ConcurrentMapBasedIntPredicateMemoizer(asConcurrentMap(cache), predicate);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate
     *            The {@link LongPredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static LongPredicate longPredicate(final LongPredicate predicate) {
        return longPredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(
            final LongPredicate predicate,
            final Map<Long, Boolean> cache) {
        return new ConcurrentMapBasedLongPredicateMemoizer(asConcurrentMap(cache), predicate);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return doublePredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final Map<Double, Boolean> cache) {
        return new ConcurrentMapBasedDoublePredicateMemoizer(asConcurrentMap(cache), predicate);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return doubleBinaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final Map<String, Double> cache) {
        return new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(asConcurrentMap(cache),
                MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction(), operator);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return intBinaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final Map<String, Integer> cache) {
        return new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(asConcurrentMap(cache),
                MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction(), operator);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return longBinaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final Map<String, Long> cache) {
        return new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(asConcurrentMap(cache),
                MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction(), operator);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return doubleUnaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final Map<Double, Double> cache) {
        return new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(asConcurrentMap(cache), operator);
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return intUnaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final Map<Integer, Integer> cache) {
        return new ConcurrentMapBasedIntUnaryOperatorMemoizer(asConcurrentMap(cache), operator);
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return longUnaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final Map<Long, Long> cache) {
        return new ConcurrentMapBasedLongUnaryOperatorMemoizer(asConcurrentMap(cache), operator);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return doubleToIntFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final Map<Double, Integer> cache) {
        return new ConcurrentMapBasedDoubleToIntFunctionMemoizer(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return doubleToLongFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final Map<Double, Long> cache) {
        return new ConcurrentMapBasedDoubleToLongFunctionMemoizer(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntToDoubleFunction} to memoize.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return intToDoubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final Map<Integer, Double> cache) {
        return new ConcurrentMapBasedIntToDoubleFunctionMemoizer(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link IntToLongFunction} to memoize.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return intToLongFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final Map<Integer, Long> cache) {
        return new ConcurrentMapBasedIntToLongFunctionMemoizer(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return longToDoubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final Map<Long, Double> cache) {
        return new ConcurrentMapBasedLongToDoubleFunctionMemoizer(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return longToIntFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final Map<Long, Integer> cache) {
        return new ConcurrentMapBasedLongToIntFunctionMemoizer(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return toDoubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <VALUE> ToDoubleFunction<VALUE> toDoubleFunction(
            final ToDoubleFunction<VALUE> function,
            final Map<VALUE, Double> cache) {
        return new ConcurrentMapBasedToDoubleFunctionMemoizer<>(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return toIntFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <VALUE> ToIntFunction<VALUE> toIntFunction(
            final ToIntFunction<VALUE> function,
            final Map<VALUE, Integer> cache) {
        return new ConcurrentMapBasedToIntFunctionMemoizer<>(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return toLongFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <VALUE> ToLongFunction<VALUE> toLongFunction(
            final ToLongFunction<VALUE> function,
            final Map<VALUE, Long> cache) {
        return new ConcurrentMapBasedToLongFunctionMemoizer<>(asConcurrentMap(cache), function);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return toDoubleBiFunction(function, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     * <h3>Features</h3>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return toIntBiFunction(function, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            {@link Map} of already computed values.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new ConcurrentMapBasedToIntBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
        return toLongBiFunction(function, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     *            The {@link Map} based cache to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new ConcurrentMapBasedToLongBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

}
