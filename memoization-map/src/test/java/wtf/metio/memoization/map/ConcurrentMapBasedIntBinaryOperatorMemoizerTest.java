/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.IntBinaryFunction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntBinaryOperator;

class ConcurrentMapBasedIntBinaryOperatorMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Integer::sum;

        // when
        final var memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Integer> cache = null;
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Integer::sum;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = null;
        final IntBinaryOperator operator = Integer::sum;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Integer::sum;

        // when
        final var memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123, 789);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Integer::sum;

        // when
        final var memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123, 789);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("123 789", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(912, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Mockito.mock(IntBinaryOperator.class);

        // when
        final var memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123, 789);
        Mockito.verify(operator).applyAsInt(123, 789);
    }

    @Test
    void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Integer::sum;

        // when
        final var memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertEquals(912, memoizer.applyAsInt(123, 789));
    }

}
