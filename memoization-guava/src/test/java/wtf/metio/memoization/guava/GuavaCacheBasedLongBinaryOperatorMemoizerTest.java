/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.LongBinaryFunction;
import wtf.metio.memoization.core.MemoizationException;

import java.util.concurrent.ExecutionException;
import java.util.function.LongBinaryOperator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedLongBinaryOperatorMemoizerTest {

    @Test
    void shouldAcceptLoadingCache() {
        // given
        final LongBinaryOperator function = (a, b) -> 123;
        final LongBinaryFunction<String> keyFunction = (a, b) -> "key";
        final Cache<String, Long> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final LongBinaryOperator function = (a, b) -> 123;
        final LongBinaryFunction<String> keyFunction = (a, b) -> "key";
        final Cache<String, Long> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123, memoizer.applyAsLong(123, 789));
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final LongBinaryFunction<String> keyFunction = (a, b) -> "key";
        final Cache<String, Long> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, null);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.applyAsLong(123, 789));
    }

}
