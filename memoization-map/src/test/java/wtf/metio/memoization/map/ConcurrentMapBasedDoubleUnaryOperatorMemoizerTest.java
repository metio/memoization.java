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
import java.util.function.DoubleUnaryOperator;

class ConcurrentMapBasedDoubleUnaryOperatorMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Double> cache = null;
        final DoubleUnaryOperator operator = input -> input;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = null;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123.456D);
    }

    /**
     *
     */
    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123D, memoizer.viewCacheForTest().keySet().iterator().next(), 0.0D);
        Assertions.assertEquals(123D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = Mockito.mock(DoubleUnaryOperator.class);
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D);
        Mockito.verify(operator).applyAsDouble(123D);
    }

    @Test
    void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertEquals(123D, memoizer.applyAsDouble(123D), 0.0D);
    }

}
