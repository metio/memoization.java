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
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.utils.memoization.map.ConcurrentHashMapBasedFunctionMemoizer;

/**
 *
 */
@SuppressWarnings("nls")
public class ConcurrentHashMapBasedFunctionMemoizerTest {

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
    public void shouldAcceptPreComputedValuesAndFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> function = input -> "output";

        // when
        final ConcurrentHashMapBasedFunctionMemoizer<String, String> memoizer = new ConcurrentHashMapBasedFunctionMemoizer<>(
                precomputedValues, function);

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
        final Function<String, String> function = input -> "output";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedFunctionMemoizer<>(precomputedValues, function);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL function - provide an actual function to fix this.");

        // then
        new ConcurrentHashMapBasedFunctionMemoizer<>(precomputedValues, function);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldMemoizeFunctionCall() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> function = input -> "output";

        // when
        final ConcurrentHashMapBasedFunctionMemoizer<String, String> memoizer = new ConcurrentHashMapBasedFunctionMemoizer<>(
                precomputedValues, function);

        // then
        Assert.assertEquals("Memoized value does not match expectations", "output", memoizer.apply("test"));
    }

}
