/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.core;

import java.io.Serial;

public final class WrappedThrowable extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1006891353167623975L;

    private final Throwable wrappedThrowable;

    public WrappedThrowable(final Throwable wrappedThrowable) {
        this.wrappedThrowable = wrappedThrowable;
    }

    public Throwable wrappedThrowable() {
        return wrappedThrowable;
    }

}
