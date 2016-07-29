/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.LongFunction;
import java.util.function.LongPredicate;

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
public class JCacheBasedLongPredicateMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizePredicate() {
        // given
        final LongPredicate predicate = a -> true;
        final LongFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Boolean> cache = JCacheMemoize.createCache(LongPredicate.class)) {
            // when
            final JCacheBasedLongPredicateMemoizer<String> loader = new JCacheBasedLongPredicateMemoizer<>(cache,
                    keyFunction, predicate);

            // then
            Assert.assertTrue("Memoized value does not match expectation", loader.test(123));
        }
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        final LongFunction<String> keyFunction = a -> "key";
        try (final Cache<String, Boolean> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedLongPredicateMemoizer<String> loader = new JCacheBasedLongPredicateMemoizer<>(cache,
                    keyFunction, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.test(123);
        }
    }

}
