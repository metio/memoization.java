/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

/**
 * @param <VALUE>  The type of the first function argument
 * @param <RESULT> The type of the result.
 */
@FunctionalInterface
public interface ObjIntFunction<VALUE, RESULT> {

    /**
     * @param value The value to apply.
     * @param i     The integer value to apply.
     * @return The result of applying the given value and integer.
     */
    RESULT apply(VALUE value, int i);

}
