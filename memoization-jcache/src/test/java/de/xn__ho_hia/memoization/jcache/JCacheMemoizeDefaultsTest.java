/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheMemoizeDefaultsTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = JCacheMemoize.supplier(supplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = JCacheMemoize.function(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntFunction() {
        // given
        final IntFunction<String> function = a -> "test";

        // when
        final IntFunction<String> memoize = JCacheMemoize.intFunction(function);

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongFunction() {
        // given
        final LongFunction<String> function = a -> "test";

        // when
        final LongFunction<String> memoize = JCacheMemoize.longFunction(function);

        // then
        Assert.assertNotNull("Memoized LongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";

        // when
        final DoubleFunction<String> memoize = JCacheMemoize.doubleFunction(function);

        // then
        Assert.assertNotNull("Memoized DoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = a -> true;

        // when
        final Predicate<String> memoize = JCacheMemoize.predicate(predicate);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = JCacheMemoize.consumer(consumer);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final DoubleConsumer memoize = JCacheMemoize.doubleConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final IntConsumer memoize = JCacheMemoize.intConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final LongConsumer memoize = JCacheMemoize.longConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<String, String> consumer = (a, b) -> System.out.println(a + b);

        // when
        final BiConsumer<String, String> memoize = JCacheMemoize.biConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final BiFunction<String, String, String> function = (first, second) -> "test";

        // when
        final BiFunction<String, String, String> memoize = JCacheMemoize.biFunction(function);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final BiPredicate<String, String> memoize = JCacheMemoize.biPredicate(biPredicate);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

}
