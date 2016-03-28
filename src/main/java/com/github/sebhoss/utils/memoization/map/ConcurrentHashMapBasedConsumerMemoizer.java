package com.github.sebhoss.utils.memoization.map;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import com.github.sebhoss.utils.memoization.shared.MemoizingConsumer;

final class ConcurrentHashMapBasedConsumerMemoizer<KEY, VALUE> extends AbstractConcurrentHashMapBasedMemoizer<KEY, VALUE> 
		implements MemoizingConsumer<KEY, VALUE> {

	private final Function<VALUE, KEY> keyFunction;
	private final Consumer<VALUE> consumer;

	ConcurrentHashMapBasedConsumerMemoizer(
			final Map<KEY, VALUE> preComputedValues,
			final Function<VALUE, KEY> keyFunction,
			final Consumer<VALUE> consumer) {
		super(preComputedValues);
		this.keyFunction = keyFunction;
		this.consumer = consumer;
	}

	@Override
	public Function<VALUE, KEY> getKeyFunction() {
		return keyFunction;
	}

	@Override
	public Consumer<VALUE> getConsumer() {
		return consumer;
	}

	@Override
	public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
		return this::computeIfAbsent;
	}

}
