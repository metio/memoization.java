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

class GuavaCacheBasedBiFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndBiFunction() {
        // given
        final BiFunction<String, String, String> biFunction = (first, second) -> first + second;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final BiFunction<String, String, String> biFunction = (first, second) -> first + second;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedBiFunctionMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertEquals("firstsecond", memoizer.apply("first", "second"));
    }

}
