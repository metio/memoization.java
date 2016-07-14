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
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 */
@SuppressWarnings("nls")
public class ConcurrentHashMapBasedConsumerMemoizerTest {

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
    public void shouldAcceptPreComputedValuesKeyFunctionAndConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

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
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullKeyFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = null;
        final Consumer<String> consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'Function.identity()'.");

        // then
        new ConcurrentHashMapBasedConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentHashMapBasedConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldMemoizeConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

        // then
        memoizer.accept("test");
    }

}
