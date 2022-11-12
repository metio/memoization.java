/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

/**
 * @param <RESULT> The resulting type.
 */
@FunctionalInterface
interface DoubleBinaryFunction<RESULT> {

    /**
     * @param first  The first double to apply.
     * @param second The second double to apply.
     * @return The result of applying both doubles.
     */
    RESULT apply(double first, double second);
}
