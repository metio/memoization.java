/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntBinaryOperator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.IntBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedIntBinaryOperatorMemoizerTest {

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
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedIntBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(
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
        final ConcurrentMap<String, Integer> cache = null;
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = null;
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Provide a key function, might just be 'MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL IntBinaryOperator - provide an actual IntBinaryOperator to fix this.");

        // then
        new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(cache, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedIntBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123, 789);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedIntBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123, 789);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123 789",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 912,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = Mockito.mock(IntBinaryOperator.class);

        // when
        final ConcurrentMapBasedIntBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        memoizer.applyAsInt(123, 789);
        Mockito.verify(operator).applyAsInt(123, 789);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final IntBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentMapBasedIntBinaryOperatorMemoizer<String> memoizer = new ConcurrentMapBasedIntBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertEquals(912, memoizer.applyAsInt(123, 789));
    }

}
