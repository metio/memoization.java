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
import wtf.metio.memoization.core.MemoizationException;

import java.util.concurrent.ExecutionException;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedIntUnaryOperatorMemoizerTest {

    @Test
    void shouldAcceptLoadingCache() {
        // given
        final IntUnaryOperator function = a -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;
        final Cache<Integer, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final IntUnaryOperator function = a -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;
        final Cache<Integer, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt(789));
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final IntFunction<Integer> keyFunction = Integer::valueOf;
        final Cache<Integer, Integer> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, null);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.applyAsInt(789));
    }

}
