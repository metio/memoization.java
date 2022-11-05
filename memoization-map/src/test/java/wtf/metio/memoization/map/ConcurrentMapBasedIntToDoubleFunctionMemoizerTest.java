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
import java.util.function.IntToDoubleFunction;

class ConcurrentMapBasedIntToDoubleFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Integer, Double> cache = null;
        final IntToDoubleFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = null;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble(123);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble(123);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = Mockito.mock(IntToDoubleFunction.class);
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble(123);
        Mockito.verify(function).applyAsDouble(123);
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123D, memoizer.applyAsDouble(123), 0.0D);
    }

}
