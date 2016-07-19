package de.xn__ho_hia.memoization.caffeine;

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
     * Memoizes a {@link Function} in a Caffeine {@link Cache}.
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

    private CaffeineMemoization() {
        // factory class
    }

}
