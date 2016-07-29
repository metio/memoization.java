/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaCacheBasedIntSupplierMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptLoadingCacheAndKeySupplier() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedIntSupplierMemoizer<String> memoizer = new GuavaCacheBasedIntSupplierMemoizer<>(
                cache, keySupplier, supplier);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldReturnSuppliedValue() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedIntSupplierMemoizer<String> memoizer = new GuavaCacheBasedIntSupplierMemoizer<>(
                cache, keySupplier, supplier);

        // then
        Assert.assertEquals(123, memoizer.getAsInt());
    }

}
