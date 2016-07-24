/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

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
public class ConcurrentMapBasedToIntBiFunctionMemoizerTest {

    /** */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    public void shouldAcceptCacheAndKeyBiFunctionAndBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

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
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToIntBiFunction - provide an actual ToIntBiFunction to fix this.");

        // then
        new ConcurrentMapBasedToIntBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiFunction() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = Mockito.mock(ToIntBiFunction.class);

        // when
        final ConcurrentMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
        Mockito.verify(biFunction).applyAsInt("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnBiFunctionResult() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToIntBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt("123.456", "789.123"));
    }

}
