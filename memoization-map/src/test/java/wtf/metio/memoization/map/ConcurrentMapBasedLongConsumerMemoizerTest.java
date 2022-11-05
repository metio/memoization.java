/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;

class ConcurrentMapBasedLongConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = System.out::println;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Long, Long> cache = null;
        final LongConsumer consumer = System.out::println;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = System.out::println;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123L);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = System.out::println;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123L);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123L);
        Mockito.verify(consumer).accept(123L);
    }

}
