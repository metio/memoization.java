package de.xn__ho_hia.memoization.shared;

/**
 * @param <VALUE>
 *            The type of the first function argument
 * @param <RESULT>
 *            The type of the result.
 */
@FunctionalInterface
public interface ObjLongFunction<VALUE, RESULT> {

    /**
     * @param value
     *            The value to apply.
     * @param i
     *            The long value to apply.
     * @return The result of applying the given value & long.
     */
    RESULT apply(VALUE value, long i);

}
