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
import java.util.function.DoubleToIntFunction;

class ConcurrentMapBasedDoubleToIntFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Integer> cache = null;
        final DoubleToIntFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = null;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123.456D);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123D, memoizer.viewCacheForTest().keySet().iterator().next(), 0.0D);
        Assertions.assertEquals(123, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = Mockito.mock(DoubleToIntFunction.class);
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123D);
        Mockito.verify(function).applyAsInt(123D);
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt(123D));
    }

}
