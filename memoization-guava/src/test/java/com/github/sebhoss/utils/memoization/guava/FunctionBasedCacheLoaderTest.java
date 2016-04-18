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
package com.github.sebhoss.utils.memoization.guava;

import com.google.common.cache.CacheLoader;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.Function;

public class FunctionBasedCacheLoaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldRequireFunctionToConstruct() {
        // given
        final Function<String, String> function = Function.identity();

        // when
        final CacheLoader<String, String> cacheLoader = new FunctionBasedCacheLoader<>(function);

        // then
        Assert.assertNotNull(cacheLoader);
    }

    @Test
    public void shouldDeclineNullFunction() {
        // given
        final Function<String, String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a function to load values into the cache!");

        // then
        new FunctionBasedCacheLoader<>(function);
    }

    @Test
    public void shouldCallProvidedFunctionDuringLoad() throws Exception {
        // given
        final CacheLoader<String, String> cacheLoader = new FunctionBasedCacheLoader<>(Function.identity());

        // when
        final String loadedValue = cacheLoader.load("test");

        // then
        Assert.assertEquals("test", loadedValue);
    }

}
