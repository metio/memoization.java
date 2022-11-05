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
import java.util.function.BiPredicate;

class GuavaCacheBasedBiPredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndBiPredicate() {
        // given
        final BiPredicate<String, String> biFunction = (first, second) -> true;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedBiPredicateMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldTransformInput() {
        // given
        final BiPredicate<String, String> biFunction = (first, second) -> true;
        final BiFunction<String, String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, Boolean> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedBiPredicateMemoizer<>(cache, keyFunction, biFunction);

        // then
        Assertions.assertTrue(memoizer.test("first", "second"));
    }

}
