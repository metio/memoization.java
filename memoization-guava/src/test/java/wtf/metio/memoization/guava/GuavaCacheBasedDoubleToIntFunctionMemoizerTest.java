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
import java.util.function.DoubleToIntFunction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedDoubleToIntFunctionMemoizerTest {

    @Test
    void shouldAcceptLoadingCache() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt(789D));
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Integer> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, null);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.applyAsInt(789D));
    }

}
