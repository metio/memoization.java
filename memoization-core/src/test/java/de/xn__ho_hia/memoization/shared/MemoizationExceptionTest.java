/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class MemoizationExceptionTest {

    /**
    *
    */
    @Test
    public void shouldWrapThrowable() {
        // given
        final Throwable throwable = new RuntimeException();

        // when
        final MemoizationException exception = new MemoizationException(throwable);

        // then
        Assert.assertNotNull("Wrapped exception is NULL", exception);
    }

    /**
    *
    */
    @Test
    public void shouldAcceptMessage() {
        // given
        final String message = "kaboom";

        // when
        final MemoizationException exception = new MemoizationException(message);

        // then
        Assert.assertNotNull("Wrapped exception is NULL", exception);
    }

}
