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
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheMemoizeCustomKeyTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplierWithKeySupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";

        // when
        final Supplier<String> memoize = JCacheMemoize.supplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplierWithKeySupplier() {
        // given
        final BooleanSupplier supplier = () -> true;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final BooleanSupplier memoize = JCacheMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = Function.identity();

        // when
        final Function<String, String> memoize = JCacheMemoize.function(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntFunctionWithKeyFunction() {
        // given
        final IntFunction<String> function = a -> "test";
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntFunction<String> memoize = JCacheMemoize.intFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongFunctionWithKeyFunction() {
        // given
        final LongFunction<String> function = a -> "test";
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongFunction<String> memoize = JCacheMemoize.longFunction(function, keyFunction);

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
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleFunction<String> memoize = JCacheMemoize.doubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final Predicate<String> memoize = JCacheMemoize.predicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicateWithKeyFunction() {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoublePredicate memoize = JCacheMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntPredicateWithKeyFunction() {
        // given
        final IntPredicate predicate = a -> true;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntPredicate memoize = JCacheMemoize.intPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicateWithKeyFunction() {
        // given
        final LongPredicate predicate = a -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongPredicate memoize = JCacheMemoize.longPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerWithKeyFunction() {
        // given
        final Consumer<String> consumer = System.out::println;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final Consumer<String> memoize = JCacheMemoize.consumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleConsumerWithKeyFunction() {
        // given
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleConsumer memoize = JCacheMemoize.doubleConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntConsumerWithKeyFunction() {
        // given
        final IntConsumer consumer = System.out::println;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntConsumer memoize = JCacheMemoize.intConsumer(consumer, keyFunction);

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
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongConsumer memoize = JCacheMemoize.longConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumerWithKeyFunction() {
        // given
        final BiConsumer<String, String> consumer = (a, b) -> System.out.println(a + b);
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiConsumer<String, String> memoize = JCacheMemoize.biConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunctionWithKeyBiFunction() {
        // given
        final BiFunction<String, String, String> function = (first, second) -> "test";
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiFunction<String, String, String> memoize = JCacheMemoize.biFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicateWithKeyBiFunction() {
        // given
        final BiPredicate<String, String> biPredicate = (first, second) -> true;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiPredicate<String, String> memoize = JCacheMemoize.biPredicate(biPredicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

}
