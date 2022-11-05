/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

class MapMemoizeDefaultsTest {

    @Test
    void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = MapMemoize.supplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final BooleanSupplier memoize = MapMemoize.booleanSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final DoubleSupplier memoize = MapMemoize.doubleSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final IntSupplier memoize = MapMemoize.intSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123L;

        // when
        final LongSupplier memoize = MapMemoize.longSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = MapMemoize.function(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";

        // when
        final BiFunction<String, String, String> memoize = MapMemoize.biFunction(bifunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = MapMemoize.consumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final BiConsumer<String, String> memoize = MapMemoize.biConsumer(biConsumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ObjDoubleConsumer<String> memoize = MapMemoize.objDoubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final DoubleConsumer memoize = MapMemoize.doubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final IntConsumer memoize = MapMemoize.intConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunction() {
        // given
        final IntFunction<String> function = a -> "test";

        // when
        final IntFunction<String> memoize = MapMemoize.intFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunction() {
        // given
        final LongFunction<String> function = a -> "test";

        // when
        final LongFunction<String> memoize = MapMemoize.longFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";

        // when
        final DoubleFunction<String> memoize = MapMemoize.doubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final LongConsumer memoize = MapMemoize.longConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumer() {
        // given
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ObjIntConsumer<String> memoize = MapMemoize.objIntConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumer() {
        // given
        final ObjLongConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ObjLongConsumer<String> memoize = MapMemoize.objLongConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = input -> true;

        // when
        final Predicate<String> memoize = MapMemoize.predicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<String, String> predicate = (first, second) -> true;

        // when
        final BiPredicate<String, String> memoize = MapMemoize.biPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = input -> true;

        // when
        final DoublePredicate memoize = MapMemoize.doublePredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = input -> true;

        // when
        final IntPredicate memoize = MapMemoize.intPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = input -> true;

        // when
        final LongPredicate memoize = MapMemoize.longPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator operator = Double::sum;

        // when
        final DoubleBinaryOperator memoize = MapMemoize.doubleBinaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator operator = Integer::sum;

        // when
        final IntBinaryOperator memoize = MapMemoize.intBinaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator operator = Long::sum;

        // when
        final LongBinaryOperator memoize = MapMemoize.longBinaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator operator = input -> 123.456D;

        // when
        final DoubleUnaryOperator memoize = MapMemoize.doubleUnaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator operator = input -> 123;

        // when
        final IntUnaryOperator memoize = MapMemoize.intUnaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator operator = input -> 123L;

        // when
        final LongUnaryOperator memoize = MapMemoize.longUnaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction operator = input -> 123;

        // when
        final DoubleToIntFunction memoize = MapMemoize.doubleToIntFunction(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction operator = input -> 123L;

        // when
        final DoubleToLongFunction memoize = MapMemoize.doubleToLongFunction(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction operator = input -> 123.456D;

        // when
        final IntToDoubleFunction memoize = MapMemoize.intToDoubleFunction(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction operator = input -> 123L;

        // when
        final IntToLongFunction memoize = MapMemoize.intToLongFunction(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction operator = input -> 123D;

        // when
        final LongToDoubleFunction memoize = MapMemoize.longToDoubleFunction(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction operator = input -> 123;

        // when
        final LongToIntFunction memoize = MapMemoize.longToIntFunction(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;

        // when
        final ToDoubleFunction<String> memoize = MapMemoize.toDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;

        // when
        final ToIntFunction<String> memoize = MapMemoize.toIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;

        // when
        final ToLongFunction<String> memoize = MapMemoize.toLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (first, second) -> 123.456D;

        // when
        final ToDoubleBiFunction<String, String> memoize = MapMemoize.toDoubleBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<String, String> function = (first, second) -> 123;

        // when
        final ToIntBiFunction<String, String> memoize = MapMemoize.toIntBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<String, String> function = (first, second) -> 123;

        // when
        final ToLongBiFunction<String, String> memoize = MapMemoize.toLongBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

}
