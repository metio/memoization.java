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
package com.github.sebhoss.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 */
@SuppressWarnings("nls")
public class ConcurrentHashMapBasedBiFunctionMemoizerTest {

    /** */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldAcceptPreComputedValuesKeyBiFunctionAndBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

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
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullKeyBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'Function.identity()'.");

        // then
        new ConcurrentHashMapBasedBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL BiFunction - provide an actual BiFunction to fix this.");

        // then
        new ConcurrentHashMapBasedBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("static-method")
    public void shouldReturnGivenBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoizer.getBiFunction());
        Assert.assertSame("Memoized BiFunction is not the same", biFunction, memoizer.getBiFunction());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("static-method")
    public void shouldReturnGivenKeyFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Key function is NULL", memoizer.getKeyFunction());
        Assert.assertSame("Key function is not the same", keyFunction, memoizer.getKeyFunction());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("static-method")
    public void shouldReturnNonNullMemoizationFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Memoizing function is NULL", memoizer.getMemoizingFunction());
    }

}
