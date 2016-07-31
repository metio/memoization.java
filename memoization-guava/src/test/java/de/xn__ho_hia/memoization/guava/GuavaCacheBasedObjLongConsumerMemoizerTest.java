/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.ObjLongConsumer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.ObjLongFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaCacheBasedObjLongConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndBiConsumer() {
        // given
        final ObjLongConsumer<String> biConsumer = (first, second) -> System.out.println(first + second);
        final ObjLongFunction<String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedObjLongConsumerMemoizer<String, String> memoizer = new GuavaCacheBasedObjLongConsumerMemoizer<>(
                cache, keyFunction, biConsumer);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldAcceptInput() {
        // given
        final ObjLongConsumer<String> biConsumer = Mockito.mock(ObjLongConsumer.class);
        final ObjLongFunction<String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedObjLongConsumerMemoizer<String, String> memoizer = new GuavaCacheBasedObjLongConsumerMemoizer<>(
                cache, keyFunction, biConsumer);

        // then
        memoizer.accept("first", 123);
        Mockito.verify(biConsumer).accept("first", 123);
    }

}
