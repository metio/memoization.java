/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedDoubleConsumerMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final ConcurrentMapBasedDoubleConsumerMemoizer<Double> memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Double> cache = null;
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = null;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentMapBasedDoubleConsumerMemoizer<>(cache, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final ConcurrentMapBasedDoubleConsumerMemoizer<Double> memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final ConcurrentMapBasedDoubleConsumerMemoizer<Double> memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123.456D,
                memoizer.viewCacheForTest().keySet().iterator().next().doubleValue(), 0.0D);
        Assert.assertEquals("Memoization value does not match expectations", 123.456D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<>();
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final ConcurrentMapBasedDoubleConsumerMemoizer<Double> memoizer = new ConcurrentMapBasedDoubleConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        memoizer.accept(123.456D);
        Mockito.verify(consumer).accept(123.456D);
    }

}
