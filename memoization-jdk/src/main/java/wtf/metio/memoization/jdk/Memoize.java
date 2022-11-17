/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import wtf.metio.memoization.core.MemoizationDefaults;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.*;

import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;
import static wtf.metio.memoization.core.ConcurrentMaps.asConcurrentMap;
import static wtf.metio.memoization.core.MemoizationDefaults.staticKey;

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
 * @see Callable
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
 * @see Runnable
 * @see Supplier
 * @see ToDoubleBiFunction
 * @see ToIntBiFunction
 * @see ToLongBiFunction
 * @see ToDoubleFunction
 * @see ToIntFunction
 * @see ToLongFunction
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class Memoize {

    private Memoize() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link Callable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output.
     * @param callable The {@link Callable} to memoize.
     * @return The wrapped {@link Callable}.
     */
    @CheckReturnValue
    public static <OUTPUT> Callable<OUTPUT> callable(final Callable<OUTPUT> callable) {
        return callable(callable, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Callable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output.
     * @param callable The {@link Callable} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Callable}.
     */
    @CheckReturnValue
    public static <OUTPUT> Callable<OUTPUT> callable(
            final Callable<OUTPUT> callable,
            final Map<String, OUTPUT> cache) {
        return callable(callable, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Callable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output.
     * @param callable    The {@link Callable} to memoize.
     * @param keySupplier The {@link Supplier} to supply the cache key.
     * @return The wrapped {@link Callable}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Callable<OUTPUT> callable(
            final Callable<OUTPUT> callable,
            final Supplier<KEY> keySupplier) {
        return callable(callable, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Callable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output.
     * @param callable    The {@link Callable} to memoize.
     * @param keySupplier The {@link Supplier} to supply the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Callable}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Callable<OUTPUT> callable(
            final Callable<OUTPUT> callable,
            final Supplier<KEY> keySupplier,
            final Map<KEY, OUTPUT> cache) {
        return new CallableMemoizer<>(asConcurrentMap(cache), keySupplier, callable);
    }

    /**
     * <p>
     * Memoizes a {@link Runnable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param runnable The {@link Runnable} to memoize.
     * @return The wrapped {@link Runnable}.
     */
    @CheckReturnValue
    public static Runnable runnable(final Runnable runnable) {
        return runnable(runnable, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Runnable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param runnable The {@link Runnable} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Runnable}.
     */
    @CheckReturnValue
    public static Runnable runnable(
            final Runnable runnable,
            final Map<String, String> cache) {
        return runnable(runnable, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Runnable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param runnable    The {@link Runnable} to memoize.
     * @param keySupplier The {@link Supplier} to supply the cache key.
     * @return The wrapped {@link Runnable}.
     */
    @CheckReturnValue
    public static <KEY> Runnable runnable(
            final Runnable runnable,
            final Supplier<KEY> keySupplier) {
        return runnable(runnable, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Runnable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param runnable    The {@link Runnable} to memoize.
     * @param keySupplier The {@link Supplier} to supply the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Runnable}.
     */
    @CheckReturnValue
    public static <KEY> Runnable runnable(
            final Runnable runnable,
            final Supplier<KEY> keySupplier,
            final Map<KEY, KEY> cache) {
        return new RunnableMemoizer<>(asConcurrentMap(cache), keySupplier, runnable);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return biConsumer(biConsumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biConsumer(biConsumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>    The type of the first parameter.
     * @param <SECOND>   The type of the second parameter.
     * @param biConsumer The {@link BiConsumer} to memoize.
     * @param cache      The {@link Map} based cache to use.
     * @return The wrapped {@link BiConsumer}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final Map<String, String> cache) {
        return biConsumer(biConsumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link BiConsumer}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new BiConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, biConsumer);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return biFunction(biFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param biFunction  The {@link BiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiFunction}.
     */
    @CheckReturnValue
    public static <KEY, FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biFunction(biFunction, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>    The type of the first parameter.
     * @param <SECOND>   The type of the second parameter.
     * @param <OUTPUT>   The type of the output/cache value.
     * @param biFunction The {@link BiFunction} to memoize.
     * @param cache      The {@link Map} based cache to use.
     * @return The wrapped {@link BiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final Map<String, OUTPUT> cache) {
        return biFunction(biFunction, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param biFunction  The {@link BiFunction} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link BiFunction}.
     */
    @CheckReturnValue
    public static <KEY, FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new BiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, biFunction);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return biPredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param predicate   The {@link BiPredicate} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiPredicate}.
     */
    @CheckReturnValue
    public static <KEY, FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return biPredicate(predicate, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <FIRST>   The type of the first parameter.
     * @param <SECOND>  The type of the second parameter.
     * @param predicate The {@link BiPredicate} to memoize.
     * @param cache     The {@link Map} based cache to use.
     * @return The wrapped {@link BiPredicate}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final Map<String, Boolean> cache) {
        return biPredicate(predicate, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <FIRST>     The type of the first parameter.
     * @param <SECOND>    The type of the second parameter.
     * @param predicate   The {@link BiPredicate} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link BiPredicate}.
     */
    @CheckReturnValue
    public static <KEY, FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Boolean> cache) {
        return new BiPredicateMemoizer<>(asConcurrentMap(cache), keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link Supplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    @CheckReturnValue
    public static BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return booleanSupplier(supplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link BooleanSupplier} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    @CheckReturnValue
    public static BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Map<String, Boolean> cache) {
        return booleanSupplier(supplier, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return booleanSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link BooleanSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link BooleanSupplier}.
     */
    @CheckReturnValue
    public static <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Boolean> cache) {
        return new BooleanSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link Consumer} to memoize.
     * @return The wrapped {@link Consumer}.
     */
    @CheckReturnValue
    public static <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return consumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY, INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction) {
        return consumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link Consumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer}.
     */
    @CheckReturnValue
    public static <INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Map<INPUT, INPUT> cache) {
        return consumer(consumer, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link Consumer} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new ConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    @CheckReturnValue
    public static DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return doubleBinaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final DoubleBinaryFunction<KEY> keyFunction) {
        return doubleBinaryOperator(operator, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link DoubleBinaryOperator} to memoize.
     * @param keyFunction The {@link DoubleBinaryFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final DoubleBinaryFunction<KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new DoubleBinaryOperatorMemoizer<>(asConcurrentMap(cache), keyFunction, operator);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleBinaryOperator} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    @CheckReturnValue
    public static DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator,
            final Map<String, Double> cache) {
        return doubleBinaryOperator(operator, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link DoubleConsumer} to memoize.
     * @return The wrapped {@link DoubleConsumer}.
     */
    @CheckReturnValue
    public static DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return doubleConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final DoubleFunction<KEY> keyFunction) {
        return doubleConsumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link DoubleConsumer} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    @CheckReturnValue
    public static <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final DoubleFunction<KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new DoubleConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link DoubleConsumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleConsumer}.
     */
    @CheckReturnValue
    public static DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer,
            final Map<Double, Double> cache) {
        return doubleConsumer(consumer, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output.
     * @param function The {@link DoubleFunction} to memoize.
     * @return The wrapped {@link DoubleFunction}.
     */
    @CheckReturnValue
    public static <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return doubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link DoubleFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @return The wrapped {@link DoubleFunction}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction) {
        return doubleFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link DoubleFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleFunction}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final DoubleFunction<KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new DoubleFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output.
     * @param function The {@link DoubleFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleFunction}.
     */
    @CheckReturnValue
    public static <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function,
            final Map<Double, OUTPUT> cache) {
        return doubleFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link DoublePredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    @CheckReturnValue
    public static DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return doublePredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final DoubleFunction<KEY> keyFunction) {
        return doublePredicate(predicate, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link DoublePredicate} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    @CheckReturnValue
    public static <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final DoubleFunction<KEY> keyFunction,
            final Map<KEY, Boolean> cache) {
        return new DoublePredicateMemoizer<>(asConcurrentMap(cache), keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link DoublePredicate} to memoize.
     * @param cache     The {@link Map} based cache to use.
     * @return The wrapped {@link DoublePredicate}.
     */
    @CheckReturnValue
    public static DoublePredicate doublePredicate(
            final DoublePredicate predicate,
            final Map<Double, Boolean> cache) {
        return doublePredicate(predicate, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link Supplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    @CheckReturnValue
    public static DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return doubleSupplier(supplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link DoubleSupplier} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    @CheckReturnValue
    public static DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Map<String, Double> cache) {
        return doubleSupplier(supplier, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return doubleSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link DoubleSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleSupplier}.
     */
    @CheckReturnValue
    public static <KEY> DoubleSupplier doubleSupplier(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Double> cache) {
        return new DoubleSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    @CheckReturnValue
    public static DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return doubleToIntFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final DoubleFunction<KEY> keyFunction) {
        return doubleToIntFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link DoubleToIntFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    @CheckReturnValue
    public static <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final DoubleFunction<KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new DoubleToIntFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToIntFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    @CheckReturnValue
    public static DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function,
            final Map<Double, Integer> cache) {
        return doubleToIntFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    @CheckReturnValue
    public static DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return doubleToLongFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final DoubleFunction<KEY> keyFunction) {
        return doubleToLongFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link DoubleToLongFunction} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    @CheckReturnValue
    public static <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final DoubleFunction<KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new DoubleToLongFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link DoubleToLongFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    @CheckReturnValue
    public static DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function,
            final Map<Double, Long> cache) {
        return doubleToLongFunction(function, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    @CheckReturnValue
    public static DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return doubleUnaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final DoubleFunction<KEY> keyFunction) {
        return doubleUnaryOperator(operator, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link DoubleUnaryOperator} to memoize.
     * @param keyFunction The {@link DoubleFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final DoubleFunction<KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new DoubleUnaryOperatorMemoizer<>(asConcurrentMap(cache), keyFunction, operator);
    }

    /**
     * <p>
     * Memoizes a {@link DoubleUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link DoubleUnaryOperator} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    @CheckReturnValue
    public static DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator,
            final Map<Double, Double> cache) {
        return doubleUnaryOperator(operator, Double::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return function(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return function(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function}.
     */
    @CheckReturnValue
    public static <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Map<INPUT, OUTPUT> cache) {
        return function(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function}.
     */
    @CheckReturnValue
    public static <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new FunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    @CheckReturnValue
    public static IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return intBinaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntBinaryOperator} to memoize.
     * @param keyFunction The {@link IntBinaryFunction} to compute the cache key.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final IntBinaryFunction<KEY> keyFunction) {
        return intBinaryOperator(operator, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntBinaryOperator} to memoize.
     * @param keyFunction The {@link IntBinaryFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final IntBinaryFunction<KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new IntBinaryOperatorMemoizer<>(asConcurrentMap(cache), keyFunction, operator);
    }

    /**
     * <p>
     * Memoizes a {@link IntBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param operator The {@link IntBinaryOperator} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    @CheckReturnValue
    public static IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator,
            final Map<String, Integer> cache) {
        return intBinaryOperator(operator, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link IntConsumer} to memoize.
     * @return The wrapped {@link IntConsumer}.
     */
    @CheckReturnValue
    public static IntConsumer intConsumer(final IntConsumer consumer) {
        return intConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> IntConsumer intConsumer(
            final IntConsumer consumer,
            final IntFunction<KEY> keyFunction) {
        return intConsumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link IntConsumer} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntConsumer}.
     */
    @CheckReturnValue
    public static <KEY> IntConsumer intConsumer(
            final IntConsumer consumer,
            final IntFunction<KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new IntConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link IntConsumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntConsumer}.
     */
    @CheckReturnValue
    public static IntConsumer intConsumer(
            final IntConsumer consumer,
            final Map<Integer, Integer> cache) {
        return intConsumer(consumer, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link IntFunction} to memoize.
     * @return The wrapped {@link IntFunction}.
     */
    @CheckReturnValue
    public static <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return intFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction) {
        return intFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link IntFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntFunction}.
     */
    @CheckReturnValue
    public static <OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return intFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link IntFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntFunction}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new IntFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link IntPredicate} to memoize.
     * @return The wrapped {@link IntPredicate}.
     */
    @CheckReturnValue
    public static IntPredicate intPredicate(final IntPredicate predicate) {
        return intPredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> IntPredicate intPredicate(
            final IntPredicate predicate,
            final IntFunction<KEY> keyFunction) {
        return intPredicate(predicate, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link IntPredicate} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntPredicate}.
     */
    @CheckReturnValue
    public static <KEY> IntPredicate intPredicate(
            final IntPredicate predicate,
            final IntFunction<KEY> keyFunction,
            final Map<KEY, Boolean> cache) {
        return new IntPredicateMemoizer<>(asConcurrentMap(cache), keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link IntPredicate} to memoize.
     * @param cache     The {@link Map} based cache to use.
     * @return The wrapped {@link IntPredicate}.
     */
    @CheckReturnValue
    public static IntPredicate intPredicate(
            final IntPredicate predicate,
            final Map<Integer, Boolean> cache) {
        return intPredicate(predicate, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link Supplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    @CheckReturnValue
    public static IntSupplier intSupplier(final IntSupplier supplier) {
        return intSupplier(supplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link IntSupplier} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntSupplier}.
     */
    @CheckReturnValue
    public static IntSupplier intSupplier(
            final IntSupplier supplier,
            final Map<String, Integer> cache) {
        return intSupplier(supplier, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return intSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link IntSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntSupplier}.
     */
    @CheckReturnValue
    public static <KEY> IntSupplier intSupplier(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Integer> cache) {
        return new IntSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToDoubleFunction} to memoize.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    @CheckReturnValue
    public static IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return intToDoubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final IntFunction<KEY> keyFunction) {
        return intToDoubleFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link IntToDoubleFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    @CheckReturnValue
    public static <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final IntFunction<KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new IntToDoubleFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToDoubleFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    @CheckReturnValue
    public static IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function,
            final Map<Integer, Double> cache) {
        return intToDoubleFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToLongFunction} to memoize.
     * @return The wrapped {@link IntToLongFunction}.
     */
    @CheckReturnValue
    public static IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return intToLongFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final IntFunction<KEY> keyFunction) {
        return intToLongFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link IntToLongFunction} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    @CheckReturnValue
    public static <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final IntFunction<KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new IntToLongFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link IntToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link IntToLongFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    @CheckReturnValue
    public static IntToLongFunction intToLongFunction(
            final IntToLongFunction function,
            final Map<Integer, Long> cache) {
        return intToLongFunction(function, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntUnaryOperator} to memoize.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    @CheckReturnValue
    public static IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return intUnaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final IntFunction<KEY> keyFunction) {
        return intUnaryOperator(operator, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link IntUnaryOperator} to memoize.
     * @param keyFunction The {@link IntFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final IntFunction<KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new IntUnaryOperatorMemoizer<>(asConcurrentMap(cache), keyFunction, operator);
    }

    /**
     * <p>
     * Memoizes a {@link IntUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link IntUnaryOperator} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link IntUnaryOperator}.
     */
    @CheckReturnValue
    public static IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator,
            final Map<Integer, Integer> cache) {
        return intUnaryOperator(operator, Integer::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongBinaryOperator} to memoize.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    @CheckReturnValue
    public static LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return longBinaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongBinaryOperator} to memoize.
     * @param keyFunction The {@link LongBinaryFunction} to compute the cache key.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final LongBinaryFunction<KEY> keyFunction) {
        return longBinaryOperator(operator, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongBinaryOperator} to memoize.
     * @param keyFunction The {@link LongBinaryFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final LongBinaryFunction<KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new LongBinaryOperatorMemoizer<>(asConcurrentMap(cache), keyFunction, operator);
    }

    /**
     * <p>
     * Memoizes a {@link LongBinaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param operator The {@link LongBinaryOperator} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongBinaryOperator}.
     */
    @CheckReturnValue
    public static LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator,
            final Map<String, Long> cache) {
        return longBinaryOperator(operator, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link LongConsumer} to memoize.
     * @return The wrapped {@link LongConsumer}.
     */
    @CheckReturnValue
    public static LongConsumer longConsumer(final LongConsumer consumer) {
        return longConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> LongConsumer longConsumer(
            final LongConsumer consumer,
            final LongFunction<KEY> keyFunction) {
        return longConsumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link LongConsumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongConsumer}.
     */
    @CheckReturnValue
    public static LongConsumer longConsumer(
            final LongConsumer consumer,
            final Map<Long, Long> cache) {
        return longConsumer(consumer, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link LongConsumer} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongConsumer}.
     */
    @CheckReturnValue
    public static <KEY> LongConsumer longConsumer(
            final LongConsumer consumer,
            final LongFunction<KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new LongConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
    @CheckReturnValue
    public static <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return longFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
    @CheckReturnValue
    public static <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction) {
        return longFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongFunction}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final LongFunction<KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new LongFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link LongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link LongFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongFunction}.
     */
    @CheckReturnValue
    public static <OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function,
            final Map<Long, OUTPUT> cache) {
        return longFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link LongPredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    @CheckReturnValue
    public static LongPredicate longPredicate(final LongPredicate predicate) {
        return longPredicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> LongPredicate longPredicate(
            final LongPredicate predicate,
            final LongFunction<KEY> keyFunction) {
        return longPredicate(predicate, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param predicate   The {@link LongPredicate} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongPredicate}.
     */
    @CheckReturnValue
    public static <KEY> LongPredicate longPredicate(
            final LongPredicate predicate,
            final LongFunction<KEY> keyFunction,
            final Map<KEY, Boolean> cache) {
        return new LongPredicateMemoizer<>(asConcurrentMap(cache), keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param predicate The {@link LongPredicate} to memoize.
     * @param cache     The {@link Map} based cache to use.
     * @return The wrapped {@link LongPredicate}.
     */
    @CheckReturnValue
    public static LongPredicate longPredicate(
            final LongPredicate predicate,
            final Map<Long, Boolean> cache) {
        return longPredicate(predicate, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link Supplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    @CheckReturnValue
    public static LongSupplier longSupplier(final LongSupplier supplier) {
        return longSupplier(supplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param supplier The {@link LongSupplier} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongSupplier}.
     */
    @CheckReturnValue
    public static LongSupplier longSupplier(
            final LongSupplier supplier,
            final Map<String, Long> cache) {
        return longSupplier(supplier, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return longSupplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param supplier    The {@link LongSupplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongSupplier}.
     */
    @CheckReturnValue
    public static <KEY> LongSupplier longSupplier(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Long> cache) {
        return new LongSupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToDoubleFunction} to memoize.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    @CheckReturnValue
    public static LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return longToDoubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final LongFunction<KEY> keyFunction) {
        return longToDoubleFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link LongToDoubleFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    @CheckReturnValue
    public static <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final LongFunction<KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new LongToDoubleFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link LongToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToDoubleFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongToDoubleFunction}.
     */
    @CheckReturnValue
    public static LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function,
            final Map<Long, Double> cache) {
        return longToDoubleFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToIntFunction} to memoize.
     * @return The wrapped {@link LongToIntFunction}.
     */
    @CheckReturnValue
    public static LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return longToIntFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final LongFunction<KEY> keyFunction) {
        return longToIntFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link LongToIntFunction} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    @CheckReturnValue
    public static <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final LongFunction<KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new LongToIntFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link LongToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param function The {@link LongToIntFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongToIntFunction}.
     */
    @CheckReturnValue
    public static LongToIntFunction longToIntFunction(
            final LongToIntFunction function,
            final Map<Long, Integer> cache) {
        return longToIntFunction(function, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongUnaryOperator} to memoize.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    @CheckReturnValue
    public static LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return longUnaryOperator(operator, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final LongFunction<KEY> keyFunction) {
        return longUnaryOperator(operator, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param operator    The {@link LongUnaryOperator} to memoize.
     * @param keyFunction The {@link LongFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    @CheckReturnValue
    public static <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final LongFunction<KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new LongUnaryOperatorMemoizer<>(asConcurrentMap(cache), keyFunction, operator);
    }

    /**
     * <p>
     * Memoizes a {@link LongUnaryOperator} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param operator The {@link LongUnaryOperator} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link LongUnaryOperator}.
     */
    @CheckReturnValue
    public static LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator,
            final Map<Long, Long> cache) {
        return longUnaryOperator(operator, Long::valueOf, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjDoubleConsumer} to memoize.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    @CheckReturnValue
    public static <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return objDoubleConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjDoubleConsumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    @CheckReturnValue
    public static <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer,
            final Map<String, String> cache) {
        return objDoubleConsumer(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer,
            final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return objDoubleConsumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjDoubleConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer,
            final ObjDoubleFunction<INPUT, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new ObjDoubleConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjIntConsumer} to memoize.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    @CheckReturnValue
    public static <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return objIntConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjIntConsumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    @CheckReturnValue
    public static <INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer,
            final Map<String, String> cache) {
        return objIntConsumer(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjIntConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer,
            final ObjIntFunction<INPUT, KEY> keyFunction) {
        return objIntConsumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjIntConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer,
            final ObjIntFunction<INPUT, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new ObjIntConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjLongConsumer} to memoize.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    @CheckReturnValue
    public static <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return objLongConsumer(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param consumer The {@link ObjLongConsumer} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    @CheckReturnValue
    public static <INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer,
            final Map<String, String> cache) {
        return objLongConsumer(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjLongConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer,
            final ObjLongFunction<INPUT, KEY> keyFunction) {
        return objLongConsumer(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link ObjLongConsumer} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer,
            final ObjLongFunction<INPUT, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new ObjLongConsumerMemoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
    @CheckReturnValue
    public static <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return predicate(predicate, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
    @CheckReturnValue
    public static <KEY, INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction) {
        return predicate(predicate, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Predicate}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, Boolean> cache) {
        return new PredicateMemoizer<>(asConcurrentMap(cache), keyFunction, predicate);
    }

    /**
     * <p>
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>   The type of the input.
     * @param predicate The {@link Predicate} to memoize.
     * @param cache     The {@link Map} based cache to use.
     * @return The wrapped {@link Predicate}.
     */
    @CheckReturnValue
    public static <INPUT> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Map<INPUT, Boolean> cache) {
        return predicate(predicate, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
    @CheckReturnValue
    public static <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return supplier(supplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param supplier The {@link Supplier} to memoize.
     * @param cache    {@link Map} of already computed values.
     * @return The wrapped {@link Supplier}.
     */
    @CheckReturnValue
    public static <OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Map<String, OUTPUT> cache) {
        return supplier(supplier, staticKey(), asConcurrentMap(cache));
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
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
    @CheckReturnValue
    public static <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier) {
        return supplier(supplier, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache key</li>
     * <li>Custom cache</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param supplier    The {@link Supplier} to memoize.
     * @param keySupplier The {@link Supplier} for the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Supplier}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, OUTPUT> cache) {
        return new SupplierMemoizer<>(asConcurrentMap(cache), keySupplier, supplier);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return toDoubleBiFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toDoubleBiFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new ToDoubleBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToDoubleBiFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final Map<String, Double> cache) {
        return toDoubleBiFunction(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToDoubleFunction} to memoize.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    @CheckReturnValue
    public static <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return toDoubleFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY, INPUT> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return toDoubleFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToDoubleFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, Double> cache) {
        return new ToDoubleFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToDoubleFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToDoubleFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    @CheckReturnValue
    public static <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function,
            final Map<INPUT, Double> cache) {
        return toDoubleFunction(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return toIntBiFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toIntBiFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
     * @param cache       {@link Map} of already computed values.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new ToIntBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToIntBiFunction} to memoize.
     * @param cache    {@link Map} of already computed values.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function,
            final Map<String, Integer> cache) {
        return toIntBiFunction(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY, INPUT> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return toIntFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToIntFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, Integer> cache) {
        return new ToIntFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToIntFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    @CheckReturnValue
    public static <INPUT> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function,
            final Map<INPUT, Integer> cache) {
        return toIntFunction(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToIntFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToIntFunction} to memoize.
     * @return The wrapped {@link ToIntFunction}.
     */
    @CheckReturnValue
    public static <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return toIntFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return toLongBiFunction(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return toLongBiFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new ToLongBiFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongBiFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <FIRST>  The type of the first parameter.
     * @param <SECOND> The type of the second parameter.
     * @param function The {@link ToLongBiFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    @CheckReturnValue
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function,
            final Map<String, Long> cache) {
        return toLongBiFunction(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
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
    @CheckReturnValue
    public static <KEY, INPUT> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return toLongFunction(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <INPUT>     The type of the input.
     * @param <KEY>       The type of the cache key.
     * @param function    The {@link ToLongFunction} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    @CheckReturnValue
    public static <KEY, INPUT> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, Long> cache) {
        return new ToLongFunctionMemoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToLongFunction} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    @CheckReturnValue
    public static <INPUT> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function,
            final Map<INPUT, Long> cache) {
        return toLongFunction(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link ToLongFunction} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>  The type of the input.
     * @param function The {@link ToLongFunction} to memoize.
     * @return The wrapped {@link ToLongFunction}.
     */
    @CheckReturnValue
    public static <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return toLongFunction(function, emptyMap());
    }

}
