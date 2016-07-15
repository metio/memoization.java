/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

/**
 * @param <VALUE>
 *            The type of the first function argument
 * @param <RESULT>
 *            The type of the result.
 */
@FunctionalInterface
public interface ObjIntFunction<VALUE, RESULT> {

    /**
     * @param value
     *            The value to apply.
     * @param i
     *            The integer value to apply.
     * @return The result of applying the given value & integer.
     */
    RESULT apply(VALUE value, int i);

}
