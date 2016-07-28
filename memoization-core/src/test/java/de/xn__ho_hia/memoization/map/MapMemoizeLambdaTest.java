/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class MapMemoizeLambdaTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplierWithLambda() {
        // given

        // when
        final Supplier<String> memoize = MapMemoize.supplier(() -> "test");

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplierWithLambda() {
        // given

        // when
        final BooleanSupplier memoize = MapMemoize.booleanSupplier(() -> true);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplierWithLambda() {
        // given

        // when
        final DoubleSupplier memoize = MapMemoize.doubleSupplier(() -> 123.456D);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplierWithLambda() {
        // given

        // when
        final IntSupplier memoize = MapMemoize.intSupplier(() -> 123);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunctionWithLambda() {
        // given

        // when
        final Function<String, String> memoize = MapMemoize.function(a -> "test");

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunctionWithLambda() {
        // given

        // when
        final BiFunction<String, String, String> memoize = MapMemoize.biFunction((a, b) -> "test");

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerWithLambda() {
        // given

        // when
        final Consumer<String> memoize = MapMemoize.consumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumerWithLambda() {
        // given

        // when
        final BiConsumer<String, String> memoize = MapMemoize
                .biConsumer((first, second) -> System.out.println(first + second));

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumerWithLambda() {
        // given

        // when
        final ObjDoubleConsumer<String> memoize = MapMemoize
                .objDoubleConsumer((first, second) -> System.out.println(first + second));

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleConsumerWithLambda() {
        // given

        // when
        final DoubleConsumer memoize = MapMemoize.doubleConsumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldIntMemoizeConsumerWithLambda() {
        // given

        // when
        final IntConsumer memoize = MapMemoize.intConsumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongConsumerWithLambda() {
        // given

        // when
        final LongConsumer memoize = MapMemoize.longConsumer(System.out::println);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntFunctionWithLambda() {
        // given

        // when
        final IntFunction<String> memoize = MapMemoize.intFunction(a -> "test");

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongFunctionWithLambda() {
        // given

        // when
        final LongFunction<String> memoize = MapMemoize.longFunction(a -> "test");

        // then
        Assert.assertNotNull("Memoized LongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjIntConsumerWithLambda() {
        // given

        // when
        final ObjIntConsumer<String> memoize = MapMemoize
                .objIntConsumer((first, second) -> System.out.println(first + second));

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
        final ObjLongConsumer<String> memoize = MapMemoize
                .objLongConsumer((first, second) -> System.out.println(first + second));

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
        final Predicate<String> memoize = MapMemoize.predicate(input -> true);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicateWithLambda() {
        // given

        // when
        final BiPredicate<String, String> memoize = MapMemoize.biPredicate((first, second) -> true);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicateWithLambda() {
        // given

        // when
        final DoublePredicate memoize = MapMemoize.doublePredicate(input -> true);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntPredicateWithLambda() {
        // given

        // when
        final IntPredicate memoize = MapMemoize.intPredicate(input -> true);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicateWithLambda() {
        // given

        // when
        final LongPredicate memoize = MapMemoize.longPredicate(input -> true);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperatorWithLambda() {
        // given

        // when
        final DoubleBinaryOperator memoize = MapMemoize.doubleBinaryOperator((first, second) -> first + second);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperatorWithLambda() {
        // given

        // when
        final IntBinaryOperator memoize = MapMemoize.intBinaryOperator((first, second) -> first + second);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperatorWithLambda() {
        // given

        // when
        final LongBinaryOperator memoize = MapMemoize.longBinaryOperator((first, second) -> first + second);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperatorWithLambda() {
        // given

        // when
        final DoubleUnaryOperator memoize = MapMemoize.doubleUnaryOperator(input -> 123.456D);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntUnaryOperatorWithLambda() {
        // given

        // when
        final IntUnaryOperator memoize = MapMemoize.intUnaryOperator(input -> 123);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperatorWithLambda() {
        // given

        // when
        final LongUnaryOperator memoize = MapMemoize.longUnaryOperator(input -> 123L);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunctionWithLambda() {
        // given

        // when
        final DoubleToIntFunction memoize = MapMemoize.doubleToIntFunction(input -> 123);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunctionWithLambda() {
        // given

        // when
        final DoubleToLongFunction memoize = MapMemoize.doubleToLongFunction(input -> 123L);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunctionWithLambda() {
        // given

        // when
        final IntToDoubleFunction memoize = MapMemoize.intToDoubleFunction(input -> 123.456D);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunctionWithLambda() {
        // given

        // when
        final IntToLongFunction memoize = MapMemoize.intToLongFunction(input -> 123L);

        // then
        Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunctionWithLambda() {
        // given

        // when
        final LongToDoubleFunction memoize = MapMemoize.longToDoubleFunction(input -> 123D);

        // then
        Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToIntFunctionWithLambda() {
        // given

        // when
        final LongToIntFunction memoize = MapMemoize.longToIntFunction(input -> 123);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunctionWithLambda() {
        // given

        // when
        final ToDoubleFunction<String> memoize = MapMemoize.toDoubleFunction(Double::parseDouble);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunctionWithLambda() {
        // given

        // when
        final ToIntFunction<String> memoize = MapMemoize.toIntFunction(Integer::parseInt);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunctionWithLambda() {
        // given

        // when
        final ToLongFunction<String> memoize = MapMemoize.toLongFunction(Long::parseLong);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunctionWithLambda() {
        // given

        // when
        final ToDoubleBiFunction<String, String> memoize = MapMemoize
                .toDoubleBiFunction((first, second) -> 123.456D);

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
        final ToIntBiFunction<String, String> memoize = MapMemoize.toIntBiFunction((first, second) -> 123);

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
        final ToLongBiFunction<String, String> memoize = MapMemoize.toLongBiFunction((first, second) -> 123);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

}
