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
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

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
public class GuavaCacheBasedDoubleUnaryOperatorMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptLoadingCache() {
        // given
        final DoubleUnaryOperator function = a -> 123.456D;
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedDoubleUnaryOperatorMemoizer<Double> memoizer = new GuavaCacheBasedDoubleUnaryOperatorMemoizer<>(
                cache, keyFunction, function);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldTransformInput() {
        // given
        final DoubleUnaryOperator function = a -> 123.456D;
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedDoubleUnaryOperatorMemoizer<Double> memoizer = new GuavaCacheBasedDoubleUnaryOperatorMemoizer<>(
                cache, keyFunction, function);

        // then
        Assert.assertEquals(123.456D, memoizer.applyAsDouble(789D), 0.0D);
    }

    /**
     * @throws ExecutionException
     *             Added for the call to 'cache.get(..)'.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final DoubleFunction<Double> keyFunction = Double::valueOf;
        final Cache<Double, Double> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);
        final GuavaCacheBasedDoubleUnaryOperatorMemoizer<Double> memoizer = new GuavaCacheBasedDoubleUnaryOperatorMemoizer<>(
                cache, keyFunction, null);

        // when
        thrown.expect(MemoizationException.class);

        // then
        memoizer.applyAsDouble(789D);
    }

}
