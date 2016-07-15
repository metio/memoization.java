/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 */
public class MemoizationExceptionTest {

    /**
     *
     */
    @SuppressWarnings({ "static-method", "nls" })
    @Test
    public void shouldWrapThrowable() {
        // given
        final Throwable throwable = new RuntimeException();

        // when
        final MemoizationException exception = new MemoizationException(throwable);

        // then
        Assert.assertNotNull("Wrapped exception is NULL", exception);
    }

}
