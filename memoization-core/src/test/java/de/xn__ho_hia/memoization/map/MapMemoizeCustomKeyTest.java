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

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.memoization.shared.IntBinaryFunction;
import de.xn__ho_hia.memoization.shared.LongBinaryFunction;
import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.memoization.shared.ObjIntFunction;
import de.xn__ho_hia.memoization.shared.ObjLongFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class MapMemoizeCustomKeyTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplierWithKeySupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";

        // when
        final Supplier<String> memoize = MapMemoize.supplier(supplier, keySupplier);

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
        final BooleanSupplier memoize = MapMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplierWithKeySupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final DoubleSupplier memoize = MapMemoize.doubleSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplierWithKeySupplier() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final IntSupplier memoize = MapMemoize.intSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplierWithKeySupplier() {
        // given
        final LongSupplier supplier = () -> 123L;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final LongSupplier memoize = MapMemoize.longSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Function<String, String> memoize = MapMemoize.function(function, keyFunction);

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
        final IntFunction<String> memoize = MapMemoize.intFunction(function, keyFunction);

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
        final LongFunction<String> memoize = MapMemoize.longFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunctionWithKeyBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";
        final BiFunction<String, String, String> keyfunction = (a, b) -> "key";

        // when
        final BiFunction<String, String, String> memoize = MapMemoize.biFunction(bifunction, keyfunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumerWithKeyFunction() {
        // given
        final Consumer<String> consumer = System.out::println;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Consumer<String> memoize = MapMemoize.consumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumerWithKeyBiFunction() {
        // given
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiConsumer<String, String> memoize = MapMemoize.biConsumer(biConsumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> "key";

        // when
        final ObjDoubleConsumer<String> memoize = MapMemoize.objDoubleConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
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
        final DoubleConsumer memoize = MapMemoize.doubleConsumer(consumer, keyFunction);

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
        final IntConsumer memoize = MapMemoize.intConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongConsumerWithKeyFunction() {
        // given
        final LongConsumer consumer = System.out::println;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongConsumer memoize = MapMemoize.longConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjIntConsumerWithKeyFunction() {
        // given
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);
        final ObjIntFunction<String, String> keyFunction = (first, second) -> "key";

        // when
        final ObjIntConsumer<String> memoize = MapMemoize.objIntConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjIntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjLongConsumerWithKeyFunction() {
        // given
        final ObjLongConsumer<String> consumer = (first, second) -> System.out.println(first + second);
        final ObjLongFunction<String, String> keyFunction = (first, second) -> "key";

        // when
        final ObjLongConsumer<String> memoize = MapMemoize.objLongConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjLongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = input -> true;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Predicate<String> memoize = MapMemoize.predicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicateWithBiFunction() {
        // given
        final BiPredicate<String, String> predicate = (first, second) -> true;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiPredicate<String, String> memoize = MapMemoize.biPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicateWithKeyFunction() {
        // given
        final DoublePredicate predicate = input -> true;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoublePredicate memoize = MapMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntPredicateWithKeyFunction() {
        // given
        final IntPredicate predicate = input -> true;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntPredicate memoize = MapMemoize.intPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicateWithKeyFunction() {
        // given
        final LongPredicate predicate = input -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongPredicate memoize = MapMemoize.longPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator operator = (first, second) -> first + second;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final DoubleBinaryOperator memoize = MapMemoize.doubleBinaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator operator = (first, second) -> first + second;
        final IntBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final IntBinaryOperator memoize = MapMemoize.intBinaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator operator = (first, second) -> first + second;
        final LongBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final LongBinaryOperator memoize = MapMemoize.longBinaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator operator = input -> 123.456D;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleUnaryOperator memoize = MapMemoize.doubleUnaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntUnaryOperatorWithKeyFunction() {
        // given
        final IntUnaryOperator operator = input -> 123;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntUnaryOperator memoize = MapMemoize.intUnaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator operator = input -> 123L;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongUnaryOperator memoize = MapMemoize.longUnaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction operator = input -> 123;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToIntFunction memoize = MapMemoize.doubleToIntFunction(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction operator = input -> 123L;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToLongFunction memoize = MapMemoize.doubleToLongFunction(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction operator = input -> 123.456D;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToDoubleFunction memoize = MapMemoize.intToDoubleFunction(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction operator = input -> 123L;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToLongFunction memoize = MapMemoize.intToLongFunction(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction operator = input -> 123D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToDoubleFunction memoize = MapMemoize.longToDoubleFunction(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToIntFunctionWithKeyFunction() {
        // given
        final LongToIntFunction operator = input -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToIntFunction memoize = MapMemoize.longToIntFunction(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunctionWithKeyFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToDoubleFunction<String> memoize = MapMemoize.toDoubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunctionWithKeyFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToIntFunction<String> memoize = MapMemoize.toIntFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunctionWithKeyFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToLongFunction<String> memoize = MapMemoize.toLongFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunctionWithKeyBiFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (first, second) -> 123.456D;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final ToDoubleBiFunction<String, String> memoize = MapMemoize.toDoubleBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunctionWithKeyBiFunction() {
        // given
        final ToIntBiFunction<String, String> function = (first, second) -> 123;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final ToIntBiFunction<String, String> memoize = MapMemoize.toIntBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunctionWithKeyBiFunction() {
        // given
        final ToLongBiFunction<String, String> function = (first, second) -> 123;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final ToLongBiFunction<String, String> memoize = MapMemoize.toLongBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

}
