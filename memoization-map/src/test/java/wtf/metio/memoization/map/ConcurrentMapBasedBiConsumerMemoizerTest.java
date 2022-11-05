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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

class ConcurrentMapBasedBiConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndBiConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer));
    }

    @Test
    void shouldRequireNonNullBiConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer));
    }

    @Test
    void shouldMemoizeBiConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer);

        // then
        memoizer.accept("first", "second");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final var memoizer = new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer);

        // then
        memoizer.accept("first", "second");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("firstsecond", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals("firstsecond", memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedBiConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = Mockito.mock(BiConsumer.class);

        // when
        final var memoizer = new ConcurrentMapBasedBiConsumerMemoizer<>(cache, keyFunction, biConsumer);

        // then
        memoizer.accept("first", "second");
        Mockito.verify(biConsumer).accept("first", "second");
    }

}
