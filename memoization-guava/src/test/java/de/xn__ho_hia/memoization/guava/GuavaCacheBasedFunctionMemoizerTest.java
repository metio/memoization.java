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
import java.util.function.Function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

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
public class GuavaCacheBasedFunctionMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptLoadingCache() {
        // given
        final Function<String, String> function = Function.identity();
        final LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .build(new FunctionBasedCacheLoader<>(function));

        // when
        final GuavaCacheBasedFunctionMemoizer<String, String> memoizer = new GuavaCacheBasedFunctionMemoizer<>(cache);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldTransformInput() {
        // given
        final Function<String, String> function = Function.identity();
        final LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .build(new FunctionBasedCacheLoader<>(function));

        // when
        final GuavaCacheBasedFunctionMemoizer<String, String> memoizer = new GuavaCacheBasedFunctionMemoizer<>(cache);

        // then
        Assert.assertEquals("value", memoizer.apply("value"));
    }

    /**
     * @throws ExecutionException
     *             Added for the call to 'cache.get(..)'.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final LoadingCache<String, String> cache = Mockito.mock(LoadingCache.class);
        given(cache.get(any())).willThrow(ExecutionException.class);
        final GuavaCacheBasedFunctionMemoizer<String, String> memoizer = new GuavaCacheBasedFunctionMemoizer<>(cache);

        // when
        thrown.expect(MemoizationException.class);

        // then
        memoizer.apply("test");
    }

}
