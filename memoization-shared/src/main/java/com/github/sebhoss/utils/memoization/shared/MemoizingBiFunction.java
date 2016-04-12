package com.github.sebhoss.utils.memoization.shared;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> extends BiFunction<FIRST, SECOND, VALUE> {

	BiFunction<FIRST, SECOND, KEY> getKeyFunction();
	BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction();
	BiFunction<FIRST, SECOND, VALUE> getBiFunction();

	default VALUE apply(final FIRST first, final SECOND second) {
		final KEY key = getKeyFunction().apply(first, second);
		return getMemoizingFunction().apply(key, givenKey -> getBiFunction().apply(first, second));
	}

}
