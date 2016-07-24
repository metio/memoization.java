package de.xn__ho_hia.memoization.caffeine;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import de.xn__ho_hia.memoization.map.MapMemoization;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 *
 * @see DoubleBinaryOperator
 * @see DoubleToIntFunction
 * @see DoubleToLongFunction
 * @see DoubleUnaryOperator
 * @see Function
 * @see IntBinaryOperator
 * @see IntToDoubleFunction
 * @see IntToLongFunction
 * @see IntUnaryOperator
 * @see ToDoubleBiFunction
 * @see ToDoubleFunction
 * @see ToIntBiFunction
 * @see ToIntFunction
 * @see ToLongBiFunction
 * @see ToLongFunction
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class CaffeineMemoization {

    private CaffeineMemoization() {
        // factory class
    }

    /**
     * Memoizes a {@link Function} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link Function} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static <KEY, VALUE> Function<KEY, VALUE> memoize(final Function<KEY, VALUE> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link Function} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link Function} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link Function}.
     */
    public static <KEY, VALUE> Function<KEY, VALUE> memoize(
            final Function<KEY, VALUE> function,
            final Cache<KEY, VALUE> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link DoubleToIntFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link DoubleToIntFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleToIntFunction memoize(final DoubleToIntFunction function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link DoubleToIntFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link DoubleToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToIntFunction}.
     */
    public static DoubleToIntFunction memoize(
            final DoubleToIntFunction function,
            final Cache<Double, Integer> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link DoubleToLongFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link DoubleToLongFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleToLongFunction memoize(final DoubleToLongFunction function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link DoubleToLongFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link DoubleToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleToLongFunction}.
     */
    public static DoubleToLongFunction memoize(
            final DoubleToLongFunction function,
            final Cache<Double, Long> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link DoubleUnaryOperator} in a Caffeine {@link Cache}.
     *
     * @param operator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleUnaryOperator memoize(final DoubleUnaryOperator operator) {
        return memoize(operator, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link DoubleUnaryOperator} in a pre-configured Caffeine {@link Cache}.
     *
     * @param operator
     *            The {@link DoubleUnaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleUnaryOperator}.
     */
    public static DoubleUnaryOperator memoize(
            final DoubleUnaryOperator operator,
            final Cache<Double, Double> cache) {
        return MapMemoization.memoize(operator, cache.asMap());
    }

    /**
     * Memoizes a {@link DoubleBinaryOperator} in a Caffeine {@link Cache}.
     *
     * @param operator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static DoubleBinaryOperator memoize(final DoubleBinaryOperator operator) {
        return memoize(operator, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link DoubleBinaryOperator} in a pre-configured Caffeine {@link Cache}.
     *
     * @param operator
     *            The {@link DoubleBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link DoubleBinaryOperator}.
     */
    public static DoubleBinaryOperator memoize(
            final DoubleBinaryOperator operator,
            final Cache<String, Double> cache) {
        return MapMemoization.memoize(operator, cache.asMap());
    }

    /**
     * Memoizes a {@link IntBinaryOperator} in a Caffeine {@link Cache}.
     *
     * @param operator
     *            The {@link IntBinaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntBinaryOperator memoize(final IntBinaryOperator operator) {
        return memoize(operator, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link IntBinaryOperator} in a pre-configured Caffeine {@link Cache}.
     *
     * @param operator
     *            The {@link IntBinaryOperator} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntBinaryOperator}.
     */
    public static IntBinaryOperator memoize(
            final IntBinaryOperator operator,
            final Cache<String, Integer> cache) {
        return MapMemoization.memoize(operator, cache.asMap());
    }

    /**
     * Memoizes a {@link IntToDoubleFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link IntToDoubleFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntToDoubleFunction memoize(final IntToDoubleFunction function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link IntToDoubleFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link IntToDoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntToDoubleFunction}.
     */
    public static IntToDoubleFunction memoize(
            final IntToDoubleFunction function,
            final Cache<Integer, Double> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link IntToLongFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link IntToLongFunction} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntToLongFunction memoize(final IntToLongFunction function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link IntToLongFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link IntToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntToLongFunction memoize(
            final IntToLongFunction function,
            final Cache<Integer, Long> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link IntUnaryOperator} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link IntUnaryOperator} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static IntUnaryOperator memoize(final IntUnaryOperator function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link IntToLongFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link IntToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link IntToLongFunction}.
     */
    public static IntUnaryOperator memoize(
            final IntUnaryOperator function,
            final Cache<Integer, Integer> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link ToDoubleBiFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> memoize(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToDoubleBiFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> memoize(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return memoize(function, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToDoubleBiFunction} in a pre-configured Caffeine {@link Cache}. Uses the default key function.
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> memoize(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final Cache<String, Double> cache) {
        return memoize(function, hashCodeKeyFunction(), cache);
    }

    /**
     * Memoizes a {@link ToDoubleBiFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToDoubleBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> memoize(
            final ToDoubleBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Double> cache) {
        return MapMemoization.memoize(function, keyFunction, cache.asMap());
    }

    /**
     * Memoizes a {@link ToIntBiFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> memoize(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToIntBiFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> memoize(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return memoize(function, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToIntBiFunction} in a pre-configured Caffeine {@link Cache}. Uses the default key function.
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> memoize(
            final ToIntBiFunction<FIRST, SECOND> function,
            final Cache<String, Integer> cache) {
        return memoize(function, hashCodeKeyFunction(), cache);
    }

    /**
     * Memoizes a {@link ToIntBiFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToIntBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> memoize(
            final ToIntBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Integer> cache) {
        return MapMemoization.memoize(function, keyFunction, cache.asMap());
    }

    /**
     * Memoizes a {@link ToLongBiFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> memoize(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToLongBiFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> memoize(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return memoize(function, keyFunction, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToLongBiFunction} in a pre-configured Caffeine {@link Cache}. Uses the default key function.
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> memoize(
            final ToLongBiFunction<FIRST, SECOND> function,
            final Cache<String, Long> cache) {
        return memoize(function, hashCodeKeyFunction(), cache);
    }

    /**
     * Memoizes a {@link ToLongBiFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToLongBiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongBiFunction}.
     */
    public static <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> memoize(
            final ToLongBiFunction<FIRST, SECOND> function,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Cache<KEY, Long> cache) {
        return MapMemoization.memoize(function, keyFunction, cache.asMap());
    }

    /**
     * Memoizes a {@link ToDoubleFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToDoubleFunction} to memoize.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <VALUE> ToDoubleFunction<VALUE> memoize(final ToDoubleFunction<VALUE> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToDoubleFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToDoubleFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToDoubleFunction}.
     */
    public static <VALUE> ToDoubleFunction<VALUE> memoize(
            final ToDoubleFunction<VALUE> function,
            final Cache<VALUE, Double> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link ToIntFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToIntFunction} to memoize.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <VALUE> ToIntFunction<VALUE> memoize(final ToIntFunction<VALUE> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToIntFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToIntFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToIntFunction}.
     */
    public static <VALUE> ToIntFunction<VALUE> memoize(
            final ToIntFunction<VALUE> function,
            final Cache<VALUE, Integer> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

    /**
     * Memoizes a {@link ToLongFunction} in a Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToLongFunction} to memoize.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <VALUE> ToLongFunction<VALUE> memoize(final ToLongFunction<VALUE> function) {
        return memoize(function, Caffeine.newBuilder().build());
    }

    /**
     * Memoizes a {@link ToLongFunction} in a pre-configured Caffeine {@link Cache}.
     *
     * @param function
     *            The {@link ToLongFunction} to memoize.
     * @param cache
     *            The {@link Cache} to use.
     * @return The wrapped {@link ToLongFunction}.
     */
    public static <VALUE> ToLongFunction<VALUE> memoize(
            final ToLongFunction<VALUE> function,
            final Cache<VALUE, Long> cache) {
        return MapMemoization.memoize(function, cache.asMap());
    }

}
