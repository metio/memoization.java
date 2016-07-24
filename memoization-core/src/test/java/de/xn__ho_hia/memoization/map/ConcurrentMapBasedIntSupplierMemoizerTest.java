/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 * Unit tests for ConcurrentMapBasedIntSupplierMemoizer.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedIntSupplierMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that a new instance can be created, given that pre-computed values, a key-supplier and the actual value
     * supplier to memoize is present. If any of those values is <code>null</code>, an exception should be thrown.
     */
    @Test
    public void shouldAcceptCacheAndKeySupplierAndValueSupplier() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        final ConcurrentMapBasedIntSupplierMemoizer<String> memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(
                cache, keySupplier, supplier);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Integer> cache = null;
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeySupplier() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = null;
        final IntSupplier supplier = () -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");

        // then
        new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullValueSupplier() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");

        // then
        new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    public void shouldMemoizeSuppliedValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        final ConcurrentMapBasedIntSupplierMemoizer<String> memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(
                cache, keySupplier, supplier);

        // then
        Assert.assertEquals("Memoized value does not match expectations", 123, memoizer.getAsInt());
    }

    /**
     *
     */
    @Test
    public void shouldUseSuppliedKey() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        final ConcurrentMapBasedIntSupplierMemoizer<String> memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(
                cache, keySupplier, supplier);

        // then
        Assert.assertTrue("Cache is not empty before memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoized value does not match expectations", 123, memoizer.getAsInt());
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldTriggerOnce() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = mock(IntSupplier.class);
        given(supplier.getAsInt()).willReturn(123);

        // when
        final ConcurrentMapBasedIntSupplierMemoizer<String> memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(
                cache, keySupplier, supplier);

        // then
        Assert.assertEquals("Memoized value does not match expectations", 123, memoizer.getAsInt()); // triggers
        Assert.assertEquals("Memoized value does not match expectations", 123, memoizer.getAsInt()); // memoized
        Assert.assertEquals("Memoized value does not match expectations", 123, memoizer.getAsInt()); // memoized
        Assert.assertEquals("Memoized value does not match expectations", 123, memoizer.getAsInt()); // memoized
        verify(supplier, times(1)).getAsInt(); // real supplier triggered once, all other calls were memoized
    }

}
