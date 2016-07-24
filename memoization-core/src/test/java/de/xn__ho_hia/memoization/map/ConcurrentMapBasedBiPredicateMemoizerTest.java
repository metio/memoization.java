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
import java.util.function.BiPredicate;

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
public class ConcurrentMapBasedBiPredicateMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(
                cache, keyFunction, biPredicate);

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
        final ConcurrentMap<String, Boolean> cache = null;
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL BiPredicate - provide an actual BiPredicate to fix this.");

        // then
        new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(
                cache, keyFunction, biPredicate);

        // then
        Assert.assertTrue(memoizer.test("first", "second"));
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(
                cache, keyFunction, biPredicate);

        // then
        memoizer.test("first", "second");
        Assert.assertEquals("Memoization key does not match expectations", "firstsecond",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertTrue("Memoization value does not match expectations",
                memoizer.viewCacheForTest().values().iterator().next().booleanValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = Mockito.mock(BiPredicate.class);

        // when
        final ConcurrentMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(
                cache, keyFunction, biPredicate);

        // then
        memoizer.test("first", "second");
        Mockito.verify(biPredicate).test("first", "second");
    }

}
