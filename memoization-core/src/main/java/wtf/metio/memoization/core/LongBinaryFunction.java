/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

/**
 * @param <RESULT> The resulting type.
 */
@FunctionalInterface
public interface LongBinaryFunction<RESULT> {

    /**
     * @param first  The first long to apply.
     * @param second The second long to apply.
     * @return The result of applying both longs.
     */
    RESULT apply(long first, long second);
}
