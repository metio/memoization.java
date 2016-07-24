package de.xn__ho_hia.memoization.caffeine;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import de.xn__ho_hia.memoization.map.MapMemoization;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 *
 * @see Function
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

}
