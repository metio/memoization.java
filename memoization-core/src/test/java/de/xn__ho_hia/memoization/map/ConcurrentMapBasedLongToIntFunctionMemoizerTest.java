/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
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
public class ConcurrentMapBasedLongToIntFunctionMemoizerTest {

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
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongToIntFunctionMemoizer<Long> memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(
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
        final ConcurrentMap<Long, Integer> cache = null;
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL LongToIntFunction - provide an actual LongToIntFunction to fix this.");

        // then
        new ConcurrentMapBasedLongToIntFunctionMemoizer<>(cache, keyFunction, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongToIntFunctionMemoizer<Long> memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        memoizer.applyAsInt(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongToIntFunctionMemoizer<Long> memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(
                cache, keyFunction, function);

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
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = Mockito.mock(LongToIntFunction.class);
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongToIntFunctionMemoizer<Long> memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(
                cache, keyFunction, function);

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
        final ConcurrentMap<Long, Integer> cache = new ConcurrentHashMap<>();
        final LongToIntFunction function = input -> 123;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongToIntFunctionMemoizer<Long> memoizer = new ConcurrentMapBasedLongToIntFunctionMemoizer<>(
                cache, keyFunction, function);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt(123L));
    }

}
