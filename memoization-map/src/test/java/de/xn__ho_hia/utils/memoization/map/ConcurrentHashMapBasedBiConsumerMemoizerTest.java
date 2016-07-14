/*
 * ysura GmbH ("COMPANY") CONFIDENTIAL
 * Unpublished Copyright (c) 2012-2015 ysura GmbH, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of COMPANY. The intellectual and technical concepts contained
 * herein are proprietary to COMPANY and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from COMPANY.  Access to the source code contained herein is hereby forbidden to anyone except current COMPANY employees, managers or contractors who have executed
 * Confidentiality and Non-disclosure agreements explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
 * information that is confidential and/or proprietary, and is a trade secret, of COMPANY. ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC PERFORMANCE,
 * OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS SOURCE CODE WITHOUT THE EXPRESS WRITTEN CONSENT OF COMPANY IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES. THE RECEIPT OR POSSESSION OF THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
 * TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 */
package de.xn__ho_hia.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 */
@SuppressWarnings("nls")
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
    @SuppressWarnings("static-method")
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
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
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
    @SuppressWarnings("static-method")
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
    @SuppressWarnings("static-method")
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
    @SuppressWarnings({ "unchecked", "static-method" })
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
