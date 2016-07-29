/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

import javax.cache.Cache;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheBasedDoubleBinaryOperatorMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final DoubleBinaryOperator function = (a, b) -> 123.456D;
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";
        try (final Cache<String, Double> cache = JCacheMemoize.createCache(DoubleFunction.class)) {
            // when
            final JCacheBasedDoubleBinaryOperatorMemoizer<String> loader = new JCacheBasedDoubleBinaryOperatorMemoizer<>(
                    cache, keyFunction, function);

            // then
            Assert.assertEquals("Memoized value does not match expectation", 123.456D,
                    loader.applyAsDouble(123.456D, 789.123D), 0.0D);
        }
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";
        try (final Cache<String, Double> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedDoubleBinaryOperatorMemoizer<String> loader = new JCacheBasedDoubleBinaryOperatorMemoizer<>(
                    cache, keyFunction, null);
            given(cache.invoke(any(), any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.applyAsDouble(123.456D, 789.123D);
        }
    }

}
