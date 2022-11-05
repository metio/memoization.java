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
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

class ConcurrentMapBasedLongToIntFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Long, Integer> cache = null;
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123L);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123L);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = Mockito.mock(LongToIntFunction.class);
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123L);
        Mockito.verify(function).applyAsInt(123L);
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt(123L));
    }

}
