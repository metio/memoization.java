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
import java.util.function.IntUnaryOperator;

class ConcurrentMapBasedIntUnaryOperatorMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Integer, Integer> cache = null;
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = null;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator));
    }

    @Test
    void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123);
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123, memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = Mockito.mock(IntUnaryOperator.class);
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123);
        Mockito.verify(operator).applyAsInt(123);
    }

    @Test
    void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);

        // then
        Assertions.assertEquals(123, memoizer.applyAsInt(123));
    }

}
