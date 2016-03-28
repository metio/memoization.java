package com.github.sebhoss.utils.memoization.guava;

import static com.github.sebhoss.utils.memoization.shared.MemoizationDefaults.defaultSupplierKey;
import static com.github.sebhoss.utils.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static java.util.function.Function.identity;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Factory for lightweight wrappers that store the result of a potentially expensive function call.
 * 
 * @see Supplier
 * @see Function
 * @see BiFunction
 * @see Consumer
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">Wikipedia: Memoization</a>
 */
public final class GuavaMemoization {
	
	private static final long DEFAULT_MAXIMUM_CACHE_SIZE = 1000L;

	/**
	 * Memoizes a {@link Supplier} in a Guava {@link Cache}.
	 * 
	 * @param supplier The {@link Supplier} to memoize.
	 * @return The wrapped {@link Supplier}.
	 */
	public static final <VALUE> Supplier<VALUE> memoize(final Supplier<VALUE> supplier) {
		return memoize(supplier, defaultSupplierKey());
	}

	/**
	 * Memoizes a {@link Supplier} in a Guava {@link Cache}. Saves the result under a specific cache key
	 * provided by a key-supplier.
	 * 
	 * @param supplier The {@link Supplier} to memoize.
	 * @param keySupplier The {@link Supplier} for the cache key.
	 * @return The wrapped {@link Supplier}.
	 */
	public static final <KEY, VALUE> Supplier<VALUE> memoize(
			final Supplier<VALUE> supplier,
			final Supplier<KEY> keySupplier) {
		return memoize(keySupplier, CacheBuilder.newBuilder()
			       .maximumSize(1)
			       .build(new SupplierBasedCacheLoader<>(supplier)));
	}

	/**
	 * Memoizes a {@link Supplier} in a previously constructed Guava {@link LoadingCache}. Saves the result
	 * under a specific cache key provided by a key-supplier.
	 * 
	 * @param keySupplier The {@link Supplier} for the cache key.
	 * @param cache The {@link LoadingCache} to use.
	 * @return The wrapped {@link Supplier}.
	 */
	public static final <KEY, VALUE> Supplier<VALUE> memoize(
			final Supplier<KEY> keySupplier,
			final LoadingCache<KEY, VALUE> cache) {
		return new GuavaCacheBasedSupplierMemoizer<>(cache, keySupplier);
	}

	/**
	 * Memoizes a {@link Function} in a Guava {@link Cache}.
	 * 
	 * @param function The {@link Function} to memoize.
	 * @return The wrapped {@link Function}.
	 */
	public static final <KEY, VALUE> Function<KEY, VALUE> memoize(final Function<KEY, VALUE> function) {
		return memoize(function, DEFAULT_MAXIMUM_CACHE_SIZE);
	}

