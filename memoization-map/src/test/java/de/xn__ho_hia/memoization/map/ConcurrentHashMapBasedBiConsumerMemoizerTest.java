/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

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
public class ConcurrentHashMapBasedBiConsumerMemoizerTest {

    /**
     *
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    public void shouldAcceptPreComputedValuesKeyFunctionAndBiConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentHashMapBasedBiConsumerMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiConsumerMemoizer<>(
                precomputedValues, keyFunction, biConsumer);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullPreComputedValues() {
        // given
        final Map<String, String> precomputedValues = null;
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedBiConsumerMemoizer<>(precomputedValues, keyFunction, biConsumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedBiConsumerMemoizer<>(precomputedValues, keyFunction, biConsumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL BiConsumer - provide an actual BiConsumer to fix this.");

        // then
        new ConcurrentHashMapBasedBiConsumerMemoizer<>(precomputedValues, keyFunction, biConsumer);
    }

    /**
     *
     */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentHashMapBasedBiConsumerMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiConsumerMemoizer<>(
                precomputedValues, keyFunction, biConsumer);

        // then
        memoizer.accept("first", "second");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentHashMapBasedBiConsumerMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiConsumerMemoizer<>(
                precomputedValues, keyFunction, biConsumer);

        // then
        memoizer.accept("first", "second");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "firstsecond",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", "firstsecond",
                memoizer.viewCacheForTest().values().iterator().next());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiConsumer<String, String> biConsumer = Mockito.mock(BiConsumer.class);

        // when
        final ConcurrentHashMapBasedBiConsumerMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiConsumerMemoizer<>(
                precomputedValues, keyFunction, biConsumer);

        // then
        memoizer.accept("first", "second");
        Mockito.verify(biConsumer).accept("first", "second");
    }

}
