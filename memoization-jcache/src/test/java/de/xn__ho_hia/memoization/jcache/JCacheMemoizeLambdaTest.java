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
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;

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
    public void shouldMemoizeBiConsumerWithLambda() {
        // given

        // when
        final BiConsumer<String, String> memoize = JCacheMemoize.biConsumer((a, b) -> System.out.println(a + b));

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
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

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicateWithLambda() {
        // given

        // when
        final BiPredicate<String, String> memoize = JCacheMemoize.biPredicate((first, second) -> true);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplierWithLambda() {
        // given

        // when
        final BooleanSupplier memoize = JCacheMemoize.booleanSupplier(() -> true);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
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
    public void shouldMemoizeDoubleBinaryOperatorWithLambda() {
        // given

        // when
        final DoubleBinaryOperator memoize = JCacheMemoize.doubleBinaryOperator((a, b) -> 123.456D);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleConsumerWithLambda() {
        // given

        // when
        final DoubleConsumer memoize = JCacheMemoize.doubleConsumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleFunctionWithLambda() {
        // given

        // when
        final DoubleFunction<String> memoize = JCacheMemoize.doubleFunction(a -> "test");

        // then
        Assert.assertNotNull("Memoized DoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicateWithLambda() {
        // given

        // when
        final DoublePredicate memoize = JCacheMemoize.doublePredicate(a -> true);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplierWithLambda() {
        // given

        // when
        final DoubleSupplier memoize = JCacheMemoize.doubleSupplier(() -> 123.456D);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
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
    public void shouldMemoizeIntBinaryOperatorWithLambda() {
        // given

        // when
        final IntBinaryOperator memoize = JCacheMemoize.intBinaryOperator((a, b) -> 123);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntConsumerWithLambda() {
        // given

        // when
        final IntConsumer memoize = JCacheMemoize.intConsumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
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
    public void shouldMemoizeIntPredicateWithLambda() {
        // given

        // when
        final IntPredicate memoize = JCacheMemoize.intPredicate(a -> true);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplierWithLambda() {
        // given

        // when
        final IntSupplier memoize = JCacheMemoize.intSupplier(() -> 123);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperatorWithLambda() {
        // given

        // when
        final LongBinaryOperator memoize = JCacheMemoize.longBinaryOperator((a, b) -> 123L);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongConsumerWithLambda() {
        // given

        // when
        final LongConsumer memoize = JCacheMemoize.longConsumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongFunctionWithLambda() {
        // given

        // when
        final LongFunction<String> memoize = JCacheMemoize.longFunction(a -> "test");

        // then
        Assert.assertNotNull("Memoized LongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicateWithLambda() {
        // given

        // when
        final LongPredicate memoize = JCacheMemoize.longPredicate(a -> true);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplierWithLambda() {
        // given

        // when
        final LongSupplier memoize = JCacheMemoize.longSupplier(() -> 123L);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumerWithLambda() {
        // given

        // when
        final ObjDoubleConsumer<String> memoize = JCacheMemoize.objDoubleConsumer((a, b) -> System.out.println(a + b));

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjIntConsumerWithLambda() {
        // given

        // when
        final ObjIntConsumer<String> memoize = JCacheMemoize.objIntConsumer((a, b) -> System.out.println(a + b));

        // then
        Assert.assertNotNull("Memoized ObjIntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjLongConsumerWithLambda() {
        // given

        // when
        final ObjLongConsumer<String> memoize = JCacheMemoize.objLongConsumer((a, b) -> System.out.println(a + b));

        // then
        Assert.assertNotNull("Memoized ObjLongConsumer is NULL", memoize);
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
    public void shouldMemoizeToDoubleBiFunctionWithLambda() {
        // given

        // when
        final ToDoubleBiFunction<String, String> memoize = JCacheMemoize.toDoubleBiFunction((a, b) -> 123);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunctionWithLambda() {
        // given

        // when
        final ToIntBiFunction<String, String> memoize = JCacheMemoize.toIntBiFunction((a, b) -> 123);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunctionWithLambda() {
        // given

        // when
        final ToLongBiFunction<String, String> memoize = JCacheMemoize.toLongBiFunction((a, b) -> 123);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

}
