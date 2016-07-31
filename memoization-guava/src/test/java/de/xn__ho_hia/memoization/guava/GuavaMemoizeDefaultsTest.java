/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

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
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
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
public class GuavaMemoizeDefaultsTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);

        // when
        final BiConsumer<String, String> memoize = GuavaMemoize.biConsumer(biConsumer);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";

        // when
        final BiFunction<String, String, String> memoize = GuavaMemoize.biFunction(bifunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<String, String> biPredicate = (a, b) -> true;

        // when
        final BiPredicate<String, String> memoize = GuavaMemoize.biPredicate(biPredicate);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final BooleanSupplier memoize = GuavaMemoize.booleanSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = GuavaMemoize.consumer(consumer);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator operator = (a, b) -> 123.456D;

        // when
        final DoubleBinaryOperator memoize = GuavaMemoize.doubleBinaryOperator(operator);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final DoubleConsumer memoize = GuavaMemoize.doubleConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";

        // when
        final DoubleFunction<String> memoize = GuavaMemoize.doubleFunction(function);

        // then
        Assert.assertNotNull("Memoized DoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = a -> true;

        // when
        final DoublePredicate memoize = GuavaMemoize.doublePredicate(predicate);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final DoubleSupplier memoize = GuavaMemoize.doubleSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;

        // when
        final DoubleToIntFunction memoize = GuavaMemoize.doubleToIntFunction(function);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123;

        // when
        final DoubleToLongFunction memoize = GuavaMemoize.doubleToLongFunction(function);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator function = a -> 123.456D;

        // when
        final DoubleUnaryOperator memoize = GuavaMemoize.doubleUnaryOperator(function);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = GuavaMemoize.function(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator function = (a, b) -> 123;

        // when
        final IntBinaryOperator memoize = GuavaMemoize.intBinaryOperator(function);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final IntConsumer memoize = GuavaMemoize.intConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntFunction() {
        // given
        final IntFunction<String> function = a -> "test";

        // when
        final IntFunction<String> memoize = GuavaMemoize.intFunction(function);

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = a -> true;

        // when
        final IntPredicate memoize = GuavaMemoize.intPredicate(predicate);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final IntSupplier memoize = GuavaMemoize.intSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;

        // when
        final IntToDoubleFunction memoize = GuavaMemoize.intToDoubleFunction(function);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction function = a -> 123;

        // when
        final IntToLongFunction memoize = GuavaMemoize.intToLongFunction(function);

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
        final IntUnaryOperator memoize = GuavaMemoize.intUnaryOperator(function);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator operator = (a, b) -> 123;

        // when
        final LongBinaryOperator memoize = GuavaMemoize.longBinaryOperator(operator);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final LongConsumer memoize = GuavaMemoize.longConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongFunction() {
        // given
        final LongFunction<String> function = a -> "test";

        // when
        final LongFunction<String> memoize = GuavaMemoize.longFunction(function);

        // then
        Assert.assertNotNull("Memoized LongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = a -> true;

        // when
        final LongPredicate memoize = GuavaMemoize.longPredicate(predicate);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123;

        // when
        final LongSupplier memoize = GuavaMemoize.longSupplier(supplier);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction function = a -> 123D;

        // when
        final LongToDoubleFunction memoize = GuavaMemoize.longToDoubleFunction(function);

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
        final LongToIntFunction memoize = GuavaMemoize.longToIntFunction(function);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator function = a -> 123;

        // when
        final LongUnaryOperator memoize = GuavaMemoize.longUnaryOperator(function);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<String> consumer = (a, b) -> System.out.println(a + b);

        // when
        final ObjDoubleConsumer<String> memoize = GuavaMemoize.objDoubleConsumer(consumer);

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = a -> true;

        // when
        final Predicate<String> memoize = GuavaMemoize.predicate(predicate);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = GuavaMemoize.supplier(supplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (a, b) -> 123;

        // when
        final ToDoubleBiFunction<String, String> memoize = GuavaMemoize.toDoubleBiFunction(function);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = a -> 123;

        // when
        final ToDoubleFunction<String> memoize = GuavaMemoize.toDoubleFunction(function);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<String, String> function = (a, b) -> 123;

        // when
        final ToIntBiFunction<String, String> memoize = GuavaMemoize.toIntBiFunction(function);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = a -> 123;

        // when
        final ToIntFunction<String> memoize = GuavaMemoize.toIntFunction(function);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<String, String> function = (a, b) -> 123;

        // when
        final ToLongBiFunction<String, String> memoize = GuavaMemoize.toLongBiFunction(function);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = a -> 123;

        // when
        final ToLongFunction<String> memoize = GuavaMemoize.toLongFunction(function);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    }

}
