/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.DoublePredicate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapBasedDoublePredicateMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndPredicate() {
        // given
        final ConcurrentMap<Double, Boolean> cache = new ConcurrentHashMap<>();
        final DoublePredicate predicate = input -> true;

        // when
        final ConcurrentMapBasedDoublePredicateMemoizer memoizer = new ConcurrentMapBasedDoublePredicateMemoizer(
                cache, predicate);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<Double, Boolean> cache = null;
        final DoublePredicate predicate = input -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL.");

        // then
        new ConcurrentMapBasedDoublePredicateMemoizer(cache, predicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullPredicate() {
        // given
        final ConcurrentMap<Double, Boolean> cache = new ConcurrentHashMap<>();
        final DoublePredicate predicate = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");

        // then
        new ConcurrentMapBasedDoublePredicateMemoizer(cache, predicate);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicateCall() {
        // given
        final ConcurrentMap<Double, Boolean> cache = new ConcurrentHashMap<>();
        final DoublePredicate predicate = input -> true;

        // when
        final ConcurrentMapBasedDoublePredicateMemoizer memoizer = new ConcurrentMapBasedDoublePredicateMemoizer(
                cache, predicate);

        // then
        Assert.assertTrue("Memoized value does not match expectations", memoizer.test(123.456D));
    }

}
