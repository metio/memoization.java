/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.BiFunction;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaCacheBasedBiFunctionMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndBiFunction() {
        // given
        final BiFunction<String, String, String> biFunction = (first, second) -> first + second;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedBiFunctionMemoizer<String, String, String, String> memoizer = new GuavaCacheBasedBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldTransformInput() {
        // given
        final BiFunction<String, String, String> biFunction = (first, second) -> first + second;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedBiFunctionMemoizer<String, String, String, String> memoizer = new GuavaCacheBasedBiFunctionMemoizer<>(
                cache, keyFunction, biFunction);

        // then
        Assert.assertEquals("firstsecond", memoizer.apply("first", "second"));
    }

}
