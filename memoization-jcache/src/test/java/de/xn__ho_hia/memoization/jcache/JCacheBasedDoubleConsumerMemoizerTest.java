/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

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
public class JCacheBasedDoubleConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);
        final DoubleFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Double> cache = JCacheMemoize.createCache(DoubleConsumer.class)) {
            // when
            final JCacheBasedDoubleConsumerMemoizer<String> loader = new JCacheBasedDoubleConsumerMemoizer<>(cache,
                    keyFunction, consumer);

            // then
            loader.accept(123.456D);
            Mockito.verify(consumer).accept(123.456D);
        }
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerOnce() {
        // given
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);
        final DoubleFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Double> cache = JCacheMemoize.createCache(DoubleConsumer.class)) {
            // when
            final JCacheBasedDoubleConsumerMemoizer<String> loader = new JCacheBasedDoubleConsumerMemoizer<>(cache,
                    keyFunction, consumer);

            // then
            loader.accept(123.456D);
            loader.accept(123.456D);
            Mockito.verify(consumer).accept(123.456D);
        }
    }

    /**
    *
    */
    @Test
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        final DoubleFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Double> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedDoubleConsumerMemoizer<String> loader = new JCacheBasedDoubleConsumerMemoizer<>(cache,
                    keyFunction, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.accept(123.456D);
        }
    }

}
