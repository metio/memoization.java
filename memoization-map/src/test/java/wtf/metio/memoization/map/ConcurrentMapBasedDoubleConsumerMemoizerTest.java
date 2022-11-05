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
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

class ConcurrentMapBasedDoubleConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Double> cache = null;
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    
    void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = null;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123.456D, memoizer.viewCacheForTest().keySet().iterator().next(), 0.0D);
        Assertions.assertEquals(123.456D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
        Mockito.verify(consumer).accept(123.456D);
    }

}
