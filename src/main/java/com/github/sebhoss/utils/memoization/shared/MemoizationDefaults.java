package com.github.sebhoss.utils.memoization.shared;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class MemoizationDefaults {

	public static Supplier<String> defaultSupplierKey() {
		return () -> "SUPPLIED";
	}

	public static <T, U> BiFunction<T, U, String> hashCodeKeyFunction() {
		return (first, second) -> {
			int firstHashCode = Objects.hashCode(first);
			int secondHashCode = Objects.hashCode(second);
			return firstHashCode + " " + secondHashCode;
		};
	}

	private MemoizationDefaults() {
		// utility class
	}

}
