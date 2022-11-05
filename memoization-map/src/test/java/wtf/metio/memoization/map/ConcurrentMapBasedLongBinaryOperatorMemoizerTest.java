/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.LongBinaryFunction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongBinaryOperator;

class ConcurrentMapBasedLongBinaryOperatorMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = Long::sum;

        // when
        final var memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Long> cache = null;
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = Long::sum;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = null;
        final LongBinaryOperator operator = Long::sum;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = Long::sum;

        // when
        final var memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123, 789);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = Long::sum;

        // when
        final var memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123, 789);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("123 789", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(912, memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    @Test
    void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = Mockito.mock(LongBinaryOperator.class);

        // when
        final var memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123, 789);
        Mockito.verify(operator).applyAsLong(123, 789);
    }

    @Test
    void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        final var memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertEquals(912, memoizer.applyAsLong(123, 789));
    }

}
