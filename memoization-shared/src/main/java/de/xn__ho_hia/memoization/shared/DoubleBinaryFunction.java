package de.xn__ho_hia.memoization.shared;

/**
 * @param <RESULT>
 *            The resulting type.
 */
@FunctionalInterface
public interface DoubleBinaryFunction<RESULT> {

    /**
     * @param first
     *            The first double to apply.
     * @param second
     *            The second double to apply.
     * @return The result of applying both doubles.
     */
    RESULT apply(double first, double second);
}
