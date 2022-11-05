/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

class GuavaCacheBasedToLongBiFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndBiFunction() {
        // given
        final ToLongBiFunction<String, String> biFunction = (first, second) -> 123;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, Long> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final ToLongBiFunction<String, String> biFunction = (first, second) -> 123;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, Long> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedToLongBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertEquals(123, memoizer.applyAsLong("first", "second"));
    }

}
