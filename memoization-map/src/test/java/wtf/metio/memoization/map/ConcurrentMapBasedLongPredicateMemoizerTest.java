/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;

class ConcurrentMapBasedLongPredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndPredicate() {
        // given
        final ConcurrentMap<Long, Boolean> cache = new ConcurrentHashMap<>();
        final LongPredicate predicate = input -> true;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Long, Boolean> cache = null;
        final LongPredicate predicate = input -> true;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongPredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldRequireNonNullPredicate() {
        // given
        final ConcurrentMap<Long, Boolean> cache = new ConcurrentHashMap<>();
        final LongPredicate predicate = null;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongPredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldMemoizePredicateCall() {
        // given
        final ConcurrentMap<Long, Boolean> cache = new ConcurrentHashMap<>();
        final LongPredicate predicate = input -> true;
        final LongFunction<Long> keyFunction = Long::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedLongPredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertTrue(memoizer.test(123L));
    }

}
