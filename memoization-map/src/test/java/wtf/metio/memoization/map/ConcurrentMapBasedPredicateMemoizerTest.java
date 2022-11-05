/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;

class ConcurrentMapBasedPredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Predicate<String> predicate = input -> true;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Boolean> cache = null;
        final Predicate<String> predicate = input -> true;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedPredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldRequireNonNullPredicate() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Predicate<String> predicate = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedPredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldMemoizePredicateCall() {
        // given
        final ConcurrentMap<String, Boolean> cache = new ConcurrentHashMap<>();
        final Predicate<String> predicate = input -> true;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertTrue(memoizer.test("test"));
    }

}
