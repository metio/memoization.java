/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.ObjDoubleFunction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjDoubleConsumer;

class ConcurrentMapBasedObjDoubleConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjDoubleFunction<String, String> keyFunction = null;
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjDoubleConsumer<String> consumer = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123.456D);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123.456D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("test123.456", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals("test", memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjDoubleConsumer<String> consumer = Mockito.mock(ObjDoubleConsumer.class);

        // when
        final var memoizer = new ConcurrentMapBasedObjDoubleConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123.456D);
        Mockito.verify(consumer).accept("test", 123.456D);
    }

}
