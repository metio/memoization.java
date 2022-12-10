/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import io.reactivex.rxjava3.functions.*;
import wtf.metio.memoization.core.MemoizationDefaults;

import java.util.Map;
import java.util.function.LongFunction;

import static java.util.Collections.emptyMap;
import static wtf.metio.memoization.core.ConcurrentMaps.asConcurrentMap;

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
 * The memoizer uses the default function or {@link Supplier} to calculate the cache key for each call. Either
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
 * The memoizer uses a user-defined function or {@link Supplier} to calculate the cache key for each call.
 * Take a look at {@link MemoizationDefaults} for a possible key functions and suppliers.
 * </p>
 *
 * @see Action
 * @see BiConsumer
 * @see BiFunction
 * @see BiPredicate
 * @see BooleanSupplier
 * @see Cancellable
 * @see Consumer
 * @see Function3
 * @see Function4
 * @see Function5
 * @see Function6
 * @see Function7
 * @see Function8
 * @see Function9
 * @see Function
 * @see IntFunction
 * @see LongConsumer
 * @see Predicate
 * @see Supplier
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class MemoizeRx {

    private MemoizeRx() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link Action} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param action The {@link Action} to memoize.
     * @return The wrapped {@link Action}.
     */
    @CheckReturnValue
    public static Action action(final Action action) {
        return action(action, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Action} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param action The {@link Action} to memoize.
     * @param cache  The {@link Map} based cache to use.
     * @return The wrapped {@link Action}.
     */
    @CheckReturnValue
    public static Action action(
            final Action action,
            final Map<Integer, Integer> cache) {
        return action(action, () -> 1, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Action} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param action      The {@link Action} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @return The wrapped {@link Action}.
     */
    @CheckReturnValue
    public static <KEY> Action action(
            final Action action,
            final Supplier<KEY> keySupplier) {
        return action(action, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Action} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param action      The {@link Action} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Action}.
     */
    @CheckReturnValue
    public static <KEY> Action action(
            final Action action,
            final Supplier<KEY> keySupplier,
            final Map<KEY, KEY> cache) {
        return new ActionMemoizer<>(asConcurrentMap(cache), keySupplier, action);
    }

    /**
     * <p>
     * Memoizes a {@link Cancellable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param cancellable The {@link Cancellable} to memoize.
     * @return The wrapped {@link Cancellable}.
     */
    @CheckReturnValue
    public static Cancellable cancellable(final Cancellable cancellable) {
        return cancellable(cancellable, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Cancellable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param cancellable The {@link Cancellable} to memoize.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Cancellable}.
     */
    @CheckReturnValue
    public static Cancellable cancellable(
            final Cancellable cancellable,
            final Map<Integer, Integer> cache) {
        return cancellable(cancellable, () -> 1, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Cancellable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param cancellable The {@link Cancellable} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @return The wrapped {@link Cancellable}.
     */
    @CheckReturnValue
    public static <KEY> Cancellable cancellable(
            final Cancellable cancellable,
            final Supplier<KEY> keySupplier) {
        return cancellable(cancellable, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Cancellable} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param cancellable The {@link Cancellable} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Cancellable}.
     */
    @CheckReturnValue
    public static <KEY> Cancellable cancellable(
            final Cancellable cancellable,
            final Supplier<KEY> keySupplier,
            final Map<KEY, KEY> cache) {
        return new CancellableMemoizer<>(asConcurrentMap(cache), keySupplier, cancellable);
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
            final Map<Integer, Boolean> cache) {
        return booleanSupplier(supplier, () -> 1, cache);
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
        return function(function, input -> input, cache);
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
     * Memoizes a {@link Function3} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function3} to memoize.
     * @return The wrapped {@link Function3}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function) {
        return function3(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function3} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function3} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function3}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function3(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function3} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function3} to memoize.
     * @param keyFunction The {@link Function3} to compute the cache key.
     * @return The wrapped {@link Function3}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return function3(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function3} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function3} to memoize.
     * @param keyFunction The {@link Function3} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function3}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function3Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function4} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function4} to memoize.
     * @return The wrapped {@link Function4}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function) {
        return function4(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function4} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function4} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function4}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function4(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function4} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function4} to memoize.
     * @param keyFunction The {@link Function4} to compute the cache key.
     * @return The wrapped {@link Function4}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return function4(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function4} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function4} to memoize.
     * @param keyFunction The {@link Function4} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function4}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function4Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function5} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function5} to memoize.
     * @return The wrapped {@link Function5}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function) {
        return function5(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function5} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function5} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function5}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function5(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function5} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function5} to memoize.
     * @param keyFunction The {@link Function5} to compute the cache key.
     * @return The wrapped {@link Function5}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return function5(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function5} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function5} to memoize.
     * @param keyFunction The {@link Function5} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function5}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function5Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function6} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function6} to memoize.
     * @return The wrapped {@link Function6}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function) {
        return function6(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function6} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function6} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function6}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function6(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function6} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function6} to memoize.
     * @param keyFunction The {@link Function6} to compute the cache key.
     * @return The wrapped {@link Function6}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return function6(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function6} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function6} to memoize.
     * @param keyFunction The {@link Function6} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function6}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function6Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function7} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function7} to memoize.
     * @return The wrapped {@link Function7}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function) {
        return function7(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function7} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function7} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function7}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function7(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function7} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function7} to memoize.
     * @param keyFunction The {@link Function7} to compute the cache key.
     * @return The wrapped {@link Function7}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return function7(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function7} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function7} to memoize.
     * @param keyFunction The {@link Function7} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function7}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function7Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function8} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <TYPE8>  The type of the eight parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function8} to memoize.
     * @return The wrapped {@link Function8}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function) {
        return function8(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function8} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <TYPE8>  The type of the eight parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function8} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function8}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function8(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function8} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <TYPE8>     The type of the eight parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function8} to memoize.
     * @param keyFunction The {@link Function8} to compute the cache key.
     * @return The wrapped {@link Function8}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return function8(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function8} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <TYPE8>     The type of the eight parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function8} to memoize.
     * @param keyFunction The {@link Function8} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function8}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function8Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function9} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <TYPE8>  The type of the eight parameter.
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function9} to memoize.
     * @return The wrapped {@link Function9}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function) {
        return function9(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function9} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <TYPE3>  The type of the third parameter.
     * @param <TYPE4>  The type of the forth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <TYPE8>  The type of the eight parameter.
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function9} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function9}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function,
            final Map<Integer, OUTPUT> cache) {
        return function9(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function9} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <TYPE8>     The type of the eight parameter.
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function9} to memoize.
     * @param keyFunction The {@link Function9} to compute the cache key.
     * @return The wrapped {@link Function9}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction) {
        return function9(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function9} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param <TYPE2>     The type of the second parameter.
     * @param <TYPE3>     The type of the third parameter.
     * @param <TYPE4>     The type of the forth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <TYPE8>     The type of the eight parameter.
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function9} to memoize.
     * @param keyFunction The {@link Function9} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function9}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function9Memoizer<>(asConcurrentMap(cache), keyFunction, function);
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
        return predicate(predicate, input -> input, cache);
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
            final Map<Integer, OUTPUT> cache) {
        return supplier(supplier, () -> 1, asConcurrentMap(cache));
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
        return consumer(consumer, input -> input, cache);
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
            final Map<Integer, Boolean> cache) {
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
            final Map<Integer, OUTPUT> cache) {
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
            final Map<Integer, Integer> cache) {
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

}
