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
import java.util.function.Consumer;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GuavaCacheBasedConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Consumer<String> consumer = System.out::println;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldConsumeGivenValue() {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Consumer<String> consumer = Mockito.mock(Consumer.class);
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("value");
        Mockito.verify(consumer).accept("value");
    }

    @Test
    void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Consumer<String> consumer = System.out::println;
        final Cache<String, String> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);

        // when
        final var memoizer = new GuavaCacheBasedConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertThrows(MemoizationException.class, () -> memoizer.accept("test"));
    }

}
