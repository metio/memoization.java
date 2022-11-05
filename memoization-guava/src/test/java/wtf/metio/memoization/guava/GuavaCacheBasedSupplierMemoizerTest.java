/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

class GuavaCacheBasedSupplierMemoizerTest {

    @Test
    void shouldAcceptLoadingCacheAndKeySupplier() {
        // given
        final Supplier<String> supplier = () -> "value";
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldReturnSuppliedValue() {
        // given
        final Supplier<String> supplier = () -> "value";
        final Supplier<String> keySupplier = () -> "key";
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedSupplierMemoizer<>(cache, keySupplier, supplier);

        // then
        Assertions.assertEquals("value", memoizer.get());
    }

}
