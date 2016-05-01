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

import java.util.function.Supplier;

import com.google.common.cache.CacheLoader;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 *
 */
public class SupplierBasedCacheLoaderTest {

    /**
     *
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @SuppressWarnings("static-method")
    @Test
    public void shouldRequireSupplierToConstruct() {
        // given
        final Supplier<String> supplier = () -> "test"; //$NON-NLS-1$

        // when
        final CacheLoader<String, String> cacheLoader = new SupplierBasedCacheLoader<>(supplier);

        // then
        Assert.assertNotNull(cacheLoader);
    }

    /**
     *
     */
    @SuppressWarnings("unused")
    @Test
    public void shouldDeclineNullSupplier() {
        // given
        final Supplier<String> supplier = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a supplier to load values into the cache!"); //$NON-NLS-1$

        // then
        new SupplierBasedCacheLoader<>(supplier);
    }

    /**
     * @throws Exception
     *             If the cache is unable to load the result.
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    public void shouldCallProvidedSupplierDuringLoad() throws Exception {
        // given
        final CacheLoader<String, String> cacheLoader = new SupplierBasedCacheLoader<>(() -> "value");

        // when
        final String loadedValue = cacheLoader.load("key");

        // then
        Assert.assertEquals("value", loadedValue);
    }

}
