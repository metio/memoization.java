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
import java.util.function.ToLongFunction;

class ConcurrentMapBasedToLongFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Long> cache = null;
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong("123");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong("123");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("123", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = Mockito.mock(ToLongFunction.class);
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong("123");
        Mockito.verify(function).applyAsLong("123");
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123L, memoizer.applyAsLong("123"));
    }

}
