/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

/**
 * @param <RESULT> The resulting type.
 */
@FunctionalInterface
public interface IntBinaryFunction<RESULT> {

    /**
     * @param first  The first int to apply.
     * @param second The second int to apply.
     * @return The result of applying both ints.
     */
    RESULT apply(int first, int second);
}
