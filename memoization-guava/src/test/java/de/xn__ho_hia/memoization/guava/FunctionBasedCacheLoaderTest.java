/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.Function;

import com.google.common.cache.CacheLoader;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 *
 */
public class FunctionBasedCacheLoaderTest {

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
    public void shouldRequireFunctionToConstruct() {
        // given
        final Function<String, String> function = Function.identity();

        // when
        final CacheLoader<String, String> cacheLoader = new FunctionBasedCacheLoader<>(function);

        // then
        Assert.assertNotNull(cacheLoader);
    }

    /**
     *
     */
    @SuppressWarnings("unused")
    @Test
    public void shouldDeclineNullFunction() {
        // given
        final Function<String, String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a function to load values into the cache!"); //$NON-NLS-1$

        // then
        new FunctionBasedCacheLoader<>(function);
    }

    /**
     * @throws Exception
     *             If the cache is unable to load the result
     */
    @SuppressWarnings("static-method")
    @Test
    public void shouldCallProvidedFunctionDuringLoad() throws Exception {
        // given
        final CacheLoader<String, String> cacheLoader = new FunctionBasedCacheLoader<>(Function.identity());

        // when
        final String loadedValue = cacheLoader.load("test"); //$NON-NLS-1$

        // then
        Assert.assertEquals("test", loadedValue); //$NON-NLS-1$
    }

}
