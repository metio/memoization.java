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
import java.util.function.Function;
import java.util.function.ToIntFunction;

class ConcurrentMapBasedToIntFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Integer> cache = null;
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt("123");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt("123");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("123", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = Mockito.mock(ToIntFunction.class);
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt("123");
        Mockito.verify(function).applyAsInt("123");
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt("123"));
    }

}
