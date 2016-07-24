/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjIntConsumer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.ObjIntFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedObjIntConsumerMemoizerTest {

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
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(
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
        final ConcurrentMap<String, String> cache = null;
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = null;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Provide a key function, might just be 'MemoizationDefaults.objIntConsumerHashCodeKeyFunction()'.");

        // then
        new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentMapBasedObjIntConsumerMemoizer<>(cache, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "test123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", "test123",
                memoizer.viewCacheForTest().values().iterator().next());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedConsumer() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = Mockito.mock(ObjIntConsumer.class);

        // when
        final ConcurrentMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentMapBasedObjIntConsumerMemoizer<>(
                cache, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
        Mockito.verify(consumer).accept("test", 123);
    }

}
