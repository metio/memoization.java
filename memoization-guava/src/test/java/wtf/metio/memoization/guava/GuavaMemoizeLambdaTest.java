/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

class GuavaMemoizeLambdaTest {

    @Test
    void shouldMemoizeBiConsumerWithLambda() {
        // given

        // when
        final BiConsumer<String, String> memoize = GuavaMemoize.biConsumer((a, b) -> System.out.println(a + b));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.biFunction((a, b) -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicateWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.biPredicate((a, b) -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplierWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.booleanSupplier(() -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumerWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.consumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperatorWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleBinaryOperator((a, b) -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumerWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicateWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doublePredicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplierWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleSupplier(() -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleToIntFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleToLongFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperatorWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.doubleUnaryOperator(a -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.function(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperatorWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intBinaryOperator((a, b) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumerWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicateWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intPredicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplierWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intSupplier(() -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intToDoubleFunction(a -> 123D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intToLongFunction(a -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperatorWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.intUnaryOperator(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperatorWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longBinaryOperator((a, b) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumerWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicateWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longPredicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplierWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longSupplier(() -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longToDoubleFunction(a -> 123D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longToIntFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperatorWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.longUnaryOperator(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumerWithLambda() {
        // given

        // when
        final ObjDoubleConsumer<String> memoize = GuavaMemoize.objDoubleConsumer((a, b) -> System.out.println(a + b));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumerWithLambda() {
        // given

        // when
        final ObjIntConsumer<String> memoize = GuavaMemoize.objIntConsumer((a, b) -> System.out.println(a + b));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumerWithLambda() {
        // given

        // when
        final ObjLongConsumer<String> memoize = GuavaMemoize.objLongConsumer((a, b) -> System.out.println(a + b));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicateWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.predicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeSupplierWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.supplier(() -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.toDoubleBiFunction((a, b) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.toDoubleFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.toIntBiFunction((a, b) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.toIntFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.toLongBiFunction((a, b) -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunctionWithLambda() {
        // given

        // when
        final var memoize = GuavaMemoize.toLongFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

}
