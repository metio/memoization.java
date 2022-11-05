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
import java.util.function.ToDoubleBiFunction;

class ConcurrentMapBasedToDoubleBiFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyBiFunctionAndBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Double> cache = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullKeyBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldRequireNonNullBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction));
    }

    @Test
    void shouldMemoizeBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsDouble("123.456", "789.123");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsDouble("123.456", "789.123");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123.456D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = Mockito.mock(ToDoubleBiFunction.class);

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        memoizer.applyAsDouble("123.456", "789.123");
        Mockito.verify(biFunction).applyAsDouble("123.456", "789.123");
    }

    @Test
    void shouldUseReturnBiFunctionResult() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertEquals(123.456D, memoizer.applyAsDouble("123.456", "789.123"), 0.0D);
    }

}
