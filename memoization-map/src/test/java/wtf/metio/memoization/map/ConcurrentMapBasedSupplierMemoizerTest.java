/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ConcurrentMapBasedSupplierMemoizerTest {

    @Test
    void shouldAcceptPreComputedValuesKeySupplierAndValueSupplier() {
        // given
        final ConcurrentMap<String, String> precomputedValues = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> precomputedValues = null;
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullKeySupplier() {
        // given
        final ConcurrentMap<String, String> precomputedValues = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = null;
        final Supplier<String> supplier = () -> "value";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier));
    }

    @Test
    void shouldRequireNonNullValueSupplier() {
        // given
        final ConcurrentMap<String, String> precomputedValues = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier));
    }

    @Test
    void shouldMemoizeSuppliedValue() {
        // given
        final ConcurrentMap<String, String> precomputedValues = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assertions.assertEquals("value", memoizer.get());
    }

    @Test
    void shouldUseSuppliedKey() {
        // given
        final ConcurrentMap<String, String> precomputedValues = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final var memoizer = new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("value", memoizer.get()); // trigger once
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
    }

    @Test
    void shouldTriggerOnce() {
        // given
        final ConcurrentMap<String, String> precomputedValues = new ConcurrentHashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = mock(Supplier.class);
        given(supplier.get()).willReturn("mocked");

        // when
        final var memoizer = new ConcurrentMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assertions.assertEquals("mocked", memoizer.get()); // triggers
        Assertions.assertEquals("mocked", memoizer.get()); // memoized
        Assertions.assertEquals("mocked", memoizer.get()); // memoized
        Assertions.assertEquals("mocked", memoizer.get()); // memoized
        verify(supplier, times(1)).get(); // real supplier triggered once, all other calls were memoized
    }

}
