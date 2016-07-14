package de.xn__ho_hia.utils.memoization.map;

import static de.xn__ho_hia.utils.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.utils.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import de.xn__ho_hia.utils.memoization.shared.MemoizationDefaults;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 *
 * @see BiConsumer
 * @see BiFunction
 * @see BiPredicate
 * @see BooleanSupplier
 * @see Consumer
 * @see DoubleConsumer
 * @see DoublePredicate
 * @see DoubleSupplier
 * @see Function
 * @see IntPredicate
 * @see IntSupplier
 * @see LongPredicate
 * @see LongSupplier
 * @see Predicate
 * @see Supplier
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
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static BooleanSupplier memoize(final BooleanSupplier supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves
     * the result under a specific cache key provided by a key-supplier.
     *
     * @param supplier
     *            The {@link BooleanSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier memoize(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return memoize(supplier, keySupplier, emptyMap());
    }

    /**
     * Memoizes a {@link BooleanSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves
     * the result under a specific cache key provided by a key-supplier. Skips previously computed values.
     *
     * @param supplier
     *            The {@link BooleanSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link BooleanSupplier}.
     */
    public static <KEY> BooleanSupplier memoize(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Boolean> preComputedValues) {
        return new ConcurrentHashMapBasedBooleanSupplierMemoizer<>(preComputedValues, keySupplier, supplier);
    }

    /**
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static DoubleSupplier memoize(final DoubleSupplier supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves
     * the result under a specific cache key provided by a key-supplier.
     *
     * @param supplier
     *            The {@link DoubleSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier memoize(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return memoize(supplier, keySupplier, emptyMap());
    }

    /**
     * Memoizes a {@link DoubleSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves
     * the result under a specific cache key provided by a key-supplier. Skips previously computed values.
     *
     * @param supplier
     *            The {@link DoubleSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link DoubleSupplier}.
     */
    public static <KEY> DoubleSupplier memoize(
            final DoubleSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Double> preComputedValues) {
        return new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(preComputedValues, keySupplier, supplier);
    }

    /**
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link IntSupplier}.
     */
    public static IntSupplier memoize(final IntSupplier supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier.
     *
     * @param supplier
     *            The {@link IntSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier memoize(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return memoize(supplier, keySupplier, emptyMap());
    }

    /**
     * Memoizes a {@link IntSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier. Skips previously computed values.
     *
     * @param supplier
     *            The {@link IntSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link IntSupplier}.
     */
    public static <KEY> IntSupplier memoize(
            final IntSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Integer> preComputedValues) {
        return new ConcurrentHashMapBasedIntSupplierMemoizer<>(preComputedValues, keySupplier, supplier);
    }

    /**
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param supplier
     *            The {@link Supplier} to memoize.
     * @return The wrapped {@link LongSupplier}.
     */
    public static LongSupplier memoize(final LongSupplier supplier) {
        return memoize(supplier, defaultKeySupplier());
    }

    /**
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier.
     *
     * @param supplier
     *            The {@link LongSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier memoize(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return memoize(supplier, keySupplier, emptyMap());
    }

    /**
     * Memoizes a {@link LongSupplier} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Saves the
     * result under a specific cache key provided by a key-supplier. Skips previously computed values.
     *
     * @param supplier
     *            The {@link LongSupplier} to memoize.
     * @param keySupplier
     *            The {@link Supplier} for the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link LongSupplier}.
     */
    public static <KEY> LongSupplier memoize(
            final LongSupplier supplier,
            final Supplier<KEY> keySupplier,
            final Map<KEY, Long> preComputedValues) {
        return new ConcurrentHashMapBasedLongSupplierMemoizer<>(preComputedValues, keySupplier, supplier);
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

    /**
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link DoubleConsumer} to memoize.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer memoize(final DoubleConsumer consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link DoubleConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link DoubleConsumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link DoubleConsumer}.
     */
    public static DoubleConsumer memoize(
            final DoubleConsumer consumer,
            final Map<Double, Double> preComputedValues) {
        return new ConcurrentHashMapBasedDoubleConsumerMemoizer(preComputedValues, consumer);
    }

    /**
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link IntConsumer} to memoize.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer memoize(final IntConsumer consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link IntConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link IntConsumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link IntConsumer}.
     */
    public static IntConsumer memoize(
            final IntConsumer consumer,
            final Map<Integer, Integer> preComputedValues) {
        return new ConcurrentHashMapBasedIntConsumerMemoizer(preComputedValues, consumer);
    }

    /**
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link LongConsumer} to memoize.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer memoize(final LongConsumer consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link LongConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link LongConsumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link LongConsumer}.
     */
    public static LongConsumer memoize(
            final LongConsumer consumer,
            final Map<Long, Long> preComputedValues) {
        return new ConcurrentHashMapBasedLongConsumerMemoizer(preComputedValues, consumer);
    }

    /**
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <VALUE> ObjDoubleConsumer<VALUE> memoize(final ObjDoubleConsumer<VALUE> consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link ObjDoubleConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link ObjDoubleConsumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link ObjDoubleConsumer}.
     */
    public static <VALUE> ObjDoubleConsumer<VALUE> memoize(
            final ObjDoubleConsumer<VALUE> consumer,
            final Map<String, String> preComputedValues) {
        return new ConcurrentHashMapBasedObjDoubleConsumerMemoizer<>(preComputedValues,
                MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction(), consumer);
    }

    /**
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link ObjIntConsumer} to memoize.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <VALUE> ObjIntConsumer<VALUE> memoize(final ObjIntConsumer<VALUE> consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link ObjIntConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link ObjIntConsumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link ObjIntConsumer}.
     */
    public static <VALUE> ObjIntConsumer<VALUE> memoize(
            final ObjIntConsumer<VALUE> consumer,
            final Map<String, String> preComputedValues) {
        return new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(preComputedValues,
                MemoizationDefaults.objIntConsumerHashCodeKeyFunction(), consumer);
    }

    /**
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param consumer
     *            The {@link ObjLongConsumer} to memoize.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <VALUE> ObjLongConsumer<VALUE> memoize(final ObjLongConsumer<VALUE> consumer) {
        return memoize(consumer, emptyMap());
    }

    /**
     * Memoizes a {@link ObjLongConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param consumer
     *            The {@link ObjLongConsumer} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link ObjLongConsumer}.
     */
    public static <VALUE> ObjLongConsumer<VALUE> memoize(
            final ObjLongConsumer<VALUE> consumer,
            final Map<String, String> preComputedValues) {
        return new ConcurrentHashMapBasedObjLongConsumerMemoizer<>(preComputedValues,
                MemoizationDefaults.objLongConsumerHashCodeKeyFunction(), consumer);
    }

    /**
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param biConsumer
     *            The {@link BiConsumer} to memoize.
     * @return The wrapped {@link BiConsumer}.
     */
    public static <FIRST, SECOND> BiConsumer<FIRST, SECOND> memoize(final BiConsumer<FIRST, SECOND> biConsumer) {
        return memoize(biConsumer, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * Memoizes a {@link BiConsumer} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param biConsumer
     *            The {@link BiConsumer} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link BiConsumer}.
     */
    public static <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> memoize(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, KEY> preComputedValues) {
        return new ConcurrentHashMapBasedBiConsumerMemoizer<>(preComputedValues, keyFunction, biConsumer);
    }

    /**
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param predicate
     *            The {@link Predicate} to memoize.
     * @return The wrapped {@link Predicate}.
     */
    public static <VALUE> Predicate<VALUE> memoize(final Predicate<VALUE> predicate) {
        return memoize(predicate, emptyMap());
    }

    /**
     * Memoizes a {@link Predicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param predicate
     *            The {@link Predicate} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link Predicate}.
     */
    public static <VALUE> Predicate<VALUE> memoize(
            final Predicate<VALUE> predicate,
            final Map<VALUE, Boolean> preComputedValues) {
        return new ConcurrentHashMapBasedPredicateMemoizer<>(preComputedValues, predicate);
    }

    /**
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param predicate
     *            The {@link BiPredicate} to memoize.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND> BiPredicate<FIRST, SECOND> memoize(final BiPredicate<FIRST, SECOND> predicate) {
        return memoize(predicate, hashCodeKeyFunction(), emptyMap());
    }

    /**
     * Memoizes a {@link BiPredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param predicate
     *            The {@link BiPredicate} to memoize.
     * @param keyFunction
     *            The {@link BiFunction} to compute the cache key.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link BiPredicate}.
     */
    public static <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> memoize(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Map<KEY, Boolean> preComputedValues) {
        return new ConcurrentHashMapBasedBiPredicateMemoizer<>(preComputedValues, keyFunction, predicate);
    }

    /**
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param predicate
     *            The {@link IntPredicate} to memoize.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate memoize(final IntPredicate predicate) {
        return memoize(predicate, emptyMap());
    }

    /**
     * Memoizes a {@link IntPredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param predicate
     *            The {@link IntPredicate} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link IntPredicate}.
     */
    public static IntPredicate memoize(
            final IntPredicate predicate,
            final Map<Integer, Boolean> preComputedValues) {
        return new ConcurrentHashMapBasedIntPredicateMemoizer(preComputedValues, predicate);
    }

    /**
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param predicate
     *            The {@link LongPredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static LongPredicate memoize(final LongPredicate predicate) {
        return memoize(predicate, emptyMap());
    }

    /**
     * Memoizes a {@link LongPredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param predicate
     *            The {@link LongPredicate} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link LongPredicate}.
     */
    public static LongPredicate memoize(
            final LongPredicate predicate,
            final Map<Long, Boolean> preComputedValues) {
        return new ConcurrentHashMapBasedLongPredicateMemoizer(preComputedValues, predicate);
    }

    /**
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}.
     *
     * @param predicate
     *            The {@link DoublePredicate} to memoize.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate memoize(final DoublePredicate predicate) {
        return memoize(predicate, emptyMap());
    }

    /**
     * Memoizes a {@link DoublePredicate} in a {@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}. Skips
     * previously computed values.
     *
     * @param predicate
     *            The {@link DoublePredicate} to memoize.
     * @param preComputedValues
     *            Map of already computed values.
     * @return The wrapped {@link DoublePredicate}.
     */
    public static DoublePredicate memoize(
            final DoublePredicate predicate,
            final Map<Double, Boolean> preComputedValues) {
        return new ConcurrentHashMapBasedDoublePredicateMemoizer(preComputedValues, predicate);
    }

}
