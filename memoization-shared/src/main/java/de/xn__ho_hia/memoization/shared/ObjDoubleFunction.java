package de.xn__ho_hia.memoization.shared;

/**
 * @param <VALUE>
 *            The type of the first function argument
 * @param <RESULT>
 *            The type of the result.
 */
@FunctionalInterface
public interface ObjDoubleFunction<VALUE, RESULT> {

    /**
     * @param value
     *            The value to apply.
     * @param d
     *            The double value to apply.
     * @return The result of applying the given value & double.
     */
    RESULT apply(VALUE value, double d);

}
