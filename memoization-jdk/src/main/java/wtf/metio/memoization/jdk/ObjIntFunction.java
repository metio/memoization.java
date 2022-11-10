/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

/**
 * @param <VALUE>  The type of the first function argument
 * @param <RESULT> The type of the result.
 */
@FunctionalInterface
interface ObjIntFunction<VALUE, RESULT> {

    /**
     * @param value The value to apply.
     * @param i     The integer value to apply.
     * @return The result of applying the given value and integer.
     */
    RESULT apply(VALUE value, int i);

}
