/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.caffeine;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.defaultKeySupplier;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;
import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction;

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
import de.xn__ho_hia.memoization.shared.MemoizationDefaults;
import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.memoization.shared.ObjIntFunction;
import de.xn__ho_hia.memoization.shared.ObjLongFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineMemoizeCustomKeyTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Function<String, String> memoize = CaffeineMemoize.function(function, keyFunction);

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
        final IntFunction<String> memoize = CaffeineMemoize.intFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToIntFunction memoize = CaffeineMemoize.doubleToIntFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToLongFunction memoize = CaffeineMemoize.doubleToLongFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleUnaryOperator memoize = CaffeineMemoize.doubleUnaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator function = (first, second) -> first + second;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final DoubleBinaryOperator memoize = CaffeineMemoize.doubleBinaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator function = (first, second) -> first + second;
        final IntBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final IntBinaryOperator memoize = CaffeineMemoize.intBinaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToDoubleFunction memoize = CaffeineMemoize.intToDoubleFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction function = a -> 123L;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToLongFunction memoize = CaffeineMemoize.intToLongFunction(function, keyFunction);

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
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntUnaryOperator memoize = CaffeineMemoize.intUnaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator function = (first, second) -> first + second;
        final LongBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final LongBinaryOperator memoize = CaffeineMemoize.longBinaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToDoubleFunction memoize = CaffeineMemoize.longToDoubleFunction(function, keyFunction);

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
        final LongToIntFunction memoize = CaffeineMemoize.longToIntFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator function = a -> 123L;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongUnaryOperator memoize = CaffeineMemoize.longUnaryOperator(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplierWithKeyFunction() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = defaultKeySupplier();

        // when
        final Supplier<String> memoize = CaffeineMemoize.supplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplierWithKeyFunction() {
        // given
        final BooleanSupplier supplier = () -> false;
        final Supplier<String> keySupplier = defaultKeySupplier();

        // when
        final BooleanSupplier memoize = CaffeineMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplierWithKeyFunction() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = defaultKeySupplier();

        // when
        final DoubleSupplier memoize = CaffeineMemoize.doubleSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplierWithKeyFunction() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = defaultKeySupplier();

        // when
        final IntSupplier memoize = CaffeineMemoize.intSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplierWithKeyFunction() {
        // given
        final LongSupplier supplier = () -> 123L;
        final Supplier<String> keySupplier = defaultKeySupplier();

        // when
        final LongSupplier memoize = CaffeineMemoize.longSupplier(supplier, keySupplier);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunctionWithKeyFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = (first, second) -> first.doubleValue()
                + second.doubleValue();
        final BiFunction<Double, Double, String> keyFunction = hashCodeKeyFunction();

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoize.toDoubleBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunctionWithKeyFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = (first, second) -> first.intValue()
                + second.intValue();
        final BiFunction<Integer, Integer, String> keyFunction = hashCodeKeyFunction();

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoize.toIntBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunctionWithKeyFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = (first, second) -> first.longValue()
                + second.longValue();
        final BiFunction<Long, Long, String> keyFunction = hashCodeKeyFunction();

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoize.toLongBiFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
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
        final ToDoubleFunction<String> memoize = CaffeineMemoize.toDoubleFunction(function, keyFunction);

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
        final ToIntFunction<String> memoize = CaffeineMemoize.toIntFunction(function, keyFunction);

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
        final ToLongFunction<String> memoize = CaffeineMemoize.toLongFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
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
        final Predicate<String> memoize = CaffeineMemoize.predicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
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
        final LongPredicate memoize = CaffeineMemoize.longPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
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
        final IntPredicate memoize = CaffeineMemoize.intPredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
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
        final DoublePredicate memoize = CaffeineMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
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
        final Consumer<String> memoize = CaffeineMemoize.consumer(consumer, keyFunction);

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
        final DoubleConsumer memoize = CaffeineMemoize.doubleConsumer(consumer, keyFunction);

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
        final IntConsumer memoize = CaffeineMemoize.intConsumer(consumer, keyFunction);

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
        final LongConsumer memoize = CaffeineMemoize.longConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicateWithKeyFunction() {
        // given
        final BiPredicate<Long, Long> function = (first, second) -> false;
        final BiFunction<Long, Long, String> keyFunction = hashCodeKeyFunction();

        // when
        final BiPredicate<Long, Long> memoize = CaffeineMemoize.biPredicate(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunctionWithKeyFunction() {
        // given
        final BiFunction<Long, Long, String> function = (first, second) -> "test";
        final BiFunction<Long, Long, String> keyFunction = hashCodeKeyFunction();

        // when
        final BiFunction<Long, Long, String> memoize = CaffeineMemoize.biFunction(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumerWithKeyFunction() {
        // given
        final BiConsumer<Long, Long> function = (first, second) -> System.out.println(first + " " + second);
        final BiFunction<Long, Long, String> keyFunction = hashCodeKeyFunction();

        // when
        final BiConsumer<Long, Long> memoize = CaffeineMemoize.biConsumer(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<Long> consumer = (first, second) -> System.out.println(first.toString() + second);
        final ObjDoubleFunction<Long, String> keyFunction = objDoubleConsumerHashCodeKeyFunction();

        // when
        final ObjDoubleConsumer<Long> memoize = CaffeineMemoize.objDoubleConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjIntConsumerWithKeyFunction() {
        // given
        final ObjIntConsumer<Long> consumer = (first, second) -> System.out.println(first.toString() + second);
        final ObjIntFunction<Long, String> keyFunction = MemoizationDefaults.objIntConsumerHashCodeKeyFunction();

        // when
        final ObjIntConsumer<Long> memoize = CaffeineMemoize.objIntConsumer(consumer, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjIntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjLongConsumerWithKeyFunction() {
        // given
        final ObjLongConsumer<Long> function = (first, second) -> System.out.println(first.toString() + second);
        final ObjLongFunction<Long, String> keyFunction = MemoizationDefaults.objLongConsumerHashCodeKeyFunction();

        // when
        final ObjLongConsumer<Long> memoize = CaffeineMemoize.objLongConsumer(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ObjLongConsumer is NULL", memoize);
    }

}
