/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.cache2k;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
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
public final class Cache2kMemoize {

    private Cache2kMemoize() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a cache2k {@link Cache}.
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
        return biConsumer(biConsumer, Cache2kBuilder.of(String.class, String.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return biConsumer(biConsumer, keyFunction, (Cache<KEY, KEY>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a cache2k {@link Cache}.
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
     * Memoizes a {@link BiConsumer} in a cache2k {@link Cache}.
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
     * Memoizes a {@link BiFunction} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(String.class, Object.class).build();
        return biFunction(biFunction, (Cache<String, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return biFunction(biFunction, keyFunction, (Cache<KEY, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a cache2k {@link Cache}.
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
     * Memoizes a {@link BiFunction} in a cache2k {@link Cache}.
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
     * Memoizes a {@link BiPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>   The type of the first parameter.
     * @param <SECOND>  The type of the second parameter.
     * @param predicate The {@link BiPredicate} to memoize.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate) {
        return biPredicate(predicate, Cache2kBuilder.of(String.class, Boolean.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link BiPredicate} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return biPredicate(predicate, keyFunction, (Cache<KEY, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>   The type of the first parameter.
     * @param <SECOND>  The type of the second parameter.
     * @param predicate The {@link BiPredicate} to memoize.
     * @param cache     The {@link Cache} to use.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final Cache<String, Boolean> cache) {
        return biPredicate(predicate, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link BiPredicate} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link BooleanSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link BooleanSupplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return booleanSupplier(supplier, Cache2kBuilder.of(String.class, Boolean.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link BooleanSupplier} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Cache<String, Boolean> cache) {
        return booleanSupplier(supplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link BooleanSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier) {
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return booleanSupplier(supplier, keySupplier, (Cache<KEY, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link BooleanSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link Consumer} in a cache2k {@link Cache}.
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
    public static <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return consumer(consumer, (Cache<INPUT, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a cache2k {@link Cache}.
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
     * Memoizes a {@link Consumer} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return consumer(consumer, keyFunction, (Cache<KEY, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a cache2k {@link Cache}.
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
     * Memoizes a {@link DoubleBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return doubleBinaryOperator(operator, Cache2kBuilder.of(String.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleBinaryOperator} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final Cache<String, Double> cache) {
        return doubleBinaryOperator(operator, MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction The {@link DoubleBinaryFunction} to compute the cache key.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final DoubleBinaryFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return doubleBinaryOperator(operator, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction The {@link DoubleBinaryFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final DoubleBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleBinaryOperator(operator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link DoubleConsumer} to memoize.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return doubleConsumer(consumer, Cache2kBuilder.of(Double.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link DoubleConsumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final Cache<Double, Double> cache) {
        return doubleConsumer(consumer, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link DoubleConsumer} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final DoubleFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return doubleConsumer(consumer, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link DoubleConsumer} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleConsumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a cache2k {@link Cache}.
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
    public static <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        final var cache = Cache2kBuilder.of(Double.class, Object.class).build();
        return doubleFunction(function, (Cache<Double, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a cache2k {@link Cache}.
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
     * Memoizes a {@link DoubleFunction} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return doubleFunction(function, keyFunction, (Cache<KEY, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a cache2k {@link Cache}.
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
     * Memoizes a {@link DoublePredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link DoublePredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return doublePredicate(predicate, Cache2kBuilder.of(Double.class, Boolean.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link DoublePredicate} to memoize.
     * @param cache     The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final Cache<Double, Boolean> cache) {
        return doublePredicate(predicate, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link DoublePredicate} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final DoubleFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return doublePredicate(predicate, keyFunction, (Cache<KEY, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link DoublePredicate} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.doublePredicate(predicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link DoubleSupplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return doubleSupplier(supplier, Cache2kBuilder.of(String.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link DoubleSupplier} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Cache<String, Double> cache) {
        return doubleSupplier(supplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link DoubleSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return doubleSupplier(supplier, keySupplier, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link DoubleSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link DoubleToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return doubleToIntFunction(function, Cache2kBuilder.of(Double.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToIntFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final Cache<Double, Integer> cache) {
        return doubleToIntFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final DoubleFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return doubleToIntFunction(function, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.doubleToIntFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return doubleToLongFunction(function, Cache2kBuilder.of(Double.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToLongFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final Cache<Double, Long> cache) {
        return doubleToLongFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final DoubleFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return doubleToLongFunction(function, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.doubleToLongFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return doubleUnaryOperator(operator, Cache2kBuilder.of(Double.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleUnaryOperator} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final Cache<Double, Double> cache) {
        return doubleUnaryOperator(operator, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final DoubleFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return doubleUnaryOperator(operator, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final DoubleFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.doubleUnaryOperator(operator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a cache2k {@link Cache}.
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
    public static <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return function(function, (Cache<INPUT, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a cache2k {@link Cache}.
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
     * Memoizes a {@link Function} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return function(function, keyFunction, (Cache<KEY, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a cache2k {@link Cache}.
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
     * Memoizes a {@link IntBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return intBinaryOperator(operator, Cache2kBuilder.of(String.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntBinaryOperator} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final Cache<String, Integer> cache) {
        return intBinaryOperator(operator, MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntBinaryOperator} to memoize.
     * @param keyFunction The {@link IntBinaryFunction} to compute the cache key.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final IntBinaryFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return intBinaryOperator(operator, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntBinaryOperator} to memoize.
     * @param keyFunction The {@link IntBinaryFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final IntBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intBinaryOperator(operator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link IntConsumer} to memoize.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(final IntConsumer consumer) {
        return intConsumer(consumer, Cache2kBuilder.of(Integer.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link IntConsumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer intConsumer(
            final IntConsumer consumer,
            final Cache<Integer, Integer> cache) {
        return intConsumer(consumer, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link IntConsumer} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntConsumer}.
     */
    public static <KEY> IntConsumer intConsumer(
            final IntConsumer consumer,
            final IntFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return intConsumer(consumer, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link IntConsumer} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntConsumer}.
     */
    public static <KEY> IntConsumer intConsumer(
            final IntConsumer consumer,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intConsumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a cache2k {@link Cache}.
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
    public static <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        final var cache = Cache2kBuilder.of(Integer.class, Object.class).build();
        return intFunction(function, (Cache<Integer, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a cache2k {@link Cache}.
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
     * Memoizes a {@link IntFunction} in a cache2k {@link Cache}.
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return intFunction(function, keyFunction, (Cache<KEY, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a cache2k {@link Cache}.
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
     * Memoizes a {@link IntPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link IntPredicate} to memoize.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(final IntPredicate predicate) {
        return intPredicate(predicate, Cache2kBuilder.of(Integer.class, Boolean.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link IntPredicate} to memoize.
     * @param cache     The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate intPredicate(
            final IntPredicate predicate,
            final Cache<Integer, Boolean> cache) {
        return intPredicate(predicate, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link IntPredicate} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntPredicate}.
     */
    public static <KEY> IntPredicate intPredicate(
            final IntPredicate predicate,
            final IntFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return intPredicate(predicate, keyFunction, (Cache<KEY, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link IntPredicate} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntPredicate}.
     */
    public static <KEY> IntPredicate intPredicate(
            final IntPredicate predicate,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.intPredicate(predicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link IntSupplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(
            final IntSupplier supplier) {
        return intSupplier(supplier, Cache2kBuilder.of(String.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link IntSupplier} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier intSupplier(
            final IntSupplier supplier,
            final Cache<String, Integer> cache) {
        return intSupplier(supplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link IntSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return intSupplier(supplier, keySupplier, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link IntSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link IntToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToDoubleFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return intToDoubleFunction(function, Cache2kBuilder.of(Integer.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToDoubleFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final Cache<Integer, Double> cache) {
        return intToDoubleFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link IntToDoubleFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final IntFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return intToDoubleFunction(function, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link IntToDoubleFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.intToDoubleFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToLongFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return intToLongFunction(function, Cache2kBuilder.of(Integer.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToLongFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final Cache<Integer, Long> cache) {
        return intToLongFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link IntToLongFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final IntFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return intToLongFunction(function, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link IntToLongFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.intToLongFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntUnaryOperator} to memoize.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return intUnaryOperator(operator, Cache2kBuilder.of(Integer.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntUnaryOperator} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final Cache<Integer, Integer> cache) {
        return intUnaryOperator(operator, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntUnaryOperator} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final IntFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return intUnaryOperator(operator, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntUnaryOperator} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    public static <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final IntFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.intUnaryOperator(operator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongBinaryOperator} to memoize.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return longBinaryOperator(operator, Cache2kBuilder.of(String.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongBinaryOperator} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final Cache<String, Long> cache) {
        return longBinaryOperator(operator, MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongBinaryOperator} to memoize.
     * @param keyFunction The {@link LongBinaryFunction} to compute the cache key.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final LongBinaryFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return longBinaryOperator(operator, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongBinaryOperator} to memoize.
     * @param keyFunction The {@link LongBinaryFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    public static <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final LongBinaryFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longBinaryOperator(operator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link LongConsumer} to memoize.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(final LongConsumer consumer) {
        return longConsumer(consumer, Cache2kBuilder.of(Long.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link LongConsumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer longConsumer(
            final LongConsumer consumer,
            final Cache<Long, Long> cache) {
        return longConsumer(consumer, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link LongConsumer} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongConsumer}.
     */
    public static <KEY> LongConsumer longConsumer(
            final LongConsumer consumer,
            final LongFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return longConsumer(consumer, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link LongConsumer} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongConsumer}.
     */
    public static <KEY> LongConsumer longConsumer(
            final LongConsumer consumer,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longConsumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link LongFunction} to memoize.
     * @return The wrapped {@link LongFunction}.
     */
    public static <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        final var cache = Cache2kBuilder.of(Long.class, Object.class).build();
        return longFunction(function, (Cache<Long, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a cache2k {@link Cache}.
     * </p>
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
     * Memoizes a {@link LongFunction} in a cache2k {@link Cache}.
     * </p>
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return longFunction(function, keyFunction, (Cache<KEY, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a cache2k {@link Cache}.
     * </p>
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
     * Memoizes a {@link LongPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link LongPredicate} to memoize.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(final LongPredicate predicate) {
        return longPredicate(predicate, Cache2kBuilder.of(Long.class, Boolean.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link LongPredicate} to memoize.
     * @param cache     The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate longPredicate(
            final LongPredicate predicate,
            final Cache<Long, Boolean> cache) {
        return longPredicate(predicate, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link LongPredicate} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongPredicate}.
     */
    public static <KEY> LongPredicate longPredicate(
            final LongPredicate predicate,
            final LongFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return longPredicate(predicate, keyFunction, (Cache<KEY, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link LongPredicate} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongPredicate}.
     */
    public static <KEY> LongPredicate longPredicate(
            final LongPredicate predicate,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Boolean> cache) {
        return MapMemoize.longPredicate(predicate, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link LongSupplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(
            final LongSupplier supplier) {
        return longSupplier(supplier, Cache2kBuilder.of(String.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link LongSupplier} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier longSupplier(
            final LongSupplier supplier,
            final Cache<String, Long> cache) {
        return longSupplier(supplier, MemoizationDefaults.defaultKeySupplier(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link LongSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return longSupplier(supplier, keySupplier, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link LongSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link LongToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToDoubleFunction} to memoize.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return longToDoubleFunction(function, Cache2kBuilder.of(Long.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToDoubleFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final Cache<Long, Double> cache) {
        return longToDoubleFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link LongToDoubleFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final LongFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return longToDoubleFunction(function, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link LongToDoubleFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    public static <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.longToDoubleFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToIntFunction} to memoize.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return longToIntFunction(function, Cache2kBuilder.of(Long.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToIntFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final Cache<Long, Integer> cache) {
        return longToIntFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link LongToIntFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final LongFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return longToIntFunction(function, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link LongToIntFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    public static <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.longToIntFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongUnaryOperator} to memoize.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return longUnaryOperator(operator, Cache2kBuilder.of(Long.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongUnaryOperator} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final Cache<Long, Long> cache) {
        return longUnaryOperator(operator, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongUnaryOperator} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final LongFunction<KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return longUnaryOperator(operator, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongUnaryOperator} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    public static <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final LongFunction<KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.longUnaryOperator(operator, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjDoubleConsumer} to memoize.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer) {
        final var cache = Cache2kBuilder.of(String.class, Object.class).build();
        return objDoubleConsumer(consumer, (Cache<String, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjDoubleConsumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer,
            final Cache<String, INPUT> cache) {
        return objDoubleConsumer(consumer, MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction The {@link ObjDoubleFunction} to compute the cache key.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer,
            final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return objDoubleConsumer(consumer, keyFunction, (Cache<KEY, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction The {@link ObjDoubleFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer,
            final ObjDoubleFunction<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.objDoubleConsumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjIntConsumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer,
            final Cache<String, INPUT> cache) {
        return objIntConsumer(consumer, MemoizationDefaults.objIntConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjIntConsumer} to memoize.
     * @param keyFunction The {@link ObjIntFunction} to compute the cache key.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer,
            final ObjIntFunction<INPUT, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return objIntConsumer(consumer, keyFunction, (Cache<KEY, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjIntConsumer} to memoize.
     * @param keyFunction The {@link ObjIntFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer,
            final ObjIntFunction<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.objIntConsumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjIntConsumer} to memoize.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer) {
        final var cache = Cache2kBuilder.of(String.class, Object.class).build();
        return objIntConsumer(consumer, (Cache<String, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjLongConsumer} to memoize.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer) {
        final var cache = Cache2kBuilder.of(String.class, Object.class).build();
        return objLongConsumer(consumer, (Cache<String, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjLongConsumer} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer,
            final Cache<String, INPUT> cache) {
        return objLongConsumer(consumer, MemoizationDefaults.objLongConsumerHashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjLongConsumer} to memoize.
     * @param keyFunction The {@link ObjLongFunction} to compute the cache key.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer,
            final ObjLongFunction<INPUT, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return objLongConsumer(consumer, keyFunction, (Cache<KEY, INPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjLongConsumer} to memoize.
     * @param keyFunction The {@link ObjLongFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer,
            final ObjLongFunction<INPUT, KEY> keyFunction,
            final Cache<KEY, INPUT> cache) {
        return MapMemoize.objLongConsumer(consumer, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>   The type of the input.
     * @param predicate The {@link Predicate} to memoize.
     * @return The wrapped {@link Predicate}.
     */
    public static <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return predicate(predicate, (Cache<INPUT, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a cache2k {@link Cache}.
     * </p>
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
     * Memoizes a {@link Predicate} in a cache2k {@link Cache}.
     * </p>
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
        final var cache = Cache2kBuilder.of(Object.class, Boolean.class).build();
        return predicate(predicate, keyFunction, (Cache<KEY, Boolean>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a cache2k {@link Cache}.
     * </p>
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
     * Memoizes a {@link Supplier} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param supplier The {@link Supplier} to memoize.
     * @return The wrapped {@link Supplier}.
     */
    public static <OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier) {
        final var cache = Cache2kBuilder.of(String.class, Object.class).build();
        return supplier(supplier, (Cache<String, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a cache2k {@link Cache}.
     * </p>
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
     * Memoizes a {@link Supplier} in a cache2k {@link Cache}.
     * </p>
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
        final var cache = Cache2kBuilder.of(Object.class, Object.class).build();
        return supplier(supplier, keySupplier, (Cache<KEY, OUTPUT>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a cache2k {@link Cache}.
     * </p>
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
     * Memoizes a {@link ToDoubleBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToDoubleBiFunction} to memoize.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return toDoubleBiFunction(function, Cache2kBuilder.of(String.class, Double.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return toDoubleBiFunction(function, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link ToDoubleBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToDoubleBiFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final Cache<String, Double> cache) {
        return toDoubleBiFunction(function, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToDoubleFunction} to memoize.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return toDoubleFunction(function, (Cache<INPUT, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToDoubleFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function,
            final Cache<INPUT, Double> cache) {
        return toDoubleFunction(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToDoubleFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Double.class).build();
        return toDoubleFunction(function, keyFunction, (Cache<KEY, Double>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToDoubleFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoize.toDoubleFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToIntBiFunction} to memoize.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return toIntBiFunction(function, Cache2kBuilder.of(String.class, Integer.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToIntBiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return toIntBiFunction(function, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToIntBiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link ToIntBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToIntBiFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final Cache<String, Integer> cache) {
        return toIntBiFunction(function, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToIntFunction} to memoize.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return toIntFunction(function, (Cache<INPUT, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToIntFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function,
            final Cache<INPUT, Integer> cache) {
        return toIntFunction(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToIntFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Integer.class).build();
        return toIntFunction(function, keyFunction, (Cache<KEY, Integer>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToIntFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoize.toIntFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToLongBiFunction} to memoize.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return toLongBiFunction(function, Cache2kBuilder.of(String.class, Long.class).build());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToLongBiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return toLongBiFunction(function, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToLongBiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Cache} to use.
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
     * Memoizes a {@link ToLongBiFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToLongBiFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final Cache<String, Long> cache) {
        return toLongBiFunction(function, MemoizationDefaults.hashCodeKeyFunction(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToLongFunction} to memoize.
     * @param cache    The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function,
            final Cache<INPUT, Long> cache) {
        return toLongFunction(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToLongFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return toLongFunction(function, keyFunction, (Cache<KEY, Long>) cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToLongFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoize.toLongFunction(function, keyFunction, cache.asMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a cache2k {@link Cache}.
     * </p>
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToLongFunction} to memoize.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        final var cache = Cache2kBuilder.of(Object.class, Long.class).build();
        return toLongFunction(function, (Cache<INPUT, Long>) cache);
    }

}
