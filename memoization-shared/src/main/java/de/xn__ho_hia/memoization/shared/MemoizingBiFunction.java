/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @param <FIRST>
 *            The type of the first input parameter.
 * @param <SECOND>
 *            The type of the second input parameter.
 * @param <KEY>
 *            The type of the key to use for memoization.
 * @param <VALUE>
 *            The type of the value to calculate.
 */
public interface MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> extends BiFunction<FIRST, SECOND, VALUE> {

    /**
     * @return The function to calculate the key.
     */
    BiFunction<FIRST, SECOND, KEY> getKeyFunction();

    /**
     * @return The {@link BiFunction} that controls how to memoize the actual {@link BiFunction}.
     */
    BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction();

    /**
     * @return The {@link BiFunction} to wrap/memoize.
     */
    BiFunction<FIRST, SECOND, VALUE> getBiFunction();

    @Override
    default VALUE apply(final FIRST first, final SECOND second) {
        final KEY key = getKeyFunction().apply(first, second);
        final BiFunction<KEY, Function<KEY, VALUE>, VALUE> memoizer = Objects.requireNonNull(getMemoizingFunction());
        return memoizer.apply(key, givenKey -> getBiFunction().apply(first, second));
    }

}
