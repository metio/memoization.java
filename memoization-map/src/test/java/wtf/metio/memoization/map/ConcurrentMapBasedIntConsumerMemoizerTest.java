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
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

class ConcurrentMapBasedIntConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = System.out::println;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Integer, Integer> cache = null;
        final IntConsumer consumer = System.out::println;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = null;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = System.out::println;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = System.out::println;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = Mockito.mock(IntConsumer.class);
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123);
        Mockito.verify(consumer).accept(123);
    }

}
