/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleToIntFunction;

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
public class ConcurrentMapBasedDoubleToIntFunctionMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;

        // when
        final ConcurrentMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer(
                cache, function);

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
        final ConcurrentMap<Double, Integer> cache = null;
        final DoubleToIntFunction function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedDoubleToIntFunctionMemoizer(cache, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL DoubleToIntFunction - provide an actual DoubleToIntFunction to fix this.");

        // then
        new ConcurrentMapBasedDoubleToIntFunctionMemoizer(cache, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;

        // when
        final ConcurrentMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer(
                cache, function);

        // then
        memoizer.applyAsInt(123.456D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;

        // when
        final ConcurrentMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer(
                cache, function);

        // then
        memoizer.applyAsInt(123D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123D,
                memoizer.viewCacheForTest().keySet().iterator().next().doubleValue(), 0.0D);
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = Mockito.mock(DoubleToIntFunction.class);

        // when
        final ConcurrentMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer(
                cache, function);

        // then
        memoizer.applyAsInt(123D);
        Mockito.verify(function).applyAsInt(123D);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<Double, Integer> cache = new ConcurrentHashMap<>();
        final DoubleToIntFunction function = input -> 123;

        // when
        final ConcurrentMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentMapBasedDoubleToIntFunctionMemoizer(
                cache, function);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt(123D));
    }

}
