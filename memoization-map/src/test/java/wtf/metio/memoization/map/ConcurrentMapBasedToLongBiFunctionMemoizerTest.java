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
import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

class ConcurrentMapBasedToLongBiFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyBiFunctionAndBiFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Long> cache = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullKeyBiFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullBiFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldMemoizeBiFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsLong("123.456", "789.123");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsLong("123.456", "789.123");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedBiFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = Mockito.mock(ToLongBiFunction.class);

        // when
        final var memoizer = new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsLong("123.456", "789.123");
        Mockito.verify(biFunction).applyAsLong("123.456", "789.123");
    }

    @Test
    void shouldUseReturnBiFunctionResult() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertEquals(123L, memoizer.applyAsLong("123.456", "789.123"));
    }

}
