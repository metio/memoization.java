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
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedDoubleConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final DoubleFunction<String> keyFunction = a -> "key";
        final DoubleConsumer consumer = System.out::println;
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldConsumeGivenValue() {
        // given
        final DoubleFunction<String> keyFunction = a -> "key";
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
        Mockito.verify(consumer).accept(123.456D);
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final DoubleFunction<String> keyFunction = a -> "key";
        final DoubleConsumer consumer = System.out::println;
        final Cache<String, Double> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.accept(123.456D));
    }

}
