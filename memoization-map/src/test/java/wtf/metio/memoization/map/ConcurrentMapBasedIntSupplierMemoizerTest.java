/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ConcurrentMapBasedIntSupplierMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeySupplierAndValueSupplier() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Integer> cache = null;
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullKeySupplier() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = null;
        final IntSupplier supplier = () -> 123;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullValueSupplier() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldMemoizeSuppliedValue() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123, memoizer.getAsInt());
    }

    @Test
    void shouldUseSuppliedKey() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = () -> 123;

        // when
        final var memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123, memoizer.getAsInt());
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
    }

    @Test
    void shouldTriggerOnce() {
        // given
        final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final IntSupplier supplier = mock(IntSupplier.class);
        given(supplier.getAsInt()).willReturn(123);

        // when
        final var memoizer = new ConcurrentMapBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123, memoizer.getAsInt()); // triggers
        Assertions.assertEquals(123, memoizer.getAsInt()); // memoized
        Assertions.assertEquals(123, memoizer.getAsInt()); // memoized
        Assertions.assertEquals(123, memoizer.getAsInt()); // memoized
        verify(supplier, times(1)).getAsInt(); // real supplier triggered once, all other calls were memoized
    }

}
