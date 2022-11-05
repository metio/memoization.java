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
import wtf.metio.memoization.core.DoubleBinaryFunction;
import wtf.metio.memoization.core.MemoizationException;

import java.util.concurrent.ExecutionException;
import java.util.function.DoubleBinaryOperator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedDoubleBinaryOperatorMemoizerTest {

    @Test
    void shouldAcceptLoadingCache() {
        // given
        final DoubleBinaryOperator function = (a, b) -> 123.456D;
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final DoubleBinaryOperator function = (a, b) -> 123.456D;
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123.456D, memoizer.applyAsDouble(123.456D, 789.123D), 0.0D);
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";
        final Cache<String, Double> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, null);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.applyAsDouble(123.456D, 789.123D));
    }

}
