package de.xn__ho_hia.memoization.shared;

/**
 * Simple wrapper in order to allow users to catch exceptions during memoization explicitly.
 */
public final class MemoizationException extends RuntimeException {

    private static final long serialVersionUID = 652523829901288629L;

    /**
     * @param throwable
     *            The exception to wrap.
     */
    public MemoizationException(final Throwable throwable) {
        super(throwable);
    }

}