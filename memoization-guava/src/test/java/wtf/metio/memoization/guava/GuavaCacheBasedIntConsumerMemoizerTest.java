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
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedIntConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final IntFunction<String> keyFunction = a -> "key";
        final IntConsumer consumer = System.out::println;
        final Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldConsumeGivenValue() {
        // given
        final IntFunction<String> keyFunction = a -> "key";
        final IntConsumer consumer = Mockito.mock(IntConsumer.class);
        final Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123);
        Mockito.verify(consumer).accept(123);
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final IntFunction<String> keyFunction = a -> "key";
        final IntConsumer consumer = System.out::println;
        final Cache<String, Integer> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.accept(123));
    }

}
