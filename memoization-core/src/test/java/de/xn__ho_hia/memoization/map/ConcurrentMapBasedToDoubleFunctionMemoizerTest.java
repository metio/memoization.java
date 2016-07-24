/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.ToDoubleFunction;

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
public class ConcurrentMapBasedToDoubleFunctionMemoizerTest {

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
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(
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
        final ConcurrentMap<String, Double> cache = null;
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");

        // then
        new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(
                cache, function);

        // then
        memoizer.applyAsDouble("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(
                cache, function);

        // then
        memoizer.applyAsDouble("123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123.456D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = Mockito.mock(ToDoubleFunction.class);

        // when
        final ConcurrentMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(
                cache, function);

        // then
        memoizer.applyAsDouble("123");
        Mockito.verify(function).applyAsDouble("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(
                cache, function);

        // then
        Assert.assertEquals(123.456D, memoizer.applyAsDouble("123"), 0.0D);
    }

}
