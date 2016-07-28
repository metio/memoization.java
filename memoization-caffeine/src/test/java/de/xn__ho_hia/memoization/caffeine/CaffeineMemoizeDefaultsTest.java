/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.caffeine;

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
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
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
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineMemoizeDefaultsTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = CaffeineMemoize.function(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;

        // when
        final DoubleToIntFunction memoize = CaffeineMemoize.doubleToIntFunction(function);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;

        // when
        final DoubleToLongFunction memoize = CaffeineMemoize.doubleToLongFunction(function);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();

        // when
        final DoubleUnaryOperator memoize = CaffeineMemoize.doubleUnaryOperator(function);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator function = (first, second) -> first + second;

        // when
        final DoubleBinaryOperator memoize = CaffeineMemoize.doubleBinaryOperator(function);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator function = (first, second) -> first + second;

        // when
        final IntBinaryOperator memoize = CaffeineMemoize.intBinaryOperator(function);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;

        // when
        final IntToDoubleFunction memoize = CaffeineMemoize.intToDoubleFunction(function);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction function = a -> 123L;

        // when
        final IntToLongFunction memoize = CaffeineMemoize.intToLongFunction(function);

        // then
        Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator function = a -> 123;

        // when
        final IntUnaryOperator memoize = CaffeineMemoize.intUnaryOperator(function);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator function = (first, second) -> first + second;

        // when
        final LongBinaryOperator memoize = CaffeineMemoize.longBinaryOperator(function);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;

        // when
        final LongToDoubleFunction memoize = CaffeineMemoize.longToDoubleFunction(function);

        // then
        Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction function = a -> 123;

        // when
        final LongToIntFunction memoize = CaffeineMemoize.longToIntFunction(function);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator function = a -> 123L;

        // when
        final LongUnaryOperator memoize = CaffeineMemoize.longUnaryOperator(function);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = CaffeineMemoize.supplier(supplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final BooleanSupplier memoize = CaffeineMemoize.booleanSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final DoubleSupplier memoize = CaffeineMemoize.doubleSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final IntSupplier memoize = CaffeineMemoize.intSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123L;

        // when
        final LongSupplier memoize = CaffeineMemoize.longSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = (first, second) -> first.doubleValue()
                + second.doubleValue();

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoize.toDoubleBiFunction(function);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = (first, second) -> first.intValue() + second.intValue();

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoize.toIntBiFunction(function);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = (first, second) -> first.longValue()
                + second.longValue();

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoize.toLongBiFunction(function);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;

        // when
        final ToDoubleFunction<String> memoize = CaffeineMemoize.toDoubleFunction(function);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;

        // when
        final ToIntFunction<String> memoize = CaffeineMemoize.toIntFunction(function);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;

        // when
        final ToLongFunction<String> memoize = CaffeineMemoize.toLongFunction(function);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = a -> true;

        // when
        final Predicate<String> memoize = CaffeineMemoize.predicate(predicate);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = a -> true;

        // when
        final LongPredicate memoize = CaffeineMemoize.longPredicate(predicate);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = a -> true;

        // when
        final IntPredicate memoize = CaffeineMemoize.intPredicate(predicate);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = a -> true;

        // when
        final DoublePredicate memoize = CaffeineMemoize.doublePredicate(predicate);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = CaffeineMemoize.consumer(consumer);

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
        final DoubleConsumer memoize = CaffeineMemoize.doubleConsumer(consumer);

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
        final IntConsumer memoize = CaffeineMemoize.intConsumer(consumer);

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
        final LongConsumer memoize = CaffeineMemoize.longConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<Long, Long> biPredicate = (first, second) -> true;

        // when
        final BiPredicate<Long, Long> memoize = CaffeineMemoize.biPredicate(biPredicate);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final BiFunction<Long, Long, String> function = (first, second) -> "test";

        // when
        final BiFunction<Long, Long, String> memoize = CaffeineMemoize.biFunction(function);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<Long, Long> function = (first, second) -> System.out.println(first + " " + second);

        // when
        final BiConsumer<Long, Long> memoize = CaffeineMemoize.biConsumer(function);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<Long> consumer = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjDoubleConsumer<Long> memoize = CaffeineMemoize.objDoubleConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjIntConsumer() {
        // given
        final ObjIntConsumer<Long> consumer = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjIntConsumer<Long> memoize = CaffeineMemoize.objIntConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized ObjIntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjLongConsumer() {
        // given
        final ObjLongConsumer<Long> function = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjLongConsumer<Long> memoize = CaffeineMemoize.objLongConsumer(function);

        // then
        Assert.assertNotNull("Memoized ObjLongConsumer is NULL", memoize);
    }

}
