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
import java.util.function.LongToDoubleFunction;

class ConcurrentMapBasedLongToDoubleFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Long, Double> cache = new ConcurrentHashMap<>();
        final LongToDoubleFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Long, Double> cache = null;
        final LongToDoubleFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Long, Double> cache = new ConcurrentHashMap<>();
        final LongToDoubleFunction function = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Long, Double> cache = new ConcurrentHashMap<>();
        final LongToDoubleFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble(123);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Double> cache = new ConcurrentHashMap<>();
        final LongToDoubleFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble(123);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().keySet().iterator().next().intValue());
        Assertions.assertEquals(123D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Long, Double> cache = new ConcurrentHashMap<>();
        final LongToDoubleFunction function = Mockito.mock(LongToDoubleFunction.class);
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble(123);
        Mockito.verify(function).applyAsDouble(123);
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Long, Double> cache = new ConcurrentHashMap<>();
        final LongToDoubleFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123D, memoizer.applyAsDouble(123), 0.0D);
    }

}
