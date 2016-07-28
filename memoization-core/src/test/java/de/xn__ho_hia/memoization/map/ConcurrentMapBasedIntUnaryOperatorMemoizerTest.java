/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

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
public class ConcurrentMapBasedIntUnaryOperatorMemoizerTest {

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
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final ConcurrentMapBasedIntUnaryOperatorMemoizer<Integer> memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(
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
        final ConcurrentMap<Integer, Integer> cache = null;
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = null;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL IntUnaryOperator - provide an actual IntUnaryOperator to fix this.");

        // then
        new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final ConcurrentMapBasedIntUnaryOperatorMemoizer<Integer> memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final ConcurrentMapBasedIntUnaryOperatorMemoizer<Integer> memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123,
                memoizer.viewCacheForTest().keySet().iterator().next().intValue());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = Mockito.mock(IntUnaryOperator.class);
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final ConcurrentMapBasedIntUnaryOperatorMemoizer<Integer> memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123);
        Mockito.verify(operator).applyAsInt(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntUnaryOperator operator = input -> input;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final ConcurrentMapBasedIntUnaryOperatorMemoizer<Integer> memoizer = new ConcurrentMapBasedIntUnaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt(123));
    }

}
