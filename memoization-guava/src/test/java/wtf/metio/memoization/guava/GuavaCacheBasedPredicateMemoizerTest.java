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
import java.util.function.Predicate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedPredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndPredicate() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTestGivenValue() {
        // given
        final Predicate<String> predicate = Mockito.mock(Predicate.class);
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        memoizer.test("value");
        Mockito.verify(predicate).test("value");
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Boolean> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.test("test"));
    }

    @Test
    void shouldMemoizeResult() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = Function.identity();
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertTrue(memoizer.test("value"));
    }

}
