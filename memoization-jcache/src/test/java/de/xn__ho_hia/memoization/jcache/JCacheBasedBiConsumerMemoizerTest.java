/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import javax.cache.Cache;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheBasedBiConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);
        final BiFunction<String, String, String> keyfunction = hashCodeKeyFunction();
        try (final Cache<String, String> cache = JCacheMemoize.createCache(BiConsumer.class)) {
            // when
            final JCacheBasedBiConsumerMemoizer<String, String, String> memoizer = new JCacheBasedBiConsumerMemoizer<>(
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
        final BiConsumer<String, String> biConsumer = Mockito.mock(BiConsumer.class);
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        try (final Cache<String, String> cache = JCacheMemoize.createCache(BiConsumer.class)) {
            // when
            final JCacheBasedBiConsumerMemoizer<String, String, String> memoizer = new JCacheBasedBiConsumerMemoizer<>(
                    cache, keyFunction, biConsumer);

            // then
            memoizer.accept("first", "second");
            Mockito.verify(biConsumer).accept("first", "second");
        }
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        final BiFunction<String, String, String> keyfunction = hashCodeKeyFunction();
        try (final Cache<String, String> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedBiConsumerMemoizer<String, String, String> loader = new JCacheBasedBiConsumerMemoizer<>(
                    cache, keyfunction, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.accept("first", "second");
        }
    }

}
