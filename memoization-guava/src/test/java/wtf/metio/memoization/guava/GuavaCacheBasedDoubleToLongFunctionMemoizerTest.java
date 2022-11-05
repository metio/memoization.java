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
import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedDoubleToLongFunctionMemoizerTest {

    @Test
    void shouldAcceptLoadingCache() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Long> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Long> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123L, memoizer.applyAsLong(789D));
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Long> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, null);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.applyAsLong(789D));
    }

}
