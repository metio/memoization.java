/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

class ConcurrentMapBasedIntPredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndPredicate() {
        // given
        final ConcurrentMap<Integer, Boolean> cache = new ConcurrentHashMap<>();
        final IntPredicate predicate = input -> true;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Integer, Boolean> cache = null;
        final IntPredicate predicate = input -> true;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntPredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldRequireNonNullPredicate() {
        // given
        final ConcurrentMap<Integer, Boolean> cache = new ConcurrentHashMap<>();
        final IntPredicate predicate = null;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntPredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldMemoizePredicateCall() {
        // given
        final ConcurrentMap<Integer, Boolean> cache = new ConcurrentHashMap<>();
        final IntPredicate predicate = input -> true;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedIntPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertTrue(memoizer.test(123));
    }

}
