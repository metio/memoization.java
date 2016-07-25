/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.Consumer;
import java.util.function.Predicate;

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
public class JCacheBasedConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = Mockito.mock(Consumer.class);
        try (final Cache<String, String> cache = JCacheMemoization.createCache(Predicate.class.getSimpleName())) {
            // when
            final JCacheBasedConsumerMemoizer<String> loader = new JCacheBasedConsumerMemoizer<>(cache,
                    consumer);

            // then
            loader.accept("test");
            Mockito.verify(consumer).accept("test");
        }
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerOnce() {
        // given
        final Consumer<String> consumer = Mockito.mock(Consumer.class);
        try (final Cache<String, String> cache = JCacheMemoization.createCache(Predicate.class.getSimpleName())) {
            // when
            final JCacheBasedConsumerMemoizer<String> loader = new JCacheBasedConsumerMemoizer<>(cache,
                    consumer);

            // then
            loader.accept("test");
            loader.accept("test");
            Mockito.verify(consumer).accept("test");
        }
    }

    /**
    *
    */
    @Test
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        try (final Cache<String, String> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedConsumerMemoizer<String> loader = new JCacheBasedConsumerMemoizer<>(cache, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.accept("test");
        }
    }

}
