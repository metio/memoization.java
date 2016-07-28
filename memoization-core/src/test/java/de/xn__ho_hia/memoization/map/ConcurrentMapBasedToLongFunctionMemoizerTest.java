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
import java.util.function.ToLongFunction;

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
public class ConcurrentMapBasedToLongFunctionMemoizerTest {

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
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToLongFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(
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
        final ConcurrentMap<String, Long> cache = null;
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToLongFunction - provide an actual ToLongFunction to fix this.");

        // then
        new ConcurrentMapBasedToLongFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToLongFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsLong("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToLongFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsLong("123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = Mockito.mock(ToLongFunction.class);
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToLongFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsLong("123");
        Mockito.verify(function).applyAsLong("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final ToLongFunction<String> function = input -> 123L;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final ConcurrentMapBasedToLongFunctionMemoizer<String, String> memoizer = new ConcurrentMapBasedToLongFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        Assert.assertEquals(123L, memoizer.applyAsLong("123"));
    }

}
