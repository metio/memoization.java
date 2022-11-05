/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ConcurrentMapBasedBooleanSupplierMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeySupplierAndValueSupplier() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final BooleanSupplier supplier = () -> true;

        // when
        final var memoizer = new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Boolean> cache = null;
        final Supplier<String> keySupplier = () -> "key";
        final BooleanSupplier supplier = () -> true;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullKeySupplier() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = null;
        final BooleanSupplier supplier = () -> true;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullValueSupplier() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final BooleanSupplier supplier = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldMemoizeSuppliedValue() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final BooleanSupplier supplier = () -> true;

        // when
        final var memoizer = new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.getAsBoolean());
    }

    @Test
    void shouldUseSuppliedKey() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final BooleanSupplier supplier = () -> true;

        // when
        final var memoizer = new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertTrue(memoizer.getAsBoolean()); // trigger once
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
    }

    @Test
    void shouldTriggerOnce() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final BooleanSupplier supplier = mock(BooleanSupplier.class);
        given(supplier.getAsBoolean()).willReturn(true);

        // when
        final var memoizer = new ConcurrentMapBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.getAsBoolean()); // triggers
        Assertions.assertTrue(memoizer.getAsBoolean()); // memoized
        Assertions.assertTrue(memoizer.getAsBoolean()); // memoized
        Assertions.assertTrue(memoizer.getAsBoolean()); // memoized
        verify(supplier, times(1)).getAsBoolean(); // real supplier triggered once, all other calls were memoized
    }

}
