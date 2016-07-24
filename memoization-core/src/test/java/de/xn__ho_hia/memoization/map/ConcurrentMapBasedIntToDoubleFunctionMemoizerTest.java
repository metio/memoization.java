/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntToDoubleFunction;

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
public class ConcurrentMapBasedIntToDoubleFunctionMemoizerTest {

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
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer(
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
        final ConcurrentMap<Integer, Double> cache = null;
        final IntToDoubleFunction function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedIntToDoubleFunctionMemoizer(cache, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL IntToDoubleFunction - provide an actual IntToDoubleFunction to fix this.");

        // then
        new ConcurrentMapBasedIntToDoubleFunctionMemoizer(cache, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer(
                cache, function);

        // then
        memoizer.applyAsDouble(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer(
                cache, function);

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
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = Mockito.mock(IntToDoubleFunction.class);

        // when
        final ConcurrentMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer(
                cache, function);

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
        final ConcurrentMap<Integer, Double> cache = new ConcurrentHashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentMapBasedIntToDoubleFunctionMemoizer(
                cache, function);

        // then
        Assert.assertEquals(123D, memoizer.applyAsDouble(123), 0.0D);
    }

}
