/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongToDoubleFunction;

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
public class ConcurrentHashMapBasedLongToDoubleFunctionMemoizerTest {

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
        final Map<Long, Double> precomputedValues = new HashMap<>();
        final LongToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(
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
        final Map<Long, Double> precomputedValues = null;
        final LongToDoubleFunction function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<Long, Double> precomputedValues = new HashMap<>();
        final LongToDoubleFunction function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL LongToDoubleFunction - provide an actual LongToDoubleFunction to fix this.");

        // then
        new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<Long, Double> precomputedValues = new HashMap<>();
        final LongToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Long, Double> precomputedValues = new HashMap<>();
        final LongToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble(123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123,
                memoizer.viewCacheForTest().keySet().iterator().next().intValue());
        Assert.assertEquals("Memoization value does not match expectations", 123D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<Long, Double> precomputedValues = new HashMap<>();
        final LongToDoubleFunction function = Mockito.mock(LongToDoubleFunction.class);

        // when
        final ConcurrentHashMapBasedLongToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble(123);
        Mockito.verify(function).applyAsDouble(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<Long, Double> precomputedValues = new HashMap<>();
        final LongToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        Assert.assertEquals(123D, memoizer.applyAsDouble(123), 0.0D);
    }

}
