/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongBinaryOperator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.LongBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedLongBinaryOperatorMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedLongBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Long> cache = null;
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = null;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Provide a key function, might just be 'MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL LongBinaryOperator - provide an actual LongBinaryOperator to fix this.");

        // then
        new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedLongBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123, 789);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedLongBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123, 789);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123 789",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 912,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = Mockito.mock(LongBinaryOperator.class);

        // when
        final ConcurrentMapBasedLongBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123, 789);
        Mockito.verify(operator).applyAsLong(123, 789);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final LongBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedLongBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedLongBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertEquals(912, memoizer.applyAsLong(123, 789));
    }

}
