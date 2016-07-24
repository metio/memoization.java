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
import java.util.function.ToDoubleBiFunction;

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
public class ConcurrentMapBasedToDoubleBiFunctionMemoizerTest {

    /** */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    public void shouldAcceptCacheAndKeyBiFunctionAndBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(
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
        final ConcurrentMap<String, Double> cache = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToDoubleBiFunction - provide an actual ToDoubleBiFunction to fix this.");

        // then
        new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(cache, keyFunction, biFunction);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        memoizer.applyAsDouble("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        memoizer.applyAsDouble("123.456", "789.123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123.456D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = Mockito.mock(ToDoubleBiFunction.class);

        // when
        final ConcurrentMapBasedToDoubleBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        memoizer.applyAsDouble("123.456", "789.123");
        Mockito.verify(biFunction).applyAsDouble("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnBiFunctionResult() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToDoubleBiFunction<String, String> biFunction = (a, b) -> 123.456D;

        // when
        final ConcurrentMapBasedToDoubleBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentMapBasedToDoubleBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        Assert.assertEquals(123.456D, memoizer.applyAsDouble("123.456", "789.123"), 0.0D);
    }

}
