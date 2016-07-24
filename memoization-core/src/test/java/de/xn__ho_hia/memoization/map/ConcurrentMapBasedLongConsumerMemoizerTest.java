/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongConsumer;

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
public class ConcurrentMapBasedLongConsumerMemoizerTest {

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
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = System.out::println;

        // when
        final ConcurrentMapBasedLongConsumerMemoizer memoizer = new ConcurrentMapBasedLongConsumerMemoizer(
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
        final ConcurrentMap<Long, Long> cache = null;
        final LongConsumer consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedLongConsumerMemoizer(cache, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentMapBasedLongConsumerMemoizer(cache, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = System.out::println;

        // when
        final ConcurrentMapBasedLongConsumerMemoizer memoizer = new ConcurrentMapBasedLongConsumerMemoizer(
                cache, consumer);

        // then
        memoizer.accept(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = System.out::println;

        // when
        final ConcurrentMapBasedLongConsumerMemoizer memoizer = new ConcurrentMapBasedLongConsumerMemoizer(
                cache, consumer);

        // then
        memoizer.accept(123L);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123L,
                memoizer.viewCacheForTest().keySet().iterator().next().longValue());
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<Long, Long> cache = new ConcurrentHashMap<>();
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);

        // when
        final ConcurrentMapBasedLongConsumerMemoizer memoizer = new ConcurrentMapBasedLongConsumerMemoizer(
                cache, consumer);

        // then
        memoizer.accept(123L);
        Mockito.verify(consumer).accept(123L);
    }

}
