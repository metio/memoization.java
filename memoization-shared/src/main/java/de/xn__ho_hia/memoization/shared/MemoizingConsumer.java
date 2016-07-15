/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @param <KEY>
 *            The type of the key to use for memoization.
 * @param <VALUE>
 *            The type of the values to memoize.
 */
public interface MemoizingConsumer<KEY, VALUE> extends Consumer<VALUE> {

    /**
     * @return The Function used to calculate the memoization key.
     */
    Function<VALUE, KEY> getKeyFunction();

    /**
     * @return The consumer to memoize/wrap.
     */
    Consumer<VALUE> getConsumer();

    /**
     * @return The {@link BiFunction} that controls how to memoize the actual {@link Consumer}.
     */
    BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction();

    @Override
    default void accept(final VALUE input) {
        final KEY key = getKeyFunction().apply(input);
        final BiFunction<KEY, Function<KEY, VALUE>, VALUE> memoizer = Objects.requireNonNull(getMemoizingFunction());
        memoizer.apply(key, givenKey -> {
            getConsumer().accept(input);
            return input;
        });
    }

}
