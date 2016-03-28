package com.github.sebhoss.utils.memoization.shared;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface MemoizingConsumer<KEY, VALUE> extends Consumer<VALUE> {

	Function<VALUE, KEY> getKeyFunction();
	Consumer<VALUE> getConsumer();
	BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction();

	default void accept(final VALUE input) {
		final KEY key = getKeyFunction().apply(input);
		getMemoizingFunction().apply(key, givenKey -> {
			getConsumer().accept(input);
			return input;
		});
	}

}
