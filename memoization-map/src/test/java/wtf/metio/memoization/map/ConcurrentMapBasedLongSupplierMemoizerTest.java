/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ConcurrentMapBasedLongSupplierMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeySupplierAndValueSupplier() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final LongSupplier supplier = () -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Long> cache = null;
        final Supplier<String> keySupplier = () -> "key";
        final LongSupplier supplier = () -> 123L;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullKeySupplier() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = null;
        final LongSupplier supplier = () -> 123L;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullValueSupplier() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final LongSupplier supplier = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldMemoizeSuppliedValue() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final LongSupplier supplier = () -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123L, memoizer.getAsLong());
    }

    @Test
    void shouldUseSuppliedKey() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final LongSupplier supplier = () -> 123L;

        // when
        final var memoizer = new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123L, memoizer.getAsLong());
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
    }

    @Test
    void shouldTriggerOnce() {
        // given
        final ConcurrentMap<String, Long> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final LongSupplier supplier = mock(LongSupplier.class);
        given(supplier.getAsLong()).willReturn(123L);

        // when
        final var memoizer = new ConcurrentMapBasedLongSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123L, memoizer.getAsLong()); // triggers
        Assertions.assertEquals(123L, memoizer.getAsLong()); // memoized
        Assertions.assertEquals(123L, memoizer.getAsLong()); // memoized
        Assertions.assertEquals(123L, memoizer.getAsLong()); // memoized
        verify(supplier, times(1)).getAsLong(); // real supplier triggered once, all other calls were memoized
    }

}
