/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;

import javax.cache.Cache;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD, CompilerWarnings.UNCHECKED })
public class JCacheBasedLongConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);
        final LongFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Long> cache = JCacheMemoize.createCache(LongConsumer.class)) {
            // when
            final JCacheBasedLongConsumerMemoizer<String> loader = new JCacheBasedLongConsumerMemoizer<>(cache,
                    keyFunction, consumer);

            // then
            loader.accept(123);
            Mockito.verify(consumer).accept(123);
        }
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerOnce() {
        // given
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);
        final LongFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Long> cache = JCacheMemoize.createCache(LongConsumer.class)) {
            // when
            final JCacheBasedLongConsumerMemoizer<String> loader = new JCacheBasedLongConsumerMemoizer<>(cache,
                    keyFunction, consumer);

            // then
            loader.accept(123);
            loader.accept(123);
            Mockito.verify(consumer).accept(123);
        }
    }

    /**
    *
    */
    @Test
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        final LongFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Long> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedLongConsumerMemoizer<String> loader = new JCacheBasedLongConsumerMemoizer<>(cache,
                    keyFunction, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.accept(123);
        }
    }

}
