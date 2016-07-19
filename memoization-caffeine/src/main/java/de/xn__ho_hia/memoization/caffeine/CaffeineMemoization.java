package de.xn__ho_hia.memoization.caffeine;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 *
 * @see Function
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class CaffeineMemoization {

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
        return new CaffeineBasedFunctionMemoizer<>(cache, function);
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
        return new CaffeineBasedDoubleUnaryOperatorMemoizer(cache, operator);
    }

    private CaffeineMemoization() {
        // factory class
    }

}
