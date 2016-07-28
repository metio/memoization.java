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
import java.util.function.LongUnaryOperator;

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
public class ConcurrentMapBasedLongUnaryOperatorMemoizerTest {

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
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongUnaryOperatorMemoizer<Long> memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

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
        final ConcurrentMap<Long, Long> cache = null;
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL LongUnaryOperator - provide an actual LongUnaryOperator to fix this.");

        // then
        new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongUnaryOperatorMemoizer<Long> memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongUnaryOperatorMemoizer<Long> memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123L);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123L,
                memoizer.viewCacheForTest().keySet().iterator().next().longValue());
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = Mockito.mock(LongUnaryOperator.class);
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongUnaryOperatorMemoizer<Long> memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsLong(123L);
        Mockito.verify(operator).applyAsLong(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongUnaryOperator operator = input -> input;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final ConcurrentMapBasedLongUnaryOperatorMemoizer<Long> memoizer = new ConcurrentMapBasedLongUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertEquals(123L, memoizer.applyAsLong(123L));
    }

}
