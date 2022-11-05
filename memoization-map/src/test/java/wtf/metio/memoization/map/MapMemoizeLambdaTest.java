/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

class MapMemoizeLambdaTest {

    @Test
    void shouldMemoizeSupplierWithLambda() {
        // given

        // when
        final Supplier<String> memoize = MapMemoize.supplier(() -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplierWithLambda() {
        // given

        // when
        final BooleanSupplier memoize = MapMemoize.booleanSupplier(() -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplierWithLambda() {
        // given

        // when
        final DoubleSupplier memoize = MapMemoize.doubleSupplier(() -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplierWithLambda() {
        // given

        // when
        final IntSupplier memoize = MapMemoize.intSupplier(() -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeFunctionWithLambda() {
        // given

        // when
        final Function<String, String> memoize = MapMemoize.function(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunctionWithLambda() {
        // given

        // when
        final BiFunction<String, String, String> memoize = MapMemoize.biFunction((a, b) -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumerWithLambda() {
        // given

        // when
        final Consumer<String> memoize = MapMemoize.consumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiConsumerWithLambda() {
        // given

        // when
        final BiConsumer<String, String> memoize = MapMemoize
                .biConsumer((first, second) -> System.out.println(first + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumerWithLambda() {
        // given

        // when
        final ObjDoubleConsumer<String> memoize = MapMemoize
                .objDoubleConsumer((first, second) -> System.out.println(first + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumerWithLambda() {
        // given

        // when
        final DoubleConsumer memoize = MapMemoize.doubleConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldIntMemoizeConsumerWithLambda() {
        // given

        // when
        final IntConsumer memoize = MapMemoize.intConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumerWithLambda() {
        // given

        // when
        final LongConsumer memoize = MapMemoize.longConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunctionWithLambda() {
        // given

        // when
        final IntFunction<String> memoize = MapMemoize.intFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunctionWithLambda() {
        // given

        // when
        final DoubleFunction<String> memoize = MapMemoize.doubleFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunctionWithLambda() {
        // given

        // when
        final LongFunction<String> memoize = MapMemoize.longFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumerWithLambda() {
        // given

        // when
        final ObjIntConsumer<String> memoize = MapMemoize
                .objIntConsumer((first, second) -> System.out.println(first + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumerWithLambda() {
        // given

        // when
        final ObjLongConsumer<String> memoize = MapMemoize
                .objLongConsumer((first, second) -> System.out.println(first + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicateWithLambda() {
        // given

        // when
        final Predicate<String> memoize = MapMemoize.predicate(input -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicateWithLambda() {
        // given

        // when
        final BiPredicate<String, String> memoize = MapMemoize.biPredicate((first, second) -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicateWithLambda() {
        // given

        // when
        final DoublePredicate memoize = MapMemoize.doublePredicate(input -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicateWithLambda() {
        // given

        // when
        final IntPredicate memoize = MapMemoize.intPredicate(input -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicateWithLambda() {
        // given

        // when
        final LongPredicate memoize = MapMemoize.longPredicate(input -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperatorWithLambda() {
        // given

        // when
        final DoubleBinaryOperator memoize = MapMemoize.doubleBinaryOperator(Double::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperatorWithLambda() {
        // given

        // when
        final IntBinaryOperator memoize = MapMemoize.intBinaryOperator(Integer::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperatorWithLambda() {
        // given

        // when
        final LongBinaryOperator memoize = MapMemoize.longBinaryOperator(Long::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperatorWithLambda() {
        // given

        // when
        final DoubleUnaryOperator memoize = MapMemoize.doubleUnaryOperator(input -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperatorWithLambda() {
        // given

        // when
        final IntUnaryOperator memoize = MapMemoize.intUnaryOperator(input -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperatorWithLambda() {
        // given

        // when
        final LongUnaryOperator memoize = MapMemoize.longUnaryOperator(input -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunctionWithLambda() {
        // given

        // when
        final DoubleToIntFunction memoize = MapMemoize.doubleToIntFunction(input -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunctionWithLambda() {
        // given

        // when
        final DoubleToLongFunction memoize = MapMemoize.doubleToLongFunction(input -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunctionWithLambda() {
        // given

        // when
        final IntToDoubleFunction memoize = MapMemoize.intToDoubleFunction(input -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunctionWithLambda() {
        // given

        // when
        final IntToLongFunction memoize = MapMemoize.intToLongFunction(input -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunctionWithLambda() {
        // given

        // when
        final LongToDoubleFunction memoize = MapMemoize.longToDoubleFunction(input -> 123D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunctionWithLambda() {
        // given

        // when
        final LongToIntFunction memoize = MapMemoize.longToIntFunction(input -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunctionWithLambda() {
        // given

        // when
        final ToDoubleFunction<String> memoize = MapMemoize.toDoubleFunction(Double::parseDouble);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunctionWithLambda() {
        // given

        // when
        final ToIntFunction<String> memoize = MapMemoize.toIntFunction(Integer::parseInt);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunctionWithLambda() {
        // given

        // when
        final ToLongFunction<String> memoize = MapMemoize.toLongFunction(Long::parseLong);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunctionWithLambda() {
        // given

        // when
        final ToDoubleBiFunction<String, String> memoize = MapMemoize
                .toDoubleBiFunction((first, second) -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunctionWithLambda() {
        // given

        // when
        final ToIntBiFunction<String, String> memoize = MapMemoize.toIntBiFunction((first, second) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunctionWithLambda() {
        // given

        // when
        final ToLongBiFunction<String, String> memoize = MapMemoize.toLongBiFunction((first, second) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

}
