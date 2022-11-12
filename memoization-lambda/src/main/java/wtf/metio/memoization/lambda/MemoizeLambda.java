/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.*;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import wtf.metio.memoization.core.MemoizationDefaults;

import java.util.Map;
import java.util.function.Supplier;

import static java.util.Collections.emptyMap;
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
 * @see Fn0
 * @see Fn1
 * @see Fn2
 * @see Fn3
 * @see Fn4
 * @see Fn5
 * @see Fn6
 * @see Fn7
 * @see Fn8
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class MemoizeLambda {

    private MemoizeLambda() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link Fn0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn0} to memoize.
     * @return The wrapped {@link Fn0}.
     */
    @CheckReturnValue
    public static <OUTPUT> Fn0<OUTPUT> fn0(final Fn0<OUTPUT> function) {
        return fn0(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn0} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn0}.
     */
    @CheckReturnValue
    public static <OUTPUT> Fn0<OUTPUT> fn0(
            final Fn0<OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn0(function, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn0} to memoize.
     * @param keySupplier The {@link Supplier} to supply the cache key.
     * @return The wrapped {@link Fn0}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Fn0<OUTPUT> fn0(
            final Fn0<OUTPUT> function,
            final Supplier<KEY> keySupplier) {
        return fn0(function, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn0} to memoize.
     * @param keySupplier The {@link Supplier} to supply the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn0}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Fn0<OUTPUT> fn0(
            final Fn0<OUTPUT> function,
            final Supplier<KEY> keySupplier,
            final Map<KEY, OUTPUT> cache) {
        return new Fn0Memoizer<>(asConcurrentMap(cache), keySupplier, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>> The type of the input parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn1} to memoize.
     * @return The wrapped {@link Fn1}.
     */
    @CheckReturnValue
    public static <INPUT, OUTPUT> Fn1<INPUT, OUTPUT> fn1(final Fn1<INPUT, OUTPUT> function) {
        return fn1(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <INPUT>> The type of the input parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn1} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn1}.
     */
    @CheckReturnValue
    public static <INPUT, OUTPUT> Fn1<INPUT, OUTPUT> fn1(
            final Fn1<INPUT, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn1(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <INPUT>>    The type of the input parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn1} to memoize.
     * @param keyFunction The {@link Fn1} to compute the cache key.
     * @return The wrapped {@link Fn1}.
     */
    @CheckReturnValue
    public static <KEY, INPUT, OUTPUT> Fn1<INPUT, OUTPUT> fn1(
            final Fn1<INPUT, OUTPUT> function,
            final Fn1<INPUT, KEY> keyFunction) {
        return fn1(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <INPUT>>    The type of the input parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn1} to memoize.
     * @param keyFunction The {@link Fn1} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn1}.
     */
    @CheckReturnValue
    public static <KEY, INPUT, OUTPUT> Fn1<INPUT, OUTPUT> fn1(
            final Fn1<INPUT, OUTPUT> function,
            final Fn1<INPUT, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn1Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn2} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn2} to memoize.
     * @return The wrapped {@link Fn2}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> fn2(final Fn2<TYPE1, TYPE2, OUTPUT> function) {
        return fn2(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn2} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn2} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn2}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> fn2(
            final Fn2<TYPE1, TYPE2, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn2(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn2} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn2} to memoize.
     * @param keyFunction The {@link Fn2} to compute the cache key.
     * @return The wrapped {@link Fn2}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> fn2(
            final Fn2<TYPE1, TYPE2, OUTPUT> function,
            final Fn2<TYPE1, TYPE2, KEY> keyFunction) {
        return fn2(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn2} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn2} to memoize.
     * @param keyFunction The {@link Fn2} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn2}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> fn2(
            final Fn2<TYPE1, TYPE2, OUTPUT> function,
            final Fn2<TYPE1, TYPE2, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn2Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn3} to memoize.
     * @return The wrapped {@link Fn3}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> fn3(final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function) {
        return fn3(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn3} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn3}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> fn3(
            final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn3(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn3} to memoize.
     * @param keyFunction The {@link Fn3} to compute the cache key.
     * @return The wrapped {@link Fn3}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> fn3(
            final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Fn3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return fn3(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn3} to memoize.
     * @param keyFunction The {@link Fn3} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn3}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> fn3(
            final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Fn3<TYPE1, TYPE2, TYPE3, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn3Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn4} to memoize.
     * @return The wrapped {@link Fn4}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> fn4(
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function) {
        return fn4(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn4} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn4}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> fn4(
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn4(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn4} to memoize.
     * @param keyFunction The {@link Fn4} to compute the cache key.
     * @return The wrapped {@link Fn4}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> fn4(
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return fn4(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn4} to memoize.
     * @param keyFunction The {@link Fn4} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn4}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> fn4(
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn4Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn5} to memoize.
     * @return The wrapped {@link Fn5}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> fn5(
            final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function) {
        return fn5(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn5} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn5}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> fn5(
            final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn5(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn5} to memoize.
     * @param keyFunction The {@link Fn5} to compute the cache key.
     * @return The wrapped {@link Fn5}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> fn5(
            final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return fn5(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn5} to memoize.
     * @param keyFunction The {@link Fn5} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn5}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> fn5(
            final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn5Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn6} to memoize.
     * @return The wrapped {@link Fn6}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> fn6(
            final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function) {
        return fn6(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn6} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn6}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> fn6(
            final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn6(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn6} to memoize.
     * @param keyFunction The {@link Fn6} to compute the cache key.
     * @return The wrapped {@link Fn6}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> fn6(
            final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return fn6(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn6} to memoize.
     * @param keyFunction The {@link Fn6} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn6}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> fn6(
            final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn6Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn7} to memoize.
     * @return The wrapped {@link Fn7}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> fn7(
            final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function) {
        return fn7(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn7} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn7}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> fn7(
            final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn7(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn7} to memoize.
     * @param keyFunction The {@link Fn7} to compute the cache key.
     * @return The wrapped {@link Fn7}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> fn7(
            final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return fn7(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn7} to memoize.
     * @param keyFunction The {@link Fn7} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn7}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> fn7(
            final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn7Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Fn8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <TYPE8>  The type of the eight parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn8} to memoize.
     * @return The wrapped {@link Fn8}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> fn8(
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function) {
        return fn8(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>  The type of the fourth parameter.
     * @param <TYPE5>  The type of the fifth parameter.
     * @param <TYPE6>  The type of the sixth parameter.
     * @param <TYPE7>  The type of the seventh parameter.
     * @param <TYPE8>  The type of the eight parameter.
     * @param <OUTPUT> The type of the output.
     * @param function The {@link Fn8} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Fn8}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> fn8(
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return fn8(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Fn8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <TYPE8>     The type of the eight parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn8} to memoize.
     * @param keyFunction The {@link Fn8} to compute the cache key.
     * @return The wrapped {@link Fn8}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> fn8(
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return fn8(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Fn8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE4>     The type of the fourth parameter.
     * @param <TYPE5>     The type of the fifth parameter.
     * @param <TYPE6>     The type of the sixth parameter.
     * @param <TYPE7>     The type of the seventh parameter.
     * @param <TYPE8>     The type of the eight parameter.
     * @param <OUTPUT>    The type of the output.
     * @param function    The {@link Fn8} to memoize.
     * @param keyFunction The {@link Fn8} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Fn8}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> fn8(
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Fn8Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

}
