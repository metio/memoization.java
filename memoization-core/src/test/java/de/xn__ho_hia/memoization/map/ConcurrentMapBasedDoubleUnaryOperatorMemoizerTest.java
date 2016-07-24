/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleUnaryOperator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedDoubleUnaryOperatorMemoizerTest {

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
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;

        // when
        final ConcurrentMapBasedDoubleUnaryOperatorMemoizer memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(
                cache, operator);

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
        final ConcurrentMap<Double, Double> cache = null;
        final DoubleUnaryOperator operator = input -> input;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(cache, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL DoubleUnaryOperator - provide an actual DoubleUnaryOperator to fix this.");

        // then
        new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(cache, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;

        // when
        final ConcurrentMapBasedDoubleUnaryOperatorMemoizer memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(
                cache, operator);

        // then
        memoizer.applyAsDouble(123.456D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;

        // when
        final ConcurrentMapBasedDoubleUnaryOperatorMemoizer memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(
                cache, operator);

        // then
        memoizer.applyAsDouble(123D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123D,
                memoizer.viewCacheForTest().keySet().iterator().next().doubleValue(), 0.0D);
        Assert.assertEquals("Memoization value does not match expectations", 123D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = Mockito.mock(DoubleUnaryOperator.class);

        // when
        final ConcurrentMapBasedDoubleUnaryOperatorMemoizer memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(
                cache, operator);

        // then
        memoizer.applyAsDouble(123D);
        Mockito.verify(operator).applyAsDouble(123D);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleUnaryOperator operator = input -> input;

        // when
        final ConcurrentMapBasedDoubleUnaryOperatorMemoizer memoizer = new ConcurrentMapBasedDoubleUnaryOperatorMemoizer(
                cache, operator);

        // then
        Assert.assertEquals(123D, memoizer.applyAsDouble(123D), 0.0D);
    }

}
