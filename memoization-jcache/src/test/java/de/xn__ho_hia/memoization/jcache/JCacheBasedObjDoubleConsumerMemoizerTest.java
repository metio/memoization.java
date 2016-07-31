/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;

import javax.cache.Cache;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheBasedObjDoubleConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final ObjDoubleConsumer<String> biConsumer = (first, second) -> System.out.println(first + second);
        final ObjDoubleFunction<String, String> keyfunction = (a, b) -> "key";
        try (final Cache<String, String> cache = JCacheMemoize.createCache(BiConsumer.class)) {
            // when
            final JCacheBasedObjDoubleConsumerMemoizer<String, String> memoizer = new JCacheBasedObjDoubleConsumerMemoizer<>(
                    cache, keyfunction, biConsumer);

            // then
            Assert.assertNotNull("Memoizer is NULL", memoizer);
        }
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldAcceptInput() {
        // given
        final ObjDoubleConsumer<String> biConsumer = Mockito.mock(ObjDoubleConsumer.class);
        final ObjDoubleFunction<String, String> keyfunction = (a, b) -> "key";
        try (final Cache<String, String> cache = JCacheMemoize.createCache(BiConsumer.class)) {
            // when
            final JCacheBasedObjDoubleConsumerMemoizer<String, String> memoizer = new JCacheBasedObjDoubleConsumerMemoizer<>(
                    cache, keyfunction, biConsumer);

            // then
            memoizer.accept("first", 123);
            Mockito.verify(biConsumer).accept("first", 123);
        }
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        final ObjDoubleFunction<String, String> keyfunction = (a, b) -> "key";
        try (final Cache<String, String> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedObjDoubleConsumerMemoizer<String, String> loader = new JCacheBasedObjDoubleConsumerMemoizer<>(
                    cache, keyfunction, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.accept("first", 123);
        }
    }

}
