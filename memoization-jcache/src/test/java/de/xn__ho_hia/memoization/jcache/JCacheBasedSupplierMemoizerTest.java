/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.cache.Cache;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheBasedSupplierMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";
        try (final Cache<String, String> cache = JCacheMemoize.createCache(Function.class)) {
            // when
            final JCacheBasedSupplierMemoizer<String, String> loader = new JCacheBasedSupplierMemoizer<>(cache,
                    keySupplier, supplier);

            // then
            Assert.assertEquals("Memoized value does not match expectation", "test", loader.get());
        }
    }

}
