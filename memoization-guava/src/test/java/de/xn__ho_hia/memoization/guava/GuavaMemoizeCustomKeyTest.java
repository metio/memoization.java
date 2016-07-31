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

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.memoization.shared.IntBinaryFunction;
import de.xn__ho_hia.memoization.shared.LongBinaryFunction;
import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaMemoizeCustomKeyTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumerWithKeyBiFunction() {
        // given
        final BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final BiConsumer<String, String> memoize = GuavaMemoize.biConsumer(biConsumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunctionWithKeyBiFunction() {
        // given
        final BiFunction<String, String, String> biFunction = (a, b) -> "test";
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final BiFunction<String, String, String> memoize = GuavaMemoize.biFunction(biFunction, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicateWithKeyBiFunction() {
        // given
        final BiPredicate<String, String> biPredicate = (a, b) -> true;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final BiPredicate<String, String> memoize = GuavaMemoize.biPredicate(biPredicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
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
        final BooleanSupplier memoize = GuavaMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
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
        final Consumer<String> memoize = GuavaMemoize.consumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator operator = (a, b) -> 123.456D;
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";

        // when
        final DoubleBinaryOperator memoize = GuavaMemoize.doubleBinaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
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
        final DoubleConsumer memoize = GuavaMemoize.doubleConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleFunctionWithKeyFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleFunction<String> memoize = GuavaMemoize.doubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleFunction is NULL", memoize);
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
        final DoublePredicate memoize = GuavaMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
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
        final DoubleSupplier memoize = GuavaMemoize.doubleSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final DoubleToIntFunction memoize = GuavaMemoize.doubleToIntFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction function = a -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final DoubleToLongFunction memoize = GuavaMemoize.doubleToLongFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator function = a -> 123.456D;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final DoubleUnaryOperator memoize = GuavaMemoize.doubleUnaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
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
        final Function<String, String> memoize = GuavaMemoize.function(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator function = (a, b) -> 123;
        final IntBinaryFunction<String> keyFunction = (a, b) -> "key";

        // when
        final IntBinaryOperator memoize = GuavaMemoize.intBinaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
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
        final IntConsumer memoize = GuavaMemoize.intConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
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
        final IntFunction<String> memoize = GuavaMemoize.intFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
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
        final IntPredicate memoize = GuavaMemoize.intPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
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
        final IntSupplier memoize = GuavaMemoize.intSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final IntToDoubleFunction memoize = GuavaMemoize.intToDoubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction function = a -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final IntToLongFunction memoize = GuavaMemoize.intToLongFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntUnaryOperatorWithKeyFunction() {
        // given
        final IntUnaryOperator function = a -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final IntUnaryOperator memoize = GuavaMemoize.intUnaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator operator = (a, b) -> 123;
        final LongBinaryFunction<String> keyFunction = (a, b) -> "key";

        // when
        final LongBinaryOperator memoize = GuavaMemoize.longBinaryOperator(operator, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
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
        final LongConsumer memoize = GuavaMemoize.longConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
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
        final LongFunction<String> memoize = GuavaMemoize.longFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicateWithLambda() {
        // given
        final LongPredicate predicate = a -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongPredicate memoize = GuavaMemoize.longPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplierWithKeySupplier() {
        // given
        final LongSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final LongSupplier memoize = GuavaMemoize.longSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction function = a -> 123D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToDoubleFunction memoize = GuavaMemoize.longToDoubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToIntFunctionWithKeyFunction() {
        // given
        final LongToIntFunction function = a -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToIntFunction memoize = GuavaMemoize.longToIntFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator function = a -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongUnaryOperator memoize = GuavaMemoize.longUnaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<String> consumer = (a, b) -> System.out.println(a + b);
        final ObjDoubleFunction<String> keyFunction = (a, b) -> "key";

        // when
        final ObjDoubleConsumer<String> memoize = GuavaMemoize.objDoubleConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Predicate<String> memoize = GuavaMemoize.predicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplierWithKeySupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";

        // when
        final Supplier<String> memoize = GuavaMemoize.supplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunctionWithKeyFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (a, b) -> 123;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final ToDoubleBiFunction<String, String> memoize = GuavaMemoize.toDoubleBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunctionWithKeyFunction() {
        // given
        final ToDoubleFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToDoubleFunction<String> memoize = GuavaMemoize.toDoubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunctionWithKeyFunction() {
        // given
        final ToIntBiFunction<String, String> function = (a, b) -> 123;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final ToIntBiFunction<String, String> memoize = GuavaMemoize.toIntBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunctionWithKeyFunction() {
        // given
        final ToIntFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToIntFunction<String> memoize = GuavaMemoize.toIntFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunctionWithKeyFunction() {
        // given
        final ToLongBiFunction<String, String> function = (a, b) -> 123;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final ToLongBiFunction<String, String> memoize = GuavaMemoize.toLongBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunctionWithKeyFunction() {
        // given
        final ToLongFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToLongFunction<String> memoize = GuavaMemoize.toLongFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    }

}
