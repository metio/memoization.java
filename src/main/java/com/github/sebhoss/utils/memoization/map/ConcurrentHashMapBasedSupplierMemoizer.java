package com.github.sebhoss.utils.memoization.map;

import java.util.Map;
import java.util.function.Supplier;

final class ConcurrentHashMapBasedSupplierMemoizer<KEY, VALUE> extends AbstractConcurrentHashMapBasedMemoizer<KEY, VALUE>
		implements Supplier<VALUE> {

	private final Supplier<KEY> keySupplier;
	private final Supplier<VALUE> supplier;

	ConcurrentHashMapBasedSupplierMemoizer(
			final Map<KEY, VALUE> precomputedValues,
			final Supplier<KEY> keySupplier,
			final Supplier<VALUE> supplier) {
		super(precomputedValues);
		this.keySupplier = keySupplier;
		this.supplier = supplier;
	}

	@Override
	public VALUE get() {
		return computeIfAbsent(keySupplier.get(), input -> supplier.get());
	}

}
