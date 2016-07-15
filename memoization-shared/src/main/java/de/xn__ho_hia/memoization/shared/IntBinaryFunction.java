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
