/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.LongSupplier;
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
public class JCacheBasedLongSupplierMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final LongSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";
        try (final Cache<String, Long> cache = JCacheMemoize.createCache(Supplier.class)) {
            // when
            final JCacheBasedLongSupplierMemoizer<String> loader = new JCacheBasedLongSupplierMemoizer<>(cache,
                    keySupplier, supplier);

            // then
            Assert.assertEquals("Memoized value does not match expectation", 123L, loader.getAsLong());
        }
    }

}
