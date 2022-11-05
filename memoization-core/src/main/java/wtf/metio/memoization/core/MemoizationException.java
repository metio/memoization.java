/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import java.io.Serial;

/**
 * Simple wrapper in order to allow users to catch exceptions during memoization explicitly.
 */
public final class MemoizationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 652523829901288629L;

    /**
     * @param throwable The exception to wrap.
     */
    public MemoizationException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * @param string The exception message.
     */
    public MemoizationException(final String string) {
        super(string);
    }

}
