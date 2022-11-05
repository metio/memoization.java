/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

class ConcurrentMapBasedDoublePredicateMemoizerTest {

    @Test
    void shouldAcceptCacheAndPredicate() {
        // given
        final ConcurrentMap<Double, Boolean> cache = new ConcurrentHashMap<>();
        final DoublePredicate predicate = input -> true;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Boolean> cache = null;
        final DoublePredicate predicate = input -> true;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldRequireNonNullPredicate() {
        // given
        final ConcurrentMap<Double, Boolean> cache = new ConcurrentHashMap<>();
        final DoublePredicate predicate = null;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate));
    }

    @Test
    void shouldMemoizePredicateCall() {
        // given
        final ConcurrentMap<Double, Boolean> cache = new ConcurrentHashMap<>();
        final DoublePredicate predicate = input -> true;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoizer = new ConcurrentMapBasedDoublePredicateMemoizer<>(cache, keyFunction, predicate);

        // then
        Assertions.assertTrue(memoizer.test(123.456D));
    }

}
