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
import java.util.function.DoublePredicate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedDoublePredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndPredicate() {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTestGivenValue() {
        // given
        final DoublePredicate predicate = Mockito.mock(DoublePredicate.class);
        final DoubleFunction<String> keyFunction = a -> "key";
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        memoizer.test(123.456D);
        Mockito.verify(predicate).test(123.456D);
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";
        final Cache<String, Boolean> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertThrows(MemoizationException.class, () ->
        memoizer.test(123.456D));
    }

    @Test
    void shouldMemoizeResult() {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertTrue(memoizer.test(123.456D));
    }

}
