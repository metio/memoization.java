/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleToLongFunction;

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
public class ConcurrentHashMapBasedDoubleToLongFunctionMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptPreComputedValuesAndFunction() {
        // given
        final Map<Double, Long> precomputedValues = new HashMap<>();
        final DoubleToLongFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToLongFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(
                precomputedValues, function);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullPreComputedValues() {
        // given
        final Map<Double, Long> precomputedValues = null;
        final DoubleToLongFunction function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<Double, Long> precomputedValues = new HashMap<>();
        final DoubleToLongFunction function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL DoubleToLongFunction - provide an actual DoubleToLongFunction to fix this.");

        // then
        new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<Double, Long> precomputedValues = new HashMap<>();
        final DoubleToLongFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToLongFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsLong(123.456D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Double, Long> precomputedValues = new HashMap<>();
        final DoubleToLongFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToLongFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsLong(123D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123D,
                memoizer.viewCacheForTest().keySet().iterator().next().doubleValue(), 0.0D);
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<Double, Long> precomputedValues = new HashMap<>();
        final DoubleToLongFunction function = Mockito.mock(DoubleToLongFunction.class);

        // when
        final ConcurrentHashMapBasedDoubleToLongFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsLong(123D);
        Mockito.verify(function).applyAsLong(123D);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<Double, Long> precomputedValues = new HashMap<>();
        final DoubleToLongFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToLongFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToLongFunctionMemoizer(
                precomputedValues, function);

        // then
        Assert.assertEquals(123L, memoizer.applyAsLong(123D));
    }

}
