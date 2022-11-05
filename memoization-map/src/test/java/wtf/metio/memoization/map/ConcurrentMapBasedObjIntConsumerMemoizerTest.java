/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.ObjIntFunction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjIntConsumer;

class ConcurrentMapBasedObjIntConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = null;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("test123", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals("test", memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = Mockito.mock(ObjIntConsumer.class);

        // when
        final var memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
        Mockito.verify(consumer).accept("test", 123);
    }

}
