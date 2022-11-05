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
import java.util.function.ToIntBiFunction;

class ConcurrentMapBasedToIntBiFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyBiFunctionAndBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Integer> cache = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullKeyBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldMemoizeBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = Mockito.mock(ToIntBiFunction.class);

        // when
        final var memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
        Mockito.verify(biFunction).applyAsInt("123.456", "789.123");
    }

    @Test
    void shouldUseReturnBiFunctionResult() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt("123.456", "789.123"));
    }

}
