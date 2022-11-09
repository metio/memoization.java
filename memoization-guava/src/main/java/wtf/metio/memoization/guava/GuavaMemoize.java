/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import wtf.metio.memoization.core.*;
import wtf.metio.memoization.map.MapMemoize;

import java.util.function.*;

import static java.util.function.Function.identity;

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
 * @see IntToDoubleFunction
 * @see IntToLongFunction
 * @see IntUnaryOperator
 * @see LongBinaryOperator
 * @see LongConsumer
 * @see LongFunction
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
public final class GuavaMemoize {

    private GuavaMemoize() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>    The type of the first parameter.
     * @param <SECOND>   The type of the second parameter.
     * @param biConsumer The {@link BiConsumer} to memoize.
     * @return The wrapped {@link BiConsumer}.
     */
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer) {
        return biConsumer(biConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param biConsumer  The {@link BiConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiConsumer}.
     */
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biConsumer(biConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>    The type of the first parameter.
     * @param <SECOND>   The type of the second parameter.
     * @param biConsumer The {@link BiConsumer} to memoize.
     * @param cache      The {@link Cache} to use.
     * @return The wrapped {@link BiConsumer}.
     */
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final Cache<String, String> cache) {
        return biConsumer(biConsumer, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param biConsumer  The {@link BiConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>    The type of the first parameter.
     * @param <SECOND>   The type of the second parameter.
     * @param <OUTPUT>   The type of the output/cache value.
     * @param biFunction The {@link BiFunction} to memoize.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return biFunction(biFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param biFunction  The {@link BiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biFunction(biFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param biFunction  The {@link BiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return MapMemoize.biFunction(biFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>    The type of the first parameter.
     * @param <SECOND>   The type of the second parameter.
     * @param <OUTPUT>   The type of the output/cache value.
     * @param biFunction The {@link BiFunction} to memoize.
     * @param cache      The {@link Cache} to use.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final Cache<String, OUTPUT> cache) {
        return biFunction(biFunction, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param biPredicate The {@link BiPredicate} to memoize.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate) {
        return biPredicate(biPredicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param biPredicate The {@link BiPredicate} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biPredicate(biPredicate, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param biPredicate The {@link BiPredicate} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.biPredicate(biPredicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param biPredicate The {@link BiPredicate} to memoize.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> biPredicate,
            final Cache<String, Boolean> cache) {
        return biPredicate(biPredicate, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param booleanSupplier The {@link BooleanSupplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(final BooleanSupplier booleanSupplier) {
        return booleanSupplier(booleanSupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param booleanSupplier The {@link BooleanSupplier} to memoize.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(
            final BooleanSupplier booleanSupplier,
            final Cache<String, Boolean> cache) {
        return booleanSupplier(booleanSupplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>           The type of the cache key.
     * @param booleanSupplier The {@link BooleanSupplier} to memoize.
     * @param keySupplier     The {@link Supplier} for the cache key.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier booleanSupplier,
            final Supplier<KEY> keySupplier) {
        return booleanSupplier(booleanSupplier, keySupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>           The type of the cache key.
     * @param booleanSupplier The {@link BooleanSupplier} to memoize.
     * @param keySupplier     The {@link Supplier} for the cache key.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier booleanSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.booleanSupplier(booleanSupplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link Consumer} to memoize.
     * @return The wrapped {@link Consumer}.
     */
    public static <INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer) {
        return consumer(consumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link Consumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link Consumer}.
     */
    public static <INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Cache<INPUT, INPUT> cache) {
        return consumer(consumer, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link Consumer} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link Consumer}.
     */
    public static <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction) {
        return consumer(consumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link Consumer} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link Consumer}.
     */
    public static <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.consumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleBinaryOperator The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator) {
        return doubleBinaryOperator(doubleBinaryOperator, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleBinaryOperator The {@link DoubleBinaryOperator} to memoize.
     * @param cache                The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator,
            final Cache<String, Double> cache) {
        return doubleBinaryOperator(doubleBinaryOperator, MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>                The type of the cache key.
     * @param doubleBinaryOperator The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction          The {@link DoubleBinaryFunction} to compute the cache key.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator,
            final DoubleBinaryFunction<KEY> keyFunction) {
        return doubleBinaryOperator(doubleBinaryOperator, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>                The type of the cache key.
     * @param doubleBinaryOperator The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction          The {@link DoubleBinaryFunction} to compute the cache key.
     * @param cache                The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator doubleBinaryOperator,
            final DoubleBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleBinaryOperator(doubleBinaryOperator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleConsumer The {@link DoubleConsumer} to memoize.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer) {
        return doubleConsumer(doubleConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleConsumer The {@link DoubleConsumer} to memoize.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer,
            final Cache<Double, Double> cache) {
        return doubleConsumer(doubleConsumer, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>          The type of the cache key.
     * @param doubleConsumer The {@link DoubleConsumer} to memoize.
     * @param keyFunction    The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer,
            final DoubleFunction<KEY> keyFunction) {
        return doubleConsumer(doubleConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>          The type of the cache key.
     * @param doubleConsumer The {@link DoubleConsumer} to memoize.
     * @param keyFunction    The {@link Function} to compute the cache key.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer doubleConsumer,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleConsumer(doubleConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link DoubleFunction} to memoize.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function) {
        return doubleFunction(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link DoubleFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final Cache<Double, OUTPUT> cache) {
        return doubleFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link DoubleFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction) {
        return doubleFunction(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link DoubleFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoubleFunction}.
     */
    public static <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return MapMemoize.doubleFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doublePredicate The {@link DoublePredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate) {
        return doublePredicate(doublePredicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doublePredicate The {@link DoublePredicate} to memoize.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate,
            final Cache<Double, Boolean> cache) {
        return doublePredicate(doublePredicate, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>           The type of the cache key.
     * @param doublePredicate The {@link DoublePredicate} to memoize.
     * @param keyFunction     The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static <KEY> DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate,
            final DoubleFunction<KEY> keyFunction) {
        return doublePredicate(doublePredicate, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>           The type of the cache key.
     * @param doublePredicate The {@link DoublePredicate} to memoize.
     * @param keyFunction     The {@link DoubleFunction} to compute the cache key.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static <KEY> DoublePredicate doublePredicate(
            final DoublePredicate doublePredicate,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.doublePredicate(doublePredicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleSupplier The {@link DoubleSupplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(final DoubleSupplier doubleSupplier) {
        return doubleSupplier(doubleSupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleSupplier The {@link DoubleSupplier} to memoize.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(
            final DoubleSupplier doubleSupplier,
            final Cache<String, Double> cache) {
        return doubleSupplier(doubleSupplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>          The type of the cache key.
     * @param doubleSupplier The {@link DoubleSupplier} to memoize.
     * @param keySupplier    The {@link Supplier} for the cache key.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier doubleSupplier,
            final Supplier<KEY> keySupplier) {
        return doubleSupplier(doubleSupplier, keySupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>          The type of the cache key.
     * @param doubleSupplier The {@link DoubleSupplier} to memoize.
     * @param keySupplier    The {@link Supplier} for the cache key.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier doubleSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleSupplier(doubleSupplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToIntFunction The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction) {
        return doubleToIntFunction(doubleToIntFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToIntFunction The {@link DoubleToIntFunction} to memoize.
     * @param cache               The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction,
            final Cache<Double, Integer> cache) {
        return doubleToIntFunction(doubleToIntFunction, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>               The type of the cache key.
     * @param doubleToIntFunction The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction         The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction,
            final DoubleFunction<KEY> keyFunction) {
        return doubleToIntFunction(doubleToIntFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>               The type of the cache key.
     * @param doubleToIntFunction The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction         The {@link DoubleFunction} to compute the cache key.
     * @param cache               The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction doubleToIntFunction,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.doubleToIntFunction(doubleToIntFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToLongFunction The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction) {
        return doubleToLongFunction(doubleToLongFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleToLongFunction The {@link DoubleToLongFunction} to memoize.
     * @param cache                The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction,
            final Cache<Double, Long> cache) {
        return doubleToLongFunction(doubleToLongFunction, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>                The type of the cache key.
     * @param doubleToLongFunction The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction          The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction,
            final DoubleFunction<KEY> keyFunction) {
        return doubleToLongFunction(doubleToLongFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>                The type of the cache key.
     * @param doubleToLongFunction The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction          The {@link DoubleFunction} to compute the cache key.
     * @param cache                The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction doubleToLongFunction,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.doubleToLongFunction(doubleToLongFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleUnaryOperator The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator) {
        return doubleUnaryOperator(doubleUnaryOperator, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param doubleUnaryOperator The {@link DoubleUnaryOperator} to memoize.
     * @param cache               The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator,
            final Cache<Double, Double> cache) {
        return doubleUnaryOperator(doubleUnaryOperator, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>               The type of the cache key.
     * @param doubleUnaryOperator The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction         The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator,
            final DoubleFunction<KEY> keyFunction) {
        return doubleUnaryOperator(doubleUnaryOperator, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>               The type of the cache key.
     * @param doubleUnaryOperator The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction         The {@link DoubleFunction} to compute the cache key.
     * @param cache               The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator doubleUnaryOperator,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleUnaryOperator(doubleUnaryOperator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function) {
        return function(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link Function}.
     */
    public static <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Cache<INPUT, OUTPUT> cache) {
        return function(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link Function}.
     */
    public static <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return function(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link Function}.
     */
    public static <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return MapMemoize.function(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intBinaryOperator The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator) {
        return intBinaryOperator(intBinaryOperator, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intBinaryOperator The {@link IntBinaryOperator} to memoize.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator,
            final Cache<String, Integer> cache) {
        return intBinaryOperator(intBinaryOperator, MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param intBinaryOperator The {@link IntBinaryOperator} to memoize.
     * @param keyFunction       The {@link IntBinaryFunction} to compute the cache key.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator,
            final IntBinaryFunction<KEY> keyFunction) {
        return intBinaryOperator(intBinaryOperator, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param intBinaryOperator The {@link IntBinaryOperator} to memoize.
     * @param keyFunction       The {@link IntBinaryFunction} to compute the cache key.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator intBinaryOperator,
            final IntBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intBinaryOperator(intBinaryOperator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intConsumer The {@link IntConsumer} to memoize.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(
            final IntConsumer intConsumer) {
        return intConsumer(intConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intConsumer The {@link IntConsumer} to memoize.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(
            final IntConsumer intConsumer,
            final Cache<Integer, Integer> cache) {
        return intConsumer(intConsumer, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param intConsumer The {@link IntConsumer} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link IntConsumer}.
     */
    public static <KEY> IntConsumer intConsumer(
            final IntConsumer intConsumer,
            final IntFunction<KEY> keyFunction) {
        return intConsumer(intConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param intConsumer The {@link IntConsumer} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static <KEY> IntConsumer intConsumer(
            final IntConsumer intConsumer,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intConsumer(intConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link IntFunction} to memoize.
     * @return The wrapped {@link IntFunction}.
     */
    public static <OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function) {
        return intFunction(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link IntFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntFunction}.
     */
    public static <OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final Cache<Integer, OUTPUT> cache) {
        return intFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link IntFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntFunction}.
     */
    public static <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction) {
        return intFunction(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link IntFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntFunction}.
     */
    public static <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return MapMemoize.intFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intPredicate The {@link IntPredicate} to memoize.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(
            final IntPredicate intPredicate) {
        return intPredicate(intPredicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intPredicate The {@link IntPredicate} to memoize.
     * @param cache        The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(
            final IntPredicate intPredicate,
            final Cache<Integer, Boolean> cache) {
        return intPredicate(intPredicate, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>        The type of the cache key.
     * @param intPredicate The {@link IntPredicate} to memoize.
     * @param keyFunction  The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntPredicate}.
     */
    public static <KEY> IntPredicate intPredicate(
            final IntPredicate intPredicate,
            final IntFunction<KEY> keyFunction) {
        return intPredicate(intPredicate, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>        The type of the cache key.
     * @param intPredicate The {@link IntPredicate} to memoize.
     * @param keyFunction  The {@link IntFunction} to compute the cache key.
     * @param cache        The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static <KEY> IntPredicate intPredicate(
            final IntPredicate intPredicate,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.intPredicate(intPredicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intSupplier The {@link IntSupplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(final IntSupplier intSupplier) {
        return intSupplier(intSupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intSupplier The {@link IntSupplier} to memoize.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(
            final IntSupplier intSupplier,
            final Cache<String, Integer> cache) {
        return intSupplier(intSupplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param intSupplier The {@link IntSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier intSupplier,
            final Supplier<KEY> keySupplier) {
        return intSupplier(intSupplier, keySupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param intSupplier The {@link IntSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier intSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intSupplier(intSupplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intToDoubleFunction The {@link IntToDoubleFunction} to memoize.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction intToDoubleFunction) {
        return intToDoubleFunction(intToDoubleFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intToDoubleFunction The {@link IntToDoubleFunction} to memoize.
     * @param cache               The {@link Cache} to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction intToDoubleFunction,
            final Cache<Integer, Double> cache) {
        return intToDoubleFunction(intToDoubleFunction, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>               The type of the cache key.
     * @param intToDoubleFunction The {@link IntToDoubleFunction} to memoize.
     * @param keyFunction         The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction intToDoubleFunction,
            final IntFunction<KEY> keyFunction) {
        return intToDoubleFunction(intToDoubleFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>               The type of the cache key.
     * @param intToDoubleFunction The {@link IntToDoubleFunction} to memoize.
     * @param keyFunction         The {@link IntFunction} to compute the cache key.
     * @param cache               The {@link Cache} to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction intToDoubleFunction,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.intToDoubleFunction(intToDoubleFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intToLongFunction The {@link IntToLongFunction} to memoize.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction intToLongFunction(
            final IntToLongFunction intToLongFunction) {
        return intToLongFunction(intToLongFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intToLongFunction The {@link IntToLongFunction} to memoize.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction intToLongFunction(
            final IntToLongFunction intToLongFunction,
            final Cache<Integer, Long> cache) {
        return intToLongFunction(intToLongFunction, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param intToLongFunction The {@link IntToLongFunction} to memoize.
     * @param keyFunction       The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction intToLongFunction,
            final IntFunction<KEY> keyFunction) {
        return intToLongFunction(intToLongFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param intToLongFunction The {@link IntToLongFunction} to memoize.
     * @param keyFunction       The {@link IntFunction} to compute the cache key.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction intToLongFunction,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.intToLongFunction(intToLongFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intUnaryOperator The {@link IntUnaryOperator} to memoize.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator intUnaryOperator) {
        return intUnaryOperator(intUnaryOperator, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param intUnaryOperator The {@link IntToLongFunction} to memoize.
     * @param cache            The {@link Cache} to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator intUnaryOperator,
            final Cache<Integer, Integer> cache) {
        return intUnaryOperator(intUnaryOperator, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>            The type of the cache key.
     * @param intUnaryOperator The {@link IntUnaryOperator} to memoize.
     * @param keyFunction      The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator intUnaryOperator,
            final IntFunction<KEY> keyFunction) {
        return intUnaryOperator(intUnaryOperator, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>            The type of the cache key.
     * @param intUnaryOperator The {@link IntUnaryOperator} to memoize.
     * @param keyFunction      The {@link IntFunction} to compute the cache key.
     * @param cache            The {@link Cache} to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator intUnaryOperator,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intUnaryOperator(intUnaryOperator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longBinaryOperator The {@link LongBinaryOperator} to memoize.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator) {
        return longBinaryOperator(longBinaryOperator, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a Guava {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longBinaryOperator The {@link LongBinaryOperator} to memoize.
     * @param cache              The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator,
            final Cache<String, Long> cache) {
        return longBinaryOperator(longBinaryOperator, MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>              The type of the cache key.
     * @param longBinaryOperator The {@link LongBinaryOperator} to memoize.
     * @param keyFunction        The {@link LongBinaryFunction} to compute the cache key.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator,
            final LongBinaryFunction<KEY> keyFunction) {
        return longBinaryOperator(longBinaryOperator, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>              The type of the cache key.
     * @param longBinaryOperator The {@link LongBinaryOperator} to memoize.
     * @param keyFunction        The {@link LongBinaryFunction} to compute the cache key.
     * @param cache              The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator longBinaryOperator,
            final LongBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longBinaryOperator(longBinaryOperator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longConsumer The {@link LongConsumer} to memoize.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(
            final LongConsumer longConsumer) {
        return longConsumer(longConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longConsumer The {@link LongConsumer} to memoize.
     * @param cache        The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(
            final LongConsumer longConsumer,
            final Cache<Long, Long> cache) {
        return longConsumer(longConsumer, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>        The type of the cache key.
     * @param longConsumer The {@link LongConsumer} to memoize.
     * @param keyFunction  The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link LongConsumer}.
     */
    public static <KEY> LongConsumer longConsumer(
            final LongConsumer longConsumer,
            final LongFunction<KEY> keyFunction) {
        return longConsumer(longConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>        The type of the cache key.
     * @param longConsumer The {@link LongConsumer} to memoize.
     * @param keyFunction  The {@link Function} to compute the cache key.
     * @param cache        The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static <KEY> LongConsumer longConsumer(
            final LongConsumer longConsumer,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longConsumer(longConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link LongFunction} to memoize.
     * @return The wrapped {@link LongFunction}.
     */
    public static <OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function) {
        return longFunction(function, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link LongFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongFunction}.
     */
    public static <OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final Cache<Long, OUTPUT> cache) {
        return longFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link LongFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongFunction}.
     */
    public static <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction) {
        return longFunction(function, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link LongFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongFunction}.
     */
    public static <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, OUTPUT> cache) {
        return MapMemoize.longFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longPredicate The {@link LongPredicate} to memoize.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(
            final LongPredicate longPredicate) {
        return longPredicate(longPredicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longPredicate The {@link LongPredicate} to memoize.
     * @param cache         The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(
            final LongPredicate longPredicate,
            final Cache<Long, Boolean> cache) {
        return longPredicate(longPredicate, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>         The type of the cache key.
     * @param longPredicate The {@link LongPredicate} to memoize.
     * @param keyFunction   The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongPredicate}.
     */
    public static <KEY> LongPredicate longPredicate(
            final LongPredicate longPredicate,
            final LongFunction<KEY> keyFunction) {
        return longPredicate(longPredicate, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>         The type of the cache key.
     * @param longPredicate The {@link LongPredicate} to memoize.
     * @param keyFunction   The {@link LongFunction} to compute the cache key.
     * @param cache         The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static <KEY> LongPredicate longPredicate(
            final LongPredicate longPredicate,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.longPredicate(longPredicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longSupplier The {@link LongSupplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(final LongSupplier longSupplier) {
        return longSupplier(longSupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longSupplier The {@link LongSupplier} to memoize.
     * @param cache        The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(
            final LongSupplier longSupplier,
            final Cache<String, Long> cache) {
        return longSupplier(longSupplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>        The type of the cache key.
     * @param longSupplier The {@link LongSupplier} to memoize.
     * @param keySupplier  The {@link Supplier} for the cache key.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier longSupplier,
            final Supplier<KEY> keySupplier) {
        return longSupplier(longSupplier, keySupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>        The type of the cache key.
     * @param longSupplier The {@link LongSupplier} to memoize.
     * @param keySupplier  The {@link Supplier} for the cache key.
     * @param cache        The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier longSupplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longSupplier(longSupplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longToDoubleFunction The {@link LongToDoubleFunction} to memoize.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction longToDoubleFunction) {
        return longToDoubleFunction(longToDoubleFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longToDoubleFunction The {@link LongToDoubleFunction} to memoize.
     * @param cache                The {@link Cache} to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction longToDoubleFunction,
            final Cache<Long, Double> cache) {
        return longToDoubleFunction(longToDoubleFunction, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>                The type of the cache key.
     * @param longToDoubleFunction The {@link LongToDoubleFunction} to memoize.
     * @param keyFunction          The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction longToDoubleFunction,
            final LongFunction<KEY> keyFunction) {
        return longToDoubleFunction(longToDoubleFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>                The type of the cache key.
     * @param longToDoubleFunction The {@link LongToDoubleFunction} to memoize.
     * @param keyFunction          The {@link LongFunction} to compute the cache key.
     * @param cache                The {@link Cache} to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction longToDoubleFunction,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.longToDoubleFunction(longToDoubleFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longToIntFunction The {@link LongToIntFunction} to memoize.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(
            final LongToIntFunction longToIntFunction) {
        return longToIntFunction(longToIntFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longToIntFunction The {@link LongToIntFunction} to memoize.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(
            final LongToIntFunction longToIntFunction,
            final Cache<Long, Integer> cache) {
        return longToIntFunction(longToIntFunction, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param longToIntFunction The {@link LongToIntFunction} to memoize.
     * @param keyFunction       The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction longToIntFunction,
            final LongFunction<KEY> keyFunction) {
        return longToIntFunction(longToIntFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param longToIntFunction The {@link LongToIntFunction} to memoize.
     * @param keyFunction       The {@link LongFunction} to compute the cache key.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction longToIntFunction,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.longToIntFunction(longToIntFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longUnaryOperator The {@link LongUnaryOperator} to memoize.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator longUnaryOperator) {
        return longUnaryOperator(longUnaryOperator, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param longUnaryOperator The {@link LongUnaryOperator} to memoize.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator longUnaryOperator,
            final Cache<Long, Long> cache) {
        return longUnaryOperator(longUnaryOperator, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param longUnaryOperator The {@link LongUnaryOperator} to memoize.
     * @param keyFunction       The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator longUnaryOperator,
            final LongFunction<KEY> keyFunction) {
        return longUnaryOperator(longUnaryOperator, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>             The type of the cache key.
     * @param longUnaryOperator The {@link LongUnaryOperator} to memoize.
     * @param keyFunction       The {@link LongFunction} to compute the cache key.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator longUnaryOperator,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longUnaryOperator(longUnaryOperator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>           The type of the input.
     * @param objDoubleConsumer The {@link ObjDoubleConsumer} to memoize.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> objDoubleConsumer) {
        return objDoubleConsumer(objDoubleConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>           The type of the input.
     * @param <KEY>             The type of the cache key.
     * @param objDoubleConsumer The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction       The {@link ObjDoubleFunction} to compute the cache key.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> objDoubleConsumer,
            final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return objDoubleConsumer(objDoubleConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>           The type of the input.
     * @param <KEY>             The type of the cache key.
     * @param objDoubleConsumer The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction       The {@link ObjDoubleFunction} to compute the cache key.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> objDoubleConsumer,
            final ObjDoubleFunction<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.objDoubleConsumer(objDoubleConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>           The type of the input.
     * @param objDoubleConsumer The {@link ObjDoubleConsumer} to memoize.
     * @param cache             The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> objDoubleConsumer,
            final Cache<String, INPUT> cache) {
        return objDoubleConsumer(objDoubleConsumer, MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param objIntConsumer The {@link ObjIntConsumer} to memoize.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> objIntConsumer) {
        return objIntConsumer(objIntConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param <KEY>          The type of the cache key.
     * @param objIntConsumer The {@link ObjIntConsumer} to memoize.
     * @param keyFunction    The {@link ObjIntFunction} to compute the cache key.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> objIntConsumer,
            final ObjIntFunction<INPUT, KEY> keyFunction) {
        return objIntConsumer(objIntConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param <KEY>          The type of the cache key.
     * @param objIntConsumer The {@link ObjIntConsumer} to memoize.
     * @param keyFunction    The {@link ObjIntFunction} to compute the cache key.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> objIntConsumer,
            final ObjIntFunction<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.objIntConsumer(objIntConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param objIntConsumer The {@link ObjIntConsumer} to memoize.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> objIntConsumer,
            final Cache<String, INPUT> cache) {
        return objIntConsumer(objIntConsumer, MemoizationDefaults.objIntConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>         The type of the input.
     * @param objLongConsumer The {@link ObjLongConsumer} to memoize.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> objLongConsumer) {
        return objLongConsumer(objLongConsumer, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>         The type of the input.
     * @param <KEY>           The type of the cache key.
     * @param objLongConsumer The {@link ObjLongConsumer} to memoize.
     * @param keyFunction     The {@link ObjLongFunction} to compute the cache key.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> objLongConsumer,
            final ObjLongFunction<INPUT, KEY> keyFunction) {
        return objLongConsumer(objLongConsumer, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>         The type of the input.
     * @param <KEY>           The type of the cache key.
     * @param objLongConsumer The {@link ObjLongConsumer} to memoize.
     * @param keyFunction     The {@link ObjLongFunction} to compute the cache key.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> objLongConsumer,
            final ObjLongFunction<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.objLongConsumer(objLongConsumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>         The type of the input.
     * @param objLongConsumer The {@link ObjLongConsumer} to memoize.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> objLongConsumer,
            final Cache<String, INPUT> cache) {
        return objLongConsumer(objLongConsumer, MemoizationDefaults.objLongConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>   The type of the input.
     * @param predicate The {@link Predicate} to memoize.
     * @return The wrapped {@link Predicate}.
     */
    public static <INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate) {
        return predicate(predicate, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>   The type of the input.
     * @param predicate The {@link Predicate} to memoize.
     * @param cache     The {@link Cache} to use.
     * @return The wrapped {@link Predicate}.
     */
    public static <INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Cache<INPUT, Boolean> cache) {
        return predicate(predicate, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link Predicate} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link Predicate}.
     */
    public static <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction) {
        return predicate(predicate, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link Predicate} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link Predicate}.
     */
    public static <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.predicate(predicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param supplier The {@link Supplier} to memoize.
     * @return The wrapped {@link Supplier}.
     */
    public static <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return supplier(supplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param supplier The {@link Supplier} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static <OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Cache<String, OUTPUT> cache) {
        return supplier(supplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param supplier    The {@link Supplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @return The wrapped {@link Supplier}.
     */
    public static <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier) {
        return supplier(supplier, keySupplier, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param supplier    The {@link Supplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link Supplier}.
     */
    public static <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier,
            final Cache<KEY, OUTPUT> cache) {
        return MapMemoize.supplier(supplier, keySupplier, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>            The type of the first parameter.
     * @param <SECOND>           The type of the second parameter.
     * @param toDoubleBiFunction The {@link ToDoubleBiFunction} to memoize.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction) {
        return toDoubleBiFunction(toDoubleBiFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>            The type of the first parameter.
     * @param <SECOND>           The type of the second parameter.
     * @param <KEY>              The type of the cache key.
     * @param toDoubleBiFunction The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction        The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toDoubleBiFunction(toDoubleBiFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>            The type of the first parameter.
     * @param <SECOND>           The type of the second parameter.
     * @param <KEY>              The type of the cache key.
     * @param toDoubleBiFunction The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction        The {@link BiFunction} to compute the cache key.
     * @param cache              The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.toDoubleBiFunction(toDoubleBiFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>            The type of the first parameter.
     * @param <SECOND>           The type of the second parameter.
     * @param toDoubleBiFunction The {@link ToDoubleBiFunction} to memoize.
     * @param cache              The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction,
            final Cache<String, Double> cache) {
        return toDoubleBiFunction(toDoubleBiFunction, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>          The type of the input.
     * @param toDoubleFunction The {@link ToDoubleFunction} to memoize.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> toDoubleFunction) {
        return toDoubleFunction(toDoubleFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>          The type of the input.
     * @param toDoubleFunction The {@link ToDoubleFunction} to memoize.
     * @param cache            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> toDoubleFunction,
            final Cache<INPUT, Double> cache) {
        return toDoubleFunction(toDoubleFunction, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>          The type of the input.
     * @param <KEY>            The type of the cache key.
     * @param toDoubleFunction The {@link ToDoubleFunction} to memoize.
     * @param keyFunction      The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> toDoubleFunction,
            final Function<INPUT, KEY> keyFunction) {
        return toDoubleFunction(toDoubleFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>          The type of the input.
     * @param <KEY>            The type of the cache key.
     * @param toDoubleFunction The {@link ToDoubleFunction} to memoize.
     * @param keyFunction      The {@link Function} to compute the cache key.
     * @param cache            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> toDoubleFunction,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.toDoubleFunction(toDoubleFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>         The type of the first parameter.
     * @param <SECOND>        The type of the second parameter.
     * @param toIntBiFunction The {@link ToIntBiFunction} to memoize.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction) {
        return toIntBiFunction(toIntBiFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>         The type of the first parameter.
     * @param <SECOND>        The type of the second parameter.
     * @param <KEY>           The type of the cache key.
     * @param toIntBiFunction The {@link ToIntBiFunction} to memoize.
     * @param keyFunction     The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toIntBiFunction(toIntBiFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>         The type of the first parameter.
     * @param <SECOND>        The type of the second parameter.
     * @param <KEY>           The type of the cache key.
     * @param toIntBiFunction The {@link ToIntBiFunction} to memoize.
     * @param keyFunction     The {@link BiFunction} to compute the cache key.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.toIntBiFunction(toIntBiFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>         The type of the first parameter.
     * @param <SECOND>        The type of the second parameter.
     * @param toIntBiFunction The {@link ToIntBiFunction} to memoize.
     * @param cache           The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> toIntBiFunction,
            final Cache<String, Integer> cache) {
        return toIntBiFunction(toIntBiFunction, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>       The type of the input.
     * @param toIntFunction The {@link ToIntFunction} to memoize.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> toIntFunction) {
        return toIntFunction(toIntFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>       The type of the input.
     * @param toIntFunction The {@link ToIntFunction} to memoize.
     * @param cache         The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> toIntFunction,
            final Cache<INPUT, Integer> cache) {
        return toIntFunction(toIntFunction, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>       The type of the input.
     * @param <KEY>         The type of the cache key.
     * @param toIntFunction The {@link ToIntFunction} to memoize.
     * @param keyFunction   The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> toIntFunction,
            final Function<INPUT, KEY> keyFunction) {
        return toIntFunction(toIntFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>       The type of the input.
     * @param <KEY>         The type of the cache key.
     * @param toIntFunction The {@link ToIntFunction} to memoize.
     * @param keyFunction   The {@link Function} to compute the cache key.
     * @param cache         The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> toIntFunction,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.toIntFunction(toIntFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>          The type of the first parameter.
     * @param <SECOND>         The type of the second parameter.
     * @param toLongBiFunction The {@link ToLongBiFunction} to memoize.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction) {
        return toLongBiFunction(toLongBiFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>          The type of the first parameter.
     * @param <SECOND>         The type of the second parameter.
     * @param <KEY>            The type of the cache key.
     * @param toLongBiFunction The {@link ToLongBiFunction} to memoize.
     * @param keyFunction      The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toLongBiFunction(toLongBiFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>          The type of the first parameter.
     * @param <SECOND>         The type of the second parameter.
     * @param <KEY>            The type of the cache key.
     * @param toLongBiFunction The {@link ToLongBiFunction} to memoize.
     * @param keyFunction      The {@link BiFunction} to compute the cache key.
     * @param cache            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.toLongBiFunction(toLongBiFunction, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>          The type of the first parameter.
     * @param <SECOND>         The type of the second parameter.
     * @param toLongBiFunction The {@link ToLongBiFunction} to memoize.
     * @param cache            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> toLongBiFunction,
            final Cache<String, Long> cache) {
        return toLongBiFunction(toLongBiFunction, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param toLongFunction The {@link ToLongFunction} to memoize.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> toLongFunction) {
        return toLongFunction(toLongFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param toLongFunction The {@link ToLongFunction} to memoize.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> toLongFunction,
            final Cache<INPUT, Long> cache) {
        return toLongFunction(toLongFunction, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param <KEY>          The type of the cache key.
     * @param toLongFunction The {@link ToLongFunction} to memoize.
     * @param keyFunction    The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> toLongFunction,
            final Function<INPUT, KEY> keyFunction) {
        return toLongFunction(toLongFunction, keyFunction, CacheBuilder.newBuilder().build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a Guava {@link Cache}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>        The type of the input.
     * @param <KEY>          The type of the cache key.
     * @param toLongFunction The {@link ToLongFunction} to memoize.
     * @param keyFunction    The {@link Function} to compute the cache key.
     * @param cache          The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> toLongFunction,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.toLongFunction(toLongFunction, keyFunction, cache.asMap());
    }

}
