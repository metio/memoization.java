/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wtf.metio.memoization.core.*;

import java.util.function.*;

class MapMemoizeCustomKeyTest {

    @Test
    void shouldMemoizeSupplierWithKeySupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";

        // when
        final Supplier<String> memoize = MapMemoize.supplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplierWithKeySupplier() {
        // given
        final BooleanSupplier supplier = () -> true;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final BooleanSupplier memoize = MapMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplierWithKeySupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final DoubleSupplier memoize = MapMemoize.doubleSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplierWithKeySupplier() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final IntSupplier memoize = MapMemoize.intSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplierWithKeySupplier() {
        // given
        final LongSupplier supplier = () -> 123L;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final LongSupplier memoize = MapMemoize.longSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Function<String, String> memoize = MapMemoize.function(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunctionWithKeyFunction() {
        // given
        final IntFunction<String> function = a -> "test";
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntFunction<String> memoize = MapMemoize.intFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunctionWithKeyFunction() {
        // given
        final LongFunction<String> function = a -> "test";
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongFunction<String> memoize = MapMemoize.longFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleFunction<String> memoize = MapMemoize.doubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunctionWithKeyBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";
        final BiFunction<String, String, String> keyfunction = (a, b) -> "key";

        // when
        final BiFunction<String, String, String> memoize = MapMemoize.biFunction(bifunction, keyfunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumerWithKeyFunction() {
        // given
        final Consumer<String> consumer = System.out::println;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Consumer<String> memoize = MapMemoize.consumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiConsumerWithKeyBiFunction() {
        // given
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiConsumer<String, String> memoize = MapMemoize.biConsumer(biConsumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);
        final ObjDoubleFunction<String, String> keyFunction = (first, second) -> "key";

        // when
        final ObjDoubleConsumer<String> memoize = MapMemoize.objDoubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumerWithKeyFunction() {
        // given
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleConsumer memoize = MapMemoize.doubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumerWithKeyFunction() {
        // given
        final IntConsumer consumer = System.out::println;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntConsumer memoize = MapMemoize.intConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumerWithKeyFunction() {
        // given
        final LongConsumer consumer = System.out::println;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongConsumer memoize = MapMemoize.longConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumerWithKeyFunction() {
        // given
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);
        final ObjIntFunction<String, String> keyFunction = (first, second) -> "key";

        // when
        final ObjIntConsumer<String> memoize = MapMemoize.objIntConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumerWithKeyFunction() {
        // given
        final ObjLongConsumer<String> consumer = (first, second) -> System.out.println(first + second);
        final ObjLongFunction<String, String> keyFunction = (first, second) -> "key";

        // when
        final ObjLongConsumer<String> memoize = MapMemoize.objLongConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = input -> true;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Predicate<String> memoize = MapMemoize.predicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicateWithBiFunction() {
        // given
        final BiPredicate<String, String> predicate = (first, second) -> true;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final BiPredicate<String, String> memoize = MapMemoize.biPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicateWithKeyFunction() {
        // given
        final DoublePredicate predicate = input -> true;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoublePredicate memoize = MapMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicateWithKeyFunction() {
        // given
        final IntPredicate predicate = input -> true;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntPredicate memoize = MapMemoize.intPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicateWithKeyFunction() {
        // given
        final LongPredicate predicate = input -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongPredicate memoize = MapMemoize.longPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator operator = Double::sum;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final DoubleBinaryOperator memoize = MapMemoize.doubleBinaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator operator = Integer::sum;
        final IntBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final IntBinaryOperator memoize = MapMemoize.intBinaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator operator = Long::sum;
        final LongBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final LongBinaryOperator memoize = MapMemoize.longBinaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator operator = input -> 123.456D;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleUnaryOperator memoize = MapMemoize.doubleUnaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperatorWithKeyFunction() {
        // given
        final IntUnaryOperator operator = input -> 123;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntUnaryOperator memoize = MapMemoize.intUnaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator operator = input -> 123L;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongUnaryOperator memoize = MapMemoize.longUnaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction operator = input -> 123;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToIntFunction memoize = MapMemoize.doubleToIntFunction(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction operator = input -> 123L;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToLongFunction memoize = MapMemoize.doubleToLongFunction(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction operator = input -> 123.456D;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToDoubleFunction memoize = MapMemoize.intToDoubleFunction(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction operator = input -> 123L;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToLongFunction memoize = MapMemoize.intToLongFunction(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction operator = input -> 123D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToDoubleFunction memoize = MapMemoize.longToDoubleFunction(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunctionWithKeyFunction() {
        // given
        final LongToIntFunction operator = input -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToIntFunction memoize = MapMemoize.longToIntFunction(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunctionWithKeyFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToDoubleFunction<String> memoize = MapMemoize.toDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunctionWithKeyFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToIntFunction<String> memoize = MapMemoize.toIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunctionWithKeyFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToLongFunction<String> memoize = MapMemoize.toLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunctionWithKeyBiFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (first, second) -> 123.456D;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final ToDoubleBiFunction<String, String> memoize = MapMemoize.toDoubleBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunctionWithKeyBiFunction() {
        // given
        final ToIntBiFunction<String, String> function = (first, second) -> 123;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final ToIntBiFunction<String, String> memoize = MapMemoize.toIntBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunctionWithKeyBiFunction() {
        // given
        final ToLongBiFunction<String, String> function = (first, second) -> 123;
        final BiFunction<String, String, String> keyFunction = (first, second) -> "key";

        // when
        final ToLongBiFunction<String, String> memoize = MapMemoize.toLongBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

}
