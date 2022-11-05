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
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedToDoubleFunctionMemoizerTest {

    @Test
    void shouldAcceptLoadingCache() {
        // given
        final ToDoubleFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final ToDoubleFunction<String> function = a -> 123.456D;
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123.456D, memoizer.applyAsDouble("test"), 0.0D);
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Double> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedToDoubleFunctionMemoizer<>(cache, keyFunction, null);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.applyAsDouble("test"));
    }

}
