/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongToIntFunction;

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
public class ConcurrentHashMapBasedLongToIntFunctionMemoizerTest {

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
        final Map<Long, Integer> precomputedValues = new HashMap<>();
        final LongToIntFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToIntFunctionMemoizer(
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
        final Map<Long, Integer> precomputedValues = null;
        final LongToIntFunction function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedLongToIntFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<Long, Integer> precomputedValues = new HashMap<>();
        final LongToIntFunction function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL LongToIntFunction - provide an actual LongToIntFunction to fix this.");

        // then
        new ConcurrentHashMapBasedLongToIntFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<Long, Integer> precomputedValues = new HashMap<>();
        final LongToIntFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToIntFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsInt(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Long, Integer> precomputedValues = new HashMap<>();
        final LongToIntFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToIntFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsInt(123L);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123L,
                memoizer.viewCacheForTest().keySet().iterator().next().longValue());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<Long, Integer> precomputedValues = new HashMap<>();
        final LongToIntFunction function = Mockito.mock(LongToIntFunction.class);

        // when
        final ConcurrentHashMapBasedLongToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToIntFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsInt(123L);
        Mockito.verify(function).applyAsInt(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<Long, Integer> precomputedValues = new HashMap<>();
        final LongToIntFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedLongToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedLongToIntFunctionMemoizer(
                precomputedValues, function);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt(123L));
    }

}
