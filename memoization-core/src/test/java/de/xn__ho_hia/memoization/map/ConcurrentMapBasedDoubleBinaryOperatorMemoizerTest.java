/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleBinaryOperator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedDoubleBinaryOperatorMemoizerTest {

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
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(
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
        final ConcurrentMap<String, Double> cache = null;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = null;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Provide a key function, might just be 'MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL DoubleBinaryOperator - provide an actual DoubleBinaryOperator to fix this.");

        // then
        new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123.456D, 789.123D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D, 789D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123.0 789.0",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 912D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Mockito.mock(DoubleBinaryOperator.class);

        // when
        final ConcurrentMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D, 789D);
        Mockito.verify(operator).applyAsDouble(123D, 789D);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedDoubleBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertEquals(912D, memoizer.applyAsDouble(123D, 789D), 0.0D);
    }

}
