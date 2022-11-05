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
import java.util.function.LongUnaryOperator;

class ConcurrentMapBasedLongUnaryOperatorMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Long, Long> cache = null;
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123L);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123L);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123L, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = Mockito.mock(LongUnaryOperator.class);
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123L);
        Mockito.verify(operator).applyAsLong(123L);
    }

    @Test
    void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertEquals(123L, memoizer.applyAsLong(123L));
    }

}
