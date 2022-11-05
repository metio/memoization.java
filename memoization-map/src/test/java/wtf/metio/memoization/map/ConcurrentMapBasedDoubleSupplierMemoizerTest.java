/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ConcurrentMapBasedDoubleSupplierMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeySupplierAndValueSupplier() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Double> cache = null;
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullKeySupplier() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = null;
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullValueSupplier() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier));
    }

    @Test
    void shouldMemoizeSuppliedValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D);
    }

    @Test
    void shouldUseSuppliedKey() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final var memoizer = new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D);
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
    }

    @Test
    void shouldTriggerOnce() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = mock(DoubleSupplier.class);
        given(supplier.getAsDouble()).willReturn(123.456D);

        // when
        final var memoizer = new ConcurrentMapBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D); // triggers
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D); // memoized
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D); // memoized
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D); // memoized
        verify(supplier, times(1)).getAsDouble(); // real supplier triggered once, all other calls were memoized
    }

}
