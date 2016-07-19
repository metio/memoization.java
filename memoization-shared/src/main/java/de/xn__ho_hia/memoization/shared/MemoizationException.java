/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
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

    /**
     * @param string
     *            The exception message.
     */
    public MemoizationException(final String string) {
        super(string);
    }

}
