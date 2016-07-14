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
