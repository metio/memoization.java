/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;

class ConcurrentMapBasedConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        final var memoizer = new ConcurrentMapBasedConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> keyFunction = null;
        final Consumer<String> consumer = System.out::println;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedConsumerMemoizer<>(cache, keyFunction, consumer));
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        final var memoizer = new ConcurrentMapBasedConsumerMemoizer<>(cache, keyFunction, consumer);

        // then
        memoizer.accept("test");
    }

}
