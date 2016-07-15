/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

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
public class ConcurrentHashMapBasedToLongBiFunctionMemoizerTest {

    /** */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    public void shouldAcceptPreComputedValuesKeyBiFunctionAndBiFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final ConcurrentHashMapBasedToLongBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

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
        final Map<String, Long> precomputedValues = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyBiFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToLongBiFunction - provide an actual ToLongBiFunction to fix this.");

        // then
        new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final ConcurrentHashMapBasedToLongBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        memoizer.applyAsLong("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final ConcurrentHashMapBasedToLongBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        memoizer.applyAsLong("123.456", "789.123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = Mockito.mock(ToLongBiFunction.class);

        // when
        final ConcurrentHashMapBasedToLongBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        memoizer.applyAsLong("123.456", "789.123");
        Mockito.verify(biFunction).applyAsLong("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnBiFunctionResult() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToLongBiFunction<String, String> biFunction = (a, b) -> 123L;

        // when
        final ConcurrentHashMapBasedToLongBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToLongBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertEquals(123L, memoizer.applyAsLong("123.456", "789.123"));
    }

}
