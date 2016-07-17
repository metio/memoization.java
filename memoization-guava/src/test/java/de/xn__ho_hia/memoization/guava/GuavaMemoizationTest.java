/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaMemoizationTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = GuavaMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeBooleanSupplier() {
    // // given
    // final BooleanSupplier supplier = () -> true;
    //
    // // when
    // final BooleanSupplier memoize = GuavaMemoization.memoize(supplier);
    //
    // // then
    // Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoubleSupplier() {
    // // given
    // final DoubleSupplier supplier = () -> 123.456D;
    //
    // // when
    // final DoubleSupplier memoize = GuavaMemoization.memoize(supplier);
    //
    // // then
    // Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeIntSupplier() {
    // // given
    // final IntSupplier supplier = () -> 123;
    //
    // // when
    // final IntSupplier memoize = GuavaMemoization.memoize(supplier);
    //
    // // then
    // Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongSupplier() {
    // // given
    // final LongSupplier supplier = () -> 123L;
    //
    // // when
    // final LongSupplier memoize = GuavaMemoization.memoize(supplier);
    //
    // // then
    // Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    // }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = GuavaMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";

        // when
        final BiFunction<String, String, String> memoize = GuavaMemoization.memoize(bifunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = GuavaMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeBiConsumer() {
    // // given
    // final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);
    //
    // // when
    // final BiConsumer<String, String> memoize = GuavaMemoization.memoize(biConsumer);
    //
    // // then
    // Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeObjDoubleConsumer() {
    // // given
    // final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);
    //
    // // when
    // final ObjDoubleConsumer<String> memoize = GuavaMemoization.memoize(consumer);
    //
    // // then
    // Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoubleConsumer() {
    // // given
    // final DoubleConsumer consumer = System.out::println;
    //
    // // when
    // final DoubleConsumer memoize = GuavaMemoization.memoize(consumer);
    //
    // // then
    // Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldIntMemoizeConsumer() {
    // // given
    // final IntConsumer consumer = System.out::println;
    //
    // // when
    // final IntConsumer memoize = GuavaMemoization.memoize(consumer);
    //
    // // then
    // Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongConsumer() {
    // // given
    // final LongConsumer consumer = System.out::println;
    //
    // // when
    // final LongConsumer memoize = GuavaMemoization.memoize(consumer);
    //
    // // then
    // Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeObjIntConsumer() {
    // // given
    // final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);
    //
    // // when
    // final ObjIntConsumer<String> memoize = GuavaMemoization.memoize(consumer);
    //
    // // then
    // Assert.assertNotNull("Memoized ObjIntConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeObjLongConsumer() {
    // // given
    // final ObjLongConsumer<String> consumer = (first, second) -> System.out.println(first + second);
    //
    // // when
    // final ObjLongConsumer<String> memoize = GuavaMemoization.memoize(consumer);
    //
    // // then
    // Assert.assertNotNull("Memoized ObjLongConsumer is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizePredicate() {
    // // given
    // final Predicate<String> predicate = input -> true;
    //
    // // when
    // final Predicate<String> memoize = GuavaMemoization.memoize(predicate);
    //
    // // then
    // Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeBiPredicate() {
    // // given
    // final BiPredicate<String, String> predicate = (first, second) -> true;
    //
    // // when
    // final BiPredicate<String, String> memoize = GuavaMemoization.memoize(predicate);
    //
    // // then
    // Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoublePredicate() {
    // // given
    // final DoublePredicate predicate = input -> true;
    //
    // // when
    // final DoublePredicate memoize = GuavaMemoization.memoize(predicate);
    //
    // // then
    // Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeIntPredicate() {
    // // given
    // final IntPredicate predicate = input -> true;
    //
    // // when
    // final IntPredicate memoize = GuavaMemoization.memoize(predicate);
    //
    // // then
    // Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongPredicate() {
    // // given
    // final LongPredicate predicate = input -> true;
    //
    // // when
    // final LongPredicate memoize = GuavaMemoization.memoize(predicate);
    //
    // // then
    // Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoubleBinaryOperator() {
    // // given
    // final DoubleBinaryOperator operator = (first, second) -> first + second;
    //
    // // when
    // final DoubleBinaryOperator memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeIntBinaryOperator() {
    // // given
    // final IntBinaryOperator operator = (first, second) -> first + second;
    //
    // // when
    // final IntBinaryOperator memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongBinaryOperator() {
    // // given
    // final LongBinaryOperator operator = (first, second) -> first + second;
    //
    // // when
    // final LongBinaryOperator memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoubleUnaryOperator() {
    // // given
    // final DoubleUnaryOperator operator = input -> 123.456D;
    //
    // // when
    // final DoubleUnaryOperator memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeIntUnaryOperator() {
    // // given
    // final IntUnaryOperator operator = input -> 123;
    //
    // // when
    // final IntUnaryOperator memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongUnaryOperator() {
    // // given
    // final LongUnaryOperator operator = input -> 123L;
    //
    // // when
    // final LongUnaryOperator memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoubleToIntFunction() {
    // // given
    // final DoubleToIntFunction operator = input -> 123;
    //
    // // when
    // final DoubleToIntFunction memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeDoubleToLongFunction() {
    // // given
    // final DoubleToLongFunction operator = input -> 123L;
    //
    // // when
    // final DoubleToLongFunction memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeIntToDoubleFunction() {
    // // given
    // final IntToDoubleFunction operator = input -> 123.456D;
    //
    // // when
    // final IntToDoubleFunction memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeIntToLongFunction() {
    // // given
    // final IntToLongFunction operator = input -> 123L;
    //
    // // when
    // final IntToLongFunction memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongToDoubleFunction() {
    // // given
    // final LongToDoubleFunction operator = input -> 123D;
    //
    // // when
    // final LongToDoubleFunction memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeLongToIntFunction() {
    // // given
    // final LongToIntFunction operator = input -> 123;
    //
    // // when
    // final LongToIntFunction memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeToDoubleFunction() {
    // // given
    // final ToDoubleFunction<String> operator = Double::parseDouble;
    //
    // // when
    // final ToDoubleFunction<String> memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeToIntFunction() {
    // // given
    // final ToIntFunction<String> operator = Integer::parseInt;
    //
    // // when
    // final ToIntFunction<String> memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeToLongFunction() {
    // // given
    // final ToLongFunction<String> operator = Long::parseLong;
    //
    // // when
    // final ToLongFunction<String> memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeToDoubleBiFunction() {
    // // given
    // final ToDoubleBiFunction<String, String> operator = (first, second) -> 123.456D;
    //
    // // when
    // final ToDoubleBiFunction<String, String> memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeToIntBiFunction() {
    // // given
    // final ToIntBiFunction<String, String> operator = (first, second) -> 123;
    //
    // // when
    // final ToIntBiFunction<String, String> memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    // }
    //
    // /**
    // *
    // */
    // @Test
    // public void shouldMemoizeToLongBiFunction() {
    // // given
    // final ToLongBiFunction<String, String> operator = (first, second) -> 123;
    //
    // // when
    // final ToLongBiFunction<String, String> memoize = GuavaMemoization.memoize(operator);
    //
    // // then
    // Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    // }

    /**
     * @throws NoSuchMethodException
     *             Reflection problemt
     * @throws IllegalAccessException
     *             Reflection problemt
     * @throws InvocationTargetException
     *             Reflection problemt
     * @throws InstantiationException
     *             Reflection problemt
     */
    @Test
    public void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // given
        final Constructor<GuavaMemoization> constructor = GuavaMemoization.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assert.assertTrue("Constructor is not private", isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
