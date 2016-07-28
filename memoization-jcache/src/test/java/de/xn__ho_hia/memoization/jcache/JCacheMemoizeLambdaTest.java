/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheMemoizeLambdaTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplierWithLambda() {
        // given

        // when
        final Supplier<String> memoize = JCacheMemoize.supplier(() -> "test");

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunctionWithLambda() {
        // given

        // when
        final Function<String, String> memoize = JCacheMemoize.function(a -> "test");

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntFunctionWithLambda() {
        // given

        // when
        final IntFunction<String> memoize = JCacheMemoize.intFunction(a -> "test");

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicateWithLambda() {
        // given

        // when
        final Predicate<String> memoize = JCacheMemoize.predicate(a -> true);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerWithLambda() {
        // given

        // when
        final Consumer<String> memoize = JCacheMemoize.consumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunctionWithLambda() {
        // given

        // when
        final BiFunction<String, String, String> memoize = JCacheMemoize.biFunction((first, second) -> "test");

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

}
