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
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

class ConcurrentMapBasedIntToLongFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Integer, Long> cache = new ConcurrentHashMap<>();
        final IntToLongFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Integer, Long> cache = null;
        final IntToLongFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Integer, Long> cache = new ConcurrentHashMap<>();
        final IntToLongFunction function = null;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Integer, Long> cache = new ConcurrentHashMap<>();
        final IntToLongFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong(123);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Long> cache = new ConcurrentHashMap<>();
        final IntToLongFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong(123);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Integer, Long> cache = new ConcurrentHashMap<>();
        final IntToLongFunction function = Mockito.mock(IntToLongFunction.class);
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong(123);
        Mockito.verify(function).applyAsLong(123);
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Integer, Long> cache = new ConcurrentHashMap<>();
        final IntToLongFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123L, memoizer.applyAsLong(123));
    }

}
