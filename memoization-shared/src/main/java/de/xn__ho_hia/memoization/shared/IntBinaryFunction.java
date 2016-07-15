/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

/**
 * @param <RESULT>
 *            The resulting type.
 */
@FunctionalInterface
public interface IntBinaryFunction<RESULT> {

    /**
     * @param first
     *            The first int to apply.
     * @param second
     *            The second int to apply.
     * @return The result of applying both ints.
     */
    RESULT apply(int first, int second);
}
