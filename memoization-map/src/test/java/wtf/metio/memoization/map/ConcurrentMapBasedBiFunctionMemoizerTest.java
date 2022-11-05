/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

class ConcurrentMapBasedBiFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyBiFunctionAndBiFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullKeyBiFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullBiFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldReturnGivenBiFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer.getBiFunction());
        Assertions.assertSame(biFunction, memoizer.getBiFunction());
    }

    @Test
    void shouldReturnGivenKeyFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer.getKeyFunction());
        Assertions.assertSame(keyFunction, memoizer.getKeyFunction());
    }

    @Test
    void shouldReturnNonNullMemoizationFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer.getMemoizingFunction());
    }

}
