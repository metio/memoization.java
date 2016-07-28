/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;

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
public class ConcurrentMapBasedToIntFunctionMemoizerTest {

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
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToIntFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(
                cache, keyFunction, function);

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
        final ConcurrentMap<String, Integer> cache = null;
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToIntFunction - provide an actual ToIntFunction to fix this.");

        // then
        new ConcurrentMapBasedToIntFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToIntFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsInt("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToIntFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsInt("123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = Mockito.mock(ToIntFunction.class);
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToIntFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsInt("123");
        Mockito.verify(function).applyAsInt("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final ToIntFunction<String> function = input -> 123;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToIntFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToIntFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt("123"));
    }

}
