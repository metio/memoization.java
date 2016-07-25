/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaCacheBasedPredicateMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndPredicate() {
        // given
        final Predicate<String> predicate = a -> true;
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedPredicateMemoizer<String> memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache,
                predicate);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldTestGivenValue() {
        // given
        final Predicate<String> predicate = Mockito.mock(Predicate.class);
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedPredicateMemoizer<String> memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache,
                predicate);

        // then
        memoizer.test("value");
        Mockito.verify(predicate).test("value");
    }

    /**
     * @throws ExecutionException
     *             Added for the call to 'cache.get(..)'.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final Predicate<String> predicate = a -> true;
        final Cache<String, Boolean> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);
        final GuavaCacheBasedPredicateMemoizer<String> memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache,
                predicate);

        // when
        thrown.expect(MemoizationException.class);

        // then
        memoizer.test("test");
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeResult() {
        // given
        final Predicate<String> predicate = a -> true;
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedPredicateMemoizer<String> memoizer = new GuavaCacheBasedPredicateMemoizer<>(cache,
                predicate);

        // then
        Assert.assertTrue(memoizer.test("value"));
    }

}
