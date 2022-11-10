/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import org.jooq.lambda.fi.lang.CheckedRunnable;
import org.jooq.lambda.fi.util.concurrent.CheckedCallable;
import org.jooq.lambda.fi.util.function.CheckedBiConsumer;
import org.jooq.lambda.function.*;
import wtf.metio.memoization.core.MemoizationDefaults;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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
 * The memoizer uses the default key function or {@link Supplier} to calculate the cache key for each call. Either
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
 * @see CheckedBiConsumer
 * @see CheckedCallable
 * @see CheckedRunnable
 * @see Consumer0
 * @see Consumer1
 * @see Consumer2
 * @see Consumer3
 * @see Consumer4
 * @see Consumer5
 * @see Consumer6
 * @see Consumer7
 * @see Consumer8
 * @see Consumer9
 * @see Consumer10
 * @see Consumer11
 * @see Consumer12
 * @see Consumer13
 * @see Consumer14
 * @see Consumer15
 * @see Consumer16
 * @see Function0
 * @see Function1
 * @see Function2
 * @see Function3
 * @see Function4
 * @see Function5
 * @see Function6
 * @see Function7
 * @see Function8
 * @see Function9
 * @see Function10
 * @see Function11
 * @see Function12
 * @see Function13
 * @see Function14
 * @see Function15
 * @see Function16
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class MemoizeJool {

    private MemoizeJool() {
        // factory class
    }

    /**
     * <p>
     * Memoizes a {@link Consumer0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link Consumer0} to memoize.
     * @return The wrapped {@link Consumer0}.
     */
    @CheckReturnValue
    public static Consumer0 consumer0(final Consumer0 consumer) {
        return consumer0(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param consumer The {@link Consumer0} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer0}.
     */
    @CheckReturnValue
    public static Consumer0 consumer0(
            final Consumer0 consumer,
            final Map<String, String> cache) {
        return consumer0(consumer, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link Consumer0} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @return The wrapped {@link Consumer0}.
     */
    @CheckReturnValue
    public static <KEY> Consumer0 consumer0(
            final Consumer0 consumer,
            final Supplier<KEY> keySupplier) {
        return consumer0(consumer, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param consumer    The {@link Consumer0} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer0}.
     */
    @CheckReturnValue
    public static <KEY> Consumer0 consumer0(
            final Consumer0 consumer,
            final Supplier<KEY> keySupplier,
            final Map<KEY, KEY> cache) {
        return new Consumer0Memoizer<>(asConcurrentMap(cache), keySupplier, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param consumer The {@link Consumer1} to memoize.
     * @return The wrapped {@link Consumer1}.
     */
    @CheckReturnValue
    public static <TYPE1> Consumer1<TYPE1> consumer1(final Consumer1<TYPE1> consumer) {
        return consumer1(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param consumer The {@link Consumer1} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer1}.
     */
    @CheckReturnValue
    public static <TYPE1> Consumer1<TYPE1> consumer1(
            final Consumer1<TYPE1> consumer,
            final Map<TYPE1, TYPE1> cache) {
        return consumer1(consumer, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param consumer    The {@link Consumer1} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link Consumer1}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1> Consumer1<TYPE1> consumer1(
            final Consumer1<TYPE1> consumer,
            final Function<TYPE1, KEY> keyFunction) {
        return consumer1(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <TYPE1>     The type of the first parameter.
     * @param consumer    The {@link Consumer1} to memoize.
     * @param keyFunction The {@link Function} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer1}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1> Consumer1<TYPE1> consumer1(
            final Consumer1<TYPE1> consumer,
            final Function<TYPE1, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer1Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer2} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param consumer The {@link Consumer2} to memoize.
     * @return The wrapped {@link Consumer2}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(final Consumer2<TYPE1, TYPE2> consumer) {
        return consumer2(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer2} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param consumer The {@link Consumer2} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer2}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(
            final Consumer2<TYPE1, TYPE2> consumer,
            final Map<String, String> cache) {
        return consumer2(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer2} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer2} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link Consumer2}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(
            final Consumer2<TYPE1, TYPE2> consumer,
            final BiFunction<TYPE1, TYPE2, KEY> keyFunction) {
        return consumer2(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer2} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer2} to memoize.
     * @param keyFunction The {@link BiFunction} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer2}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(
            final Consumer2<TYPE1, TYPE2> consumer,
            final BiFunction<TYPE1, TYPE2, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer2Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer3} to memoize.
     * @return The wrapped {@link Consumer3}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(
            final Consumer3<TYPE1, TYPE2, TYPE3> consumer) {
        return consumer3(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer3} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer3}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(
            final Consumer3<TYPE1, TYPE2, TYPE3> consumer,
            final Map<String, String> cache) {
        return consumer3(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer3} to memoize.
     * @param keyFunction The {@link Function3} to compute the cache key.
     * @return The wrapped {@link Consumer3}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(
            final Consumer3<TYPE1, TYPE2, TYPE3> consumer,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return consumer3(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer3} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer3} to memoize.
     * @param keyFunction The {@link Function3} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer3}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(
            final Consumer3<TYPE1, TYPE2, TYPE3> consumer,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer3Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer4} to memoize.
     * @return The wrapped {@link Consumer4}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(
            final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer) {
        return consumer4(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer4} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer4}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(
            final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer,
            final Map<String, String> cache) {
        return consumer4(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer4} to memoize.
     * @param keyFunction The {@link Function4} to compute the cache key.
     * @return The wrapped {@link Consumer4}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(
            final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer,
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return consumer4(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer4} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer4} to memoize.
     * @param keyFunction The {@link Function4} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer4}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(
            final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer,
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer4Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer5} to memoize.
     * @return The wrapped {@link Consumer5}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(
            final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer) {
        return consumer5(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer5} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer5}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(
            final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer,
            final Map<String, String> cache) {
        return consumer5(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer5} to memoize.
     * @param keyFunction The {@link Function5} to compute the cache key.
     * @return The wrapped {@link Consumer5}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(
            final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return consumer5(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer5} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer5} to memoize.
     * @param keyFunction The {@link Function5} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer5}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(
            final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer5Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer6} to memoize.
     * @return The wrapped {@link Consumer6}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(
            final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer) {
        return consumer6(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer6} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer6}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(
            final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer,
            final Map<String, String> cache) {
        return consumer6(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer6} to memoize.
     * @param keyFunction The {@link Function6} to compute the cache key.
     * @return The wrapped {@link Consumer6}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(
            final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer,
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return consumer6(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer6} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer6} to memoize.
     * @param keyFunction The {@link Function6} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer6}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(
            final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer,
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer6Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer7} to memoize.
     * @return The wrapped {@link Consumer7}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(
            final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer) {
        return consumer7(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer7} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer7}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(
            final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer,
            final Map<String, String> cache) {
        return consumer7(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer7} to memoize.
     * @param keyFunction The {@link Function7} to compute the cache key.
     * @return The wrapped {@link Consumer7}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(
            final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer,
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return consumer7(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer7} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer7} to memoize.
     * @param keyFunction The {@link Function7} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer7}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(
            final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer,
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer7Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer8} to memoize.
     * @return The wrapped {@link Consumer8}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(
            final Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer) {
        return consumer8(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer The {@link Consumer8} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer8}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(
            final Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer,
            final Map<String, String> cache) {
        return consumer8(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer8} to memoize.
     * @param keyFunction The {@link Function8} to compute the cache key.
     * @return The wrapped {@link Consumer8}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(
            final Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return consumer8(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer8} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param consumer    The {@link Consumer8} to memoize.
     * @param keyFunction The {@link Function8} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer8}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(
            final Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer8Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer9} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param consumer The {@link Consumer9} to memoize.
     * @return The wrapped {@link Consumer9}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(
            final Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer) {
        return consumer9(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer9} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param consumer The {@link Consumer9} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer9}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(
            final Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer,
            final Map<String, String> cache) {
        return consumer9(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer9} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param consumer    The {@link Consumer9} to memoize.
     * @param keyFunction The {@link Function9} to compute the cache key.
     * @return The wrapped {@link Consumer9}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(
            final Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction) {
        return consumer9(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer9} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param consumer    The {@link Consumer9} to memoize.
     * @param keyFunction The {@link Function9} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer9}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(
            final Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer9Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param consumer The {@link Consumer10} to memoize.
     * @return The wrapped {@link Consumer10}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(
            final Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer) {
        return consumer10(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param consumer The {@link Consumer10} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer10}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(
            final Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer,
            final Map<String, String> cache) {
        return consumer10(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param consumer    The {@link Consumer10} to memoize.
     * @param keyFunction The {@link Function10} to compute the cache key.
     * @return The wrapped {@link Consumer10}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(
            final Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer,
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction) {
        return consumer10(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param consumer    The {@link Consumer10} to memoize.
     * @param keyFunction The {@link Function10} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer10}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(
            final Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer,
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer10Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param consumer The {@link Consumer11} to memoize.
     * @return The wrapped {@link Consumer11}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(
            final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer) {
        return consumer11(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param consumer The {@link Consumer11} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer11}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(
            final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer,
            final Map<String, String> cache) {
        return consumer11(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param consumer    The {@link Consumer11} to memoize.
     * @param keyFunction The {@link Function11} to compute the cache key.
     * @return The wrapped {@link Consumer11}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(
            final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer,
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction) {
        return consumer11(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param consumer    The {@link Consumer11} to memoize.
     * @param keyFunction The {@link Function11} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer11}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(
            final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer,
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer11Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param consumer The {@link Consumer12} to memoize.
     * @return The wrapped {@link Consumer12}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(
            final Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer) {
        return consumer12(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param consumer The {@link Consumer12} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer12}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(
            final Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer,
            final Map<String, String> cache) {
        return consumer12(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param consumer    The {@link Consumer12} to memoize.
     * @param keyFunction The {@link Function12} to compute the cache key.
     * @return The wrapped {@link Consumer12}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(
            final Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer,
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction) {
        return consumer12(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param consumer    The {@link Consumer12} to memoize.
     * @param keyFunction The {@link Function12} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer12}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(
            final Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer,
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer12Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param consumer The {@link Consumer13} to memoize.
     * @return The wrapped {@link Consumer13}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(
            final Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer) {
        return consumer13(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param consumer The {@link Consumer13} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer13}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(
            final Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer,
            final Map<String, String> cache) {
        return consumer13(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param consumer    The {@link Consumer13} to memoize.
     * @param keyFunction The {@link Function13} to compute the cache key.
     * @return The wrapped {@link Consumer13}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(
            final Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer,
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction) {
        return consumer13(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param consumer    The {@link Consumer13} to memoize.
     * @param keyFunction The {@link Function13} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer13}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(
            final Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer,
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer13Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param consumer The {@link Consumer14} to memoize.
     * @return The wrapped {@link Consumer14}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(
            final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer) {
        return consumer14(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param consumer The {@link Consumer14} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer14}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(
            final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer,
            final Map<String, String> cache) {
        return consumer14(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param consumer    The {@link Consumer14} to memoize.
     * @param keyFunction The {@link Function14} to compute the cache key.
     * @return The wrapped {@link Consumer14}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(
            final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer,
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction) {
        return consumer14(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param consumer    The {@link Consumer14} to memoize.
     * @param keyFunction The {@link Function14} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer14}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(
            final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer,
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer14Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param consumer The {@link Consumer15} to memoize.
     * @return The wrapped {@link Consumer15}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(
            final Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer) {
        return consumer15(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param consumer The {@link Consumer15} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer15}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(
            final Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer,
            final Map<String, String> cache) {
        return consumer15(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param consumer    The {@link Consumer15} to memoize.
     * @param keyFunction The {@link Function15} to compute the cache key.
     * @return The wrapped {@link Consumer15}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(
            final Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer,
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction) {
        return consumer15(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param consumer    The {@link Consumer15} to memoize.
     * @param keyFunction The {@link Function15} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer15}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(
            final Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer,
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer15Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param <TYPE16> The type of the sixteenth parameter.
     * @param consumer The {@link Consumer16} to memoize.
     * @return The wrapped {@link Consumer16}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(
            final Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer) {
        return consumer16(consumer, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>  The type of the ninth parameter.
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param <TYPE16> The type of the sixteenth parameter.
     * @param consumer The {@link Consumer16} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer16}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(
            final Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer,
            final Map<String, String> cache) {
        return consumer16(consumer, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Consumer16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param <TYPE16>    The type of the sixteenth parameter.
     * @param consumer    The {@link Consumer16} to memoize.
     * @param keyFunction The {@link Function16} to compute the cache key.
     * @return The wrapped {@link Consumer16}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(
            final Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer,
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction) {
        return consumer16(consumer, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Consumer16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE9>     The type of the ninth parameter.
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param <TYPE16>    The type of the sixteenth parameter.
     * @param consumer    The {@link Consumer16} to memoize.
     * @param keyFunction The {@link Function16} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Consumer16}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(
            final Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer,
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction,
            final Map<KEY, KEY> cache) {
        return new Consumer16Memoizer<>(asConcurrentMap(cache), keyFunction, consumer);
    }

    /**
     * <p>
     * Memoizes a {@link Function0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function0} to memoize.
     * @return The wrapped {@link Function0}.
     */
    @CheckReturnValue
    public static <OUTPUT> Function0<OUTPUT> function0(final Function0<OUTPUT> function) {
        return function0(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function0} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function0}.
     */
    @CheckReturnValue
    public static <OUTPUT> Function0<OUTPUT> function0(
            final Function0<OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function0(function, staticKey(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function0} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @return The wrapped {@link Function0}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Function0<OUTPUT> function0(
            final Function0<OUTPUT> function,
            final Supplier<KEY> keySupplier) {
        return function0(function, keySupplier, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function0} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function0} to memoize.
     * @param keySupplier The {@link Supplier} to get the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function0}.
     */
    @CheckReturnValue
    public static <KEY, OUTPUT> Function0<OUTPUT> function0(
            final Function0<OUTPUT> function,
            final Supplier<KEY> keySupplier,
            final Map<KEY, OUTPUT> cache) {
        return new Function0Memoizer<>(asConcurrentMap(cache), keySupplier, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function1} to memoize.
     * @return The wrapped {@link Function1}.
     */
    @CheckReturnValue
    public static <INPUT, OUTPUT> Function1<INPUT, OUTPUT> function1(
            final Function1<INPUT, OUTPUT> function) {
        return function1(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function1} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function1}.
     */
    @CheckReturnValue
    public static <INPUT, OUTPUT> Function1<INPUT, OUTPUT> function1(
            final Function1<INPUT, OUTPUT> function,
            final Map<INPUT, OUTPUT> cache) {
        return function1(function, identity(), cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function1} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @return The wrapped {@link Function1}.
     */
    @CheckReturnValue
    public static <KEY, INPUT, OUTPUT> Function1<INPUT, OUTPUT> function1(
            final Function1<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return function1(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function1} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Custom cache key</li>
     * </ul>
     *
     * @param <KEY>       The type of the cache key.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function1} to memoize.
     * @param keyFunction The {@link Function} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function1}.
     */
    @CheckReturnValue
    public static <KEY, INPUT, OUTPUT> Function1<INPUT, OUTPUT> function1(
            final Function1<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function1Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function2} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Default cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function2} to memoize.
     * @return The wrapped {@link Function2}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(
            final Function2<TYPE1, TYPE2, OUTPUT> function) {
        return function2(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function2} in a {@link java.util.concurrent.ConcurrentMap}.
     * </p>
     *
     * <ul>
     * <li>Custom cache</li>
     * <li>Default cache key</li>
     * </ul>
     *
     * @param <TYPE1>  The type of the first parameter.
     * @param <TYPE2>  The type of the second parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function2} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function2}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(
            final Function2<TYPE1, TYPE2, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function2(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function2} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function2} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link Function2}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(
            final Function2<TYPE1, TYPE2, OUTPUT> function,
            final BiFunction<TYPE1, TYPE2, KEY> keyFunction) {
        return function2(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function2} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function2} to memoize.
     * @param keyFunction The {@link BiFunction} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function2}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(
            final Function2<TYPE1, TYPE2, OUTPUT> function,
            final BiFunction<TYPE1, TYPE2, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function2Memoizer<>(asConcurrentMap(cache), keyFunction, function);
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
            final Map<String, OUTPUT> cache) {
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
            final Map<String, OUTPUT> cache) {
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
            final Map<String, OUTPUT> cache) {
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
            final Map<String, OUTPUT> cache) {
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
            final Map<String, OUTPUT> cache) {
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
            final Map<String, OUTPUT> cache) {
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
            final Map<String, OUTPUT> cache) {
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
     * Memoizes a {@link Function10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function10} to memoize.
     * @return The wrapped {@link Function10}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function) {
        return function10(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function10} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function10}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function10(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function10} to memoize.
     * @param keyFunction The {@link Function10} to compute the cache key.
     * @return The wrapped {@link Function10}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function,
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction) {
        return function10(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function10} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function10} to memoize.
     * @param keyFunction The {@link Function10} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function10}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function,
            final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function10Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function11} to memoize.
     * @return The wrapped {@link Function11}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function) {
        return function11(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function11} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function11}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function11(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function11} to memoize.
     * @param keyFunction The {@link Function11} to compute the cache key.
     * @return The wrapped {@link Function11}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function,
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction) {
        return function11(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function11} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function11} to memoize.
     * @param keyFunction The {@link Function11} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function11}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function,
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function11Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function12} to memoize.
     * @return The wrapped {@link Function12}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function) {
        return function12(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function12} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function12}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function12(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function12} to memoize.
     * @param keyFunction The {@link Function12} to compute the cache key.
     * @return The wrapped {@link Function12}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function,
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction) {
        return function12(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function12} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function12} to memoize.
     * @param keyFunction The {@link Function12} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function12}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function,
            final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function12Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function13} to memoize.
     * @return The wrapped {@link Function13}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function) {
        return function13(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function13} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function13}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function13(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function13} to memoize.
     * @param keyFunction The {@link Function13} to compute the cache key.
     * @return The wrapped {@link Function13}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function,
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction) {
        return function13(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function13} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function13} to memoize.
     * @param keyFunction The {@link Function13} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function13}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function,
            final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function13Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function14} to memoize.
     * @return The wrapped {@link Function14}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function) {
        return function14(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function14} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function14}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function14(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function14} to memoize.
     * @param keyFunction The {@link Function14} to compute the cache key.
     * @return The wrapped {@link Function14}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function,
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction) {
        return function14(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function14} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function14} to memoize.
     * @param keyFunction The {@link Function14} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function14}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function,
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function14Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function15} to memoize.
     * @return The wrapped {@link Function15}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function) {
        return function15(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function15} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function15}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function15(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function15} to memoize.
     * @param keyFunction The {@link Function15} to compute the cache key.
     * @return The wrapped {@link Function15}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function,
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction) {
        return function15(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function15} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function15} to memoize.
     * @param keyFunction The {@link Function15} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function15}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function,
            final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function15Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

    /**
     * <p>
     * Memoizes a {@link Function16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param <TYPE16> The type of the sixteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function16} to memoize.
     * @return The wrapped {@link Function16}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function) {
        return function16(function, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10> The type of the tenth parameter.
     * @param <TYPE11> The type of the eleventh parameter.
     * @param <TYPE12> The type of the twelfth parameter.
     * @param <TYPE13> The type of the thirteenth parameter.
     * @param <TYPE14> The type of the fourteenth parameter.
     * @param <TYPE15> The type of the fifteenth parameter.
     * @param <TYPE16> The type of the sixteenth parameter.
     * @param <OUTPUT> The type of the output/cache value.
     * @param function The {@link Function16} to memoize.
     * @param cache    The {@link Map} based cache to use.
     * @return The wrapped {@link Function16}.
     */
    @CheckReturnValue
    public static <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function,
            final Map<String, OUTPUT> cache) {
        return function16(function, MemoizationDefaults::hashCodes, cache);
    }

    /**
     * <p>
     * Memoizes a {@link Function16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param <TYPE16>    The type of the sixteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function16} to memoize.
     * @param keyFunction The {@link Function16} to compute the cache key.
     * @return The wrapped {@link Function16}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function,
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction) {
        return function16(function, keyFunction, emptyMap());
    }

    /**
     * <p>
     * Memoizes a {@link Function16} in a {@link java.util.concurrent.ConcurrentMap}.
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
     * @param <TYPE10>    The type of the tenth parameter.
     * @param <TYPE11>    The type of the eleventh parameter.
     * @param <TYPE12>    The type of the twelfth parameter.
     * @param <TYPE13>    The type of the thirteenth parameter.
     * @param <TYPE14>    The type of the fourteenth parameter.
     * @param <TYPE15>    The type of the fifteenth parameter.
     * @param <TYPE16>    The type of the sixteenth parameter.
     * @param <OUTPUT>    The type of the output/cache value.
     * @param function    The {@link Function16} to memoize.
     * @param keyFunction The {@link Function16} to compute the cache key.
     * @param cache       The {@link Map} based cache to use.
     * @return The wrapped {@link Function16}.
     */
    @CheckReturnValue
    public static <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function,
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction,
            final Map<KEY, OUTPUT> cache) {
        return new Function16Memoizer<>(asConcurrentMap(cache), keyFunction, function);
    }

}
