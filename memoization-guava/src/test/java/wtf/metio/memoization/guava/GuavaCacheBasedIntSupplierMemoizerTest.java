/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

class GuavaCacheBasedIntSupplierMemoizerTest {

    @Test
    void shouldAcceptLoadingCacheAndKeySupplier() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldReturnSuppliedValue() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedIntSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals(123, memoizer.getAsInt());
    }

}