	/**
	 * Memoizes a {@link Function} in a Guava {@link Cache}. Restricts the number of computed values to cache.
	 * 
	 * @param function The {@link Function} to memoize.
	 * @param maximumCacheSize The maximum number of computed values to cache.
	 * @return The wrapped {@link Function}.
	 */
	public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
			final Function<KEY, VALUE> function,
			final long maximumCacheSize) {
		return memoize(new FunctionBasedCacheLoader<>(function), maximumCacheSize);
	}

	/**
	 * Memoizes a {@link Function} in a previously constructed Guava {@link CacheLoader}. Restricts the number
	 * of computed values to cache.
	 * 
	 * @param loader The {@link CacheLoader} to use.
	 * @param maximumCacheSize The maximum number of computed values to cache.
	 * @return The wrapped {@link Function}.
	 */
	public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
			final CacheLoader<KEY, VALUE> loader,
			final long maximumCacheSize) {
		return memoize(CacheBuilder.newBuilder()
			       .maximumSize(maximumCacheSize)
			       .build(loader));
	}

	/**
	 * Memoizes a {@link Function} in a previously constructed Guava {@link LoadingCache}.
	 * 
	 * @param cache The {@link LoadingCache} to use.
	 * @return The wrapped {@link Function}.
	 */
	public static final <KEY, VALUE> Function<KEY, VALUE> memoize(
			final LoadingCache<KEY, VALUE> cache) {
		return new GuavaCacheBasedFunctionMemoizer<>(cache);
	}

	/**
	 * Memoizes a {@link BiFunction} in a Guava {@link Cache}.
	 * 
	 * @param biFunction The {@link BiFunction} to memoize.
	 * @return The wrapped {@link BiFunction}.
	 */
	public static final <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
			final BiFunction<FIRST, SECOND, VALUE> biFunction) {
		return memoize(biFunction, hashCodeKeyFunction());
	}

	/**
	 * Memoizes a {@link BiFunction} in a Guava {@link Cache}. Saves results under a specific cache key
	 * computed by a key-function.
	 * 
	 * @param biFunction The {@link BiFunction} to memoize.
	 * @param keyFunction The {@link BiFunction} to compute the cache key.
	 * @return The wrapped {@link BiFunction}.
	 */
	public static final <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
			final BiFunction<FIRST, SECOND, VALUE> biFunction,
			final BiFunction<FIRST, SECOND, KEY> keyFunction) {
		return memoize(biFunction, keyFunction, DEFAULT_MAXIMUM_CACHE_SIZE);
	}

	/**
	 * Memoizes a {@link BiFunction} in a Guava {@link Cache}. Saves results under a specific cache key
	 * computed by a key-function. Restricts the number of computed values to cache.
	 * 
	 * @param biFunction The {@link BiFunction} to memoize.
	 * @param keyFunction The {@link BiFunction} to compute the cache key.
	 * @param maximumCacheSize The maximum number of computed values to cache.
	 * @return The wrapped {@link BiFunction}.
	 */
	public static final <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
			final BiFunction<FIRST, SECOND, VALUE> biFunction,
			final BiFunction<FIRST, SECOND, KEY> keyFunction,
			final long maximumCacheSize) {
		return memoize(biFunction, keyFunction, CacheBuilder.newBuilder()
			       .maximumSize(maximumCacheSize)
			       .build());
	}

	/**
	 * Memoizes a {@link BiFunction} in a previously constructed Guava {@link Cache}.
	 * 
	 * @param biFunction The {@link BiFunction} to memoize.
	 * @param cache The {@link Cache} to use.
	 * @return The wrapped {@link BiFunction}.
	 */
	public static final <FIRST, SECOND, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
			final BiFunction<FIRST, SECOND, VALUE> biFunction,
			final Cache<String, VALUE> cache) {
		return memoize(biFunction, hashCodeKeyFunction(), cache);
	}

	/**
	 * Memoizes a {@link BiFunction} in a previously constructed Guava {@link Cache}. Saves results under
	 * a specific cache key computed by a key-function.
	 * 
	 * @param biFunction The {@link BiFunction} to memoize.
	 * @param keyFunction The {@link BiFunction} to compute the cache key.
	 * @param cache The {@link Cache} to use.
	 * @return The wrapped {@link BiFunction}.
	 */
	public static final <FIRST, SECOND, KEY, VALUE> BiFunction<FIRST, SECOND, VALUE> memoize(
			final BiFunction<FIRST, SECOND, VALUE> biFunction,
			final BiFunction<FIRST, SECOND, KEY> keyFunction,
			final Cache<KEY, VALUE> cache) {
		return new GuavaCacheBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);
	}

	/**
	 * Memoizes a {@link Consumer} in a Guava {@link Cache}.
	 * 
	 * @param consumer The {@link Consumer} to memoize.
	 * @return The wrapped {@link Consumer}.
	 */
	public static final <VALUE> Consumer<VALUE> memoize(final Consumer<VALUE> consumer) {
		return memoize(consumer, DEFAULT_MAXIMUM_CACHE_SIZE);
	}

	/**
	 * Memoizes a {@link Consumer} in a Guava {@link Cache}. Restricts the number of computed values to cache.
	 * 
	 * @param consumer The {@link Consumer} to memoize.
	 * @param maximumCacheSize The maximum number of computed values to cache.
	 * @return The wrapped {@link Consumer}.
	 */
	public static final <VALUE> Consumer<VALUE> memoize(
			final Consumer<VALUE> consumer,
			final long maximumCacheSize) {
		return memoize(consumer, CacheBuilder.newBuilder()
			       .maximumSize(maximumCacheSize)
			       .build());
	}

	/**
	 * Memoizes a {@link Consumer} in a previously constructed {@link Cache}.
	 * 
	 * @param consumer The {@link Consumer} to memoize.
	 * @param cache The {@link Cache} to use.
	 * @return The wrapped {@link Consumer}.
	 */
	public static final <VALUE> Consumer<VALUE> memoize(
			final Consumer<VALUE> consumer,
			final Cache<VALUE, VALUE> cache) {
		return new GuavaCacheBasedConsumerMemoizer<>(cache, identity(), consumer);
	}

	private GuavaMemoization() {
		// factory class
	}

}
