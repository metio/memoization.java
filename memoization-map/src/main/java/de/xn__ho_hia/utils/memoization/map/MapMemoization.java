package de.xn__ho_hia.utils.memoization.map;

import static de.xn__ho_hia.utils.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.utils.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 *
 * @see Supplier
 * @see Function
 * @see BiFunction
 * @see Consumer
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class MapMemoization {

    private MapMemoization() {
        // factory class
    }

    /**
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link Supplier}.
     */
    public static <VALUE> Supplier<VALUE> memoize(final Supplier<VALUE> supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link Supplier}.
     */
    public static <KEY, VALUE> Supplier<VALUE> memoize(
            final Supplier<VALUE> supplier,
            final Supplier<KEY> keySupplier) {
        return memoize(supplier, keySupplier, emptyMap());
    }

    /**
     * Memoizes a {@link Supplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier. Skips previously computed values.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link Supplier}.
     */
    public static <KEY, VALUE> Supplier<VALUE> memoize(
            final Supplier<VALUE> supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, VALUE> preComputedValues) {
        return new ConcurrentHashMapBasedSupplierMemoizer<>(preComputedValues, keySupplier, supplier);
    }

    /**
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param function
     *            The {@link Function} to memoize.
     * @return The wrapped {@link Function}.
     */
    public static <KEY, VALUE> Function<KEY, VALUE> memoize(final Function<KEY, VALUE> function) {
        return memoize(function, emptyMap());
    }

    /**
     * Memoizes a {@link Function} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param function
     *            The {@link Function} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link Function}.
     */
    public static <KEY, VALUE> Function<KEY, VALUE> memoize(
            final Function<KEY, VALUE> function,
            final Map<KEY, VALUE> preComputedValues) {
        return new ConcurrentHashMapBasedFunctionMemoizer<>(preComputedValues, function);
    }

    /**
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        return memoize(biFunction, hashCodeKeyFunction());
    }

    /**
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier.
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return memoize(biFunction, keyFunction, emptyMap());
    }

    /**
     * Memoizes a {@link BiFunction} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier. Skips previously computed values.
     *
     * @param biFunction
     *            The {@link BiFunction} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link BiFunction}.
     */
    public static <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
            final BiFunction<FIRST, SECOND, VALUE> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, VALUE> preComputedValues) {
        return new ConcurrentHashMapBasedBiFunctionMemoizer<>(preComputedValues, keyFunction, biFunction);
    }

    /**
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link Consumer} to memoize.
     * @return The wrapped {@link Consumer}.
     */
    public static <VALUE> Consumer<VALUE> memoize(final Consumer<VALUE> consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link Consumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link Consumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link Consumer}.
     */
    public static <VALUE> Consumer<VALUE> memoize(
            final Consumer<VALUE> consumer,
            final Map<VALUE, VALUE> preComputedValues) {
        return new ConcurrentHashMapBasedConsumerMemoizer<>(preComputedValues, identity(), consumer);
    }

}
