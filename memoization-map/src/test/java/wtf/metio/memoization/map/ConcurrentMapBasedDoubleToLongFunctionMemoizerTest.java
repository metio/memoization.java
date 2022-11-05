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
import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;

class ConcurrentMapBasedDoubleToLongFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Double, Long> cache = new ConcurrentHashMap<>();
        final DoubleToLongFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Long> cache = null;
        final DoubleToLongFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Double, Long> cache = new ConcurrentHashMap<>();
        final DoubleToLongFunction function = null;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Double, Long> cache = new ConcurrentHashMap<>();
        final DoubleToLongFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong(123.456D);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Long> cache = new ConcurrentHashMap<>();
        final DoubleToLongFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong(123D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123D, memoizer.viewCacheForTest().keySet().iterator().next(), 0.0D);
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Double, Long> cache = new ConcurrentHashMap<>();
        final DoubleToLongFunction function = Mockito.mock(DoubleToLongFunction.class);
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsLong(123D);
        Mockito.verify(function).applyAsLong(123D);
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Double, Long> cache = new ConcurrentHashMap<>();
        final DoubleToLongFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123L, memoizer.applyAsLong(123D));
    }

}
