/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

class GuavaCacheBasedBooleanSupplierMemoizerTest {

    @Test
    void shouldAcceptLoadingCacheAndKeySupplier() {
        // given
        final BooleanSupplier supplier = () -> true;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldReturnSuppliedValue() {
        // given
        final BooleanSupplier supplier = () -> true;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedBooleanSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertTrue(memoizer.getAsBoolean());
    }

}
