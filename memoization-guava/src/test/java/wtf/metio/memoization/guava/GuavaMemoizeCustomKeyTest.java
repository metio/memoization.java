/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wtf.metio.memoization.core.*;

import java.util.function.*;

class GuavaMemoizeCustomKeyTest {

    @Test
    void shouldMemoizeBiConsumerWithKeyBiFunction() {
        // given
        final BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.biConsumer(biConsumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunctionWithKeyBiFunction() {
        // given
        final BiFunction<String, String, String> biFunction = (a, b) -> "test";
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.biFunction(biFunction, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicateWithKeyBiFunction() {
        // given
        final BiPredicate<String, String> biPredicate = (a, b) -> true;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.biPredicate(biPredicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplierWithKeySupplier() {
        // given
        final BooleanSupplier supplier = () -> true;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final var memoize = GuavaMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumerWithKeyFunction() {
        // given
        final Consumer<String> consumer = System.out::println;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.consumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator operator = (a, b) -> 123.456D;
        final DoubleBinaryFunction<String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.doubleBinaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumerWithKeyFunction() {
        // given
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.doubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunctionWithKeyFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.doubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicateWithKeyFunction() {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplierWithKeySupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final var memoize = GuavaMemoize.doubleSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoize = GuavaMemoize.doubleToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction function = a -> 123;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoize = GuavaMemoize.doubleToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator function = a -> 123.456D;
        final DoubleFunction<Double> keyFunction = Double::valueOf;

        // when
        final var memoize = GuavaMemoize.doubleUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.function(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator function = (a, b) -> 123;
        final IntBinaryFunction<String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.intBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumerWithKeyFunction() {
        // given
        final IntConsumer consumer = System.out::println;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.intConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunctionWithKeyFunction() {
        // given
        final IntFunction<String> function = a -> "test";
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.intFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicateWithKeyFunction() {
        // given
        final IntPredicate predicate = a -> true;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.intPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplierWithKeySupplier() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final var memoize = GuavaMemoize.intSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoize = GuavaMemoize.intToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction function = a -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoize = GuavaMemoize.intToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperatorWithKeyFunction() {
        // given
        final IntUnaryOperator function = a -> 123;
        final IntFunction<Integer> keyFunction = Integer::valueOf;

        // when
        final var memoize = GuavaMemoize.intUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator operator = (a, b) -> 123;
        final LongBinaryFunction<String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.longBinaryOperator(operator, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumerWithKeyFunction() {
        // given
        final LongConsumer consumer = System.out::println;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.longConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunctionWithKeyFunction() {
        // given
        final LongFunction<String> function = a -> "test";
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.longFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicateWithLambda() {
        // given
        final LongPredicate predicate = a -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.longPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplierWithKeySupplier() {
        // given
        final LongSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = () -> "key";

        // when
        final var memoize = GuavaMemoize.longSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction function = a -> 123D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.longToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunctionWithKeyFunction() {
        // given
        final LongToIntFunction function = a -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.longToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator function = a -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.longUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<String> consumer = (a, b) -> System.out.println(a + b);
        final ObjDoubleFunction<String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.objDoubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumerWithKeyFunction() {
        // given
        final ObjIntConsumer<String> consumer = (a, b) -> System.out.println(a + b);
        final ObjIntFunction<String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.objIntConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumerWithKeyFunction() {
        // given
        final ObjLongConsumer<String> consumer = (a, b) -> System.out.println(a + b);
        final ObjLongFunction<String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.objLongConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.predicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeSupplierWithKeySupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";

        // when
        final var memoize = GuavaMemoize.supplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunctionWithKeyFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (a, b) -> 123;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.toDoubleBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunctionWithKeyFunction() {
        // given
        final ToDoubleFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.toDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunctionWithKeyFunction() {
        // given
        final ToIntBiFunction<String, String> function = (a, b) -> 123;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.toIntBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunctionWithKeyFunction() {
        // given
        final ToIntFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.toIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunctionWithKeyFunction() {
        // given
        final ToLongBiFunction<String, String> function = (a, b) -> 123;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";

        // when
        final var memoize = GuavaMemoize.toLongBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunctionWithKeyFunction() {
        // given
        final ToLongFunction<String> function = a -> 123;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final var memoize = GuavaMemoize.toLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

}
