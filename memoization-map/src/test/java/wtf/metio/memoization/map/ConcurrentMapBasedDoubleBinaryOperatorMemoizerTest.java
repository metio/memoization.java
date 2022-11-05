/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.DoubleBinaryFunction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleBinaryOperator;

class ConcurrentMapBasedDoubleBinaryOperatorMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Double::sum;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Double> cache = null;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Double::sum;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = null;
        final DoubleBinaryOperator operator = Double::sum;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Double::sum;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123.456D, 789.123D);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Double::sum;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D, 789D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("123.0 789.0", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(912D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Mockito.mock(DoubleBinaryOperator.class);

        // when
        final var memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D, 789D);
        Mockito.verify(operator).applyAsDouble(123D, 789D);
    }

    @Test
    void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Double::sum;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertEquals(912D, memoizer.applyAsDouble(123D, 789D), 0.0D);
    }

}
