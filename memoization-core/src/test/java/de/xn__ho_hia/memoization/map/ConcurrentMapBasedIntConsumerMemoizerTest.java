/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntConsumer;

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
public class ConcurrentMapBasedIntConsumerMemoizerTest {

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
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = System.out::println;

        // when
        final ConcurrentMapBasedIntConsumerMemoizer memoizer = new ConcurrentMapBasedIntConsumerMemoizer(
                cache, consumer);

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
        final ConcurrentMap<Integer, Integer> cache = null;
        final IntConsumer consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedIntConsumerMemoizer(cache, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentMapBasedIntConsumerMemoizer(cache, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = System.out::println;

        // when
        final ConcurrentMapBasedIntConsumerMemoizer memoizer = new ConcurrentMapBasedIntConsumerMemoizer(
                cache, consumer);

        // then
        memoizer.accept(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = System.out::println;

        // when
        final ConcurrentMapBasedIntConsumerMemoizer memoizer = new ConcurrentMapBasedIntConsumerMemoizer(
                cache, consumer);

        // then
        memoizer.accept(123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123,
                memoizer.viewCacheForTest().keySet().iterator().next().intValue());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<Integer, Integer> cache = new ConcurrentHashMap<>();
        final IntConsumer consumer = Mockito.mock(IntConsumer.class);

        // when
        final ConcurrentMapBasedIntConsumerMemoizer memoizer = new ConcurrentMapBasedIntConsumerMemoizer(
                cache, consumer);

        // then
        memoizer.accept(123);
        Mockito.verify(consumer).accept(123);
    }

}
