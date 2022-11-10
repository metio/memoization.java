/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.core;

import java.io.Serial;

public final class WrappedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -509533184888867075L;

    private final Exception wrappedException;

    public WrappedException(final Exception wrappedException) {
        this.wrappedException = wrappedException;
    }

    public Exception wrappedException() {
        return wrappedException;
    }

}
