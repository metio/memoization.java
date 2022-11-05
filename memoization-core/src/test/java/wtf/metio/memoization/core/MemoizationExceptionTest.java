/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoizationExceptionTest {

    @Test
    void shouldWrapThrowable() {
        // given
        final Throwable throwable = new RuntimeException();

        // when
        final MemoizationException exception = new MemoizationException(throwable);

        // then
        Assertions.assertNotNull(exception);
    }

    @Test
    void shouldAcceptMessage() {
        // given
        final String message = "kaboom";

        // when
        final MemoizationException exception = new MemoizationException(message);

        // then
        Assertions.assertNotNull(exception);
    }

}
