/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

class GuavaCacheBasedDoubleSupplierMemoizerTest {

    @Test
    void shouldAcceptLoadingCacheAndKeySupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldReturnSuppliedValue() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Double> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedDoubleSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123.456D, memoizer.getAsDouble(), 0.0D);
    }

}
