/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

class ConcurrentMapBasedBiPredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final var memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Boolean> cache = null;
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate));
    }

    @Test
    void shouldRequireNonNullKeyFunction() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate));
    }

    @Test
    void shouldRequireNonNullBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = null;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate));
    }

    @Test
    void shouldMemoizeBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final var memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);

        // then
        Assertions.assertTrue(memoizer.test("first", "second"));
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(
                cache, keyFunction, biPredicate);

        // then
        memoizer.test("first", "second");
        Assertions.assertEquals("firstsecond", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertTrue(memoizer.viewCacheForTest().values().iterator().next());
    }

    @Test
    void shouldUseCallWrappedBiPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = Mockito.mock(BiPredicate.class);

        // when
        final var memoizer = new ConcurrentMapBasedBiPredicateMemoizer<>(cache, keyFunction, biPredicate);

        // then
        memoizer.test("first", "second");
        Mockito.verify(biPredicate).test("first", "second");
    }

}
