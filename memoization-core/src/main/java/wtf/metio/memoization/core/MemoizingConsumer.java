/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @param <KEY>   The type of the key to use for memoization.
 * @param <INPUT> The type of the values to memoize.
 */
public interface MemoizingConsumer<KEY, INPUT> extends Consumer<INPUT> {

    /**
     * @return The Function used to calculate the memoization key.
     */
    Function<INPUT, KEY> getKeyFunction();

    /**
     * @return The consumer to memoize/wrap.
     */
    Consumer<INPUT> consumer();

    /**
     * @return The {@link BiFunction} that controls how to memoize the actual {@link Consumer}.
     */
    BiFunction<KEY, Function<KEY, INPUT>, INPUT> getMemoizingFunction();

    @Override
    default void accept(final INPUT input) {
        final KEY key = getKeyFunction().apply(input);
        final BiFunction<KEY, Function<KEY, INPUT>, INPUT> memoizer = Objects.requireNonNull(getMemoizingFunction());

        memoizer.apply(key, givenKey -> {
            consumer().accept(input);
            return input;
        });
    }

}
