/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

class CaffeineMemoizeLambdaTest {

    @Test
    void shouldMemoizeFunctionWithLambda() {
        // given

        // when
        final Function<String, String> memoize = CaffeineMemoize.function(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunctionWithLambda() {
        // given

        // when
        final IntFunction<String> memoize = CaffeineMemoize.intFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunctionWithLambda() {
        // given

        // when
        final LongFunction<String> memoize = CaffeineMemoize.longFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunctionWithLambda() {
        // given

        // when
        final DoubleFunction<String> memoize = CaffeineMemoize.doubleFunction(a -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunctionWithLambda() {
        // given

        // when
        final DoubleToIntFunction memoize = CaffeineMemoize.doubleToIntFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunctionWithLambda() {
        // given

        // when
        final DoubleToLongFunction memoize = CaffeineMemoize.doubleToLongFunction(a -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperatorWithLambda() {
        // given

        // when
        final DoubleUnaryOperator memoize = CaffeineMemoize.doubleUnaryOperator(a -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperatorWithLambda() {
        // given

        // when
        final DoubleBinaryOperator memoize = CaffeineMemoize.doubleBinaryOperator(Double::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperatorWithLambda() {
        // given

        // when
        final IntBinaryOperator memoize = CaffeineMemoize.intBinaryOperator(Integer::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunctionWithLambda() {
        // given

        // when
        final IntToDoubleFunction memoize = CaffeineMemoize.intToDoubleFunction(a -> 123D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunctionWithLambda() {
        // given

        // when
        final IntToLongFunction memoize = CaffeineMemoize.intToLongFunction(a -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperatorWithLambda() {
        // given

        // when
        final IntUnaryOperator memoize = CaffeineMemoize.intUnaryOperator(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperatorWithLambda() {
        // given

        // when
        final LongBinaryOperator memoize = CaffeineMemoize.longBinaryOperator(Long::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunctionWithLambda() {
        // given

        // when
        final LongToDoubleFunction memoize = CaffeineMemoize.longToDoubleFunction(a -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunctionWithLambda() {
        // given

        // when
        final LongToIntFunction memoize = CaffeineMemoize.longToIntFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperatorWithLambda() {
        // given

        // when
        final LongUnaryOperator memoize = CaffeineMemoize.longUnaryOperator(a -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeSupplierWithLambda() {
        // given

        // when
        final Supplier<String> memoize = CaffeineMemoize.supplier(() -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplierWithLambda() {
        // given

        // when
        final BooleanSupplier memoize = CaffeineMemoize.booleanSupplier(() -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplierWithLambda() {
        // given

        // when
        final DoubleSupplier memoize = CaffeineMemoize.doubleSupplier(() -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplierWithLambda() {
        // given

        // when
        final IntSupplier memoize = CaffeineMemoize.intSupplier(() -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplierWithLambda() {
        // given

        // when
        final LongSupplier memoize = CaffeineMemoize.longSupplier(() -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunctionWithLambda() {
        // given

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoize.toDoubleBiFunction(Double::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunctionWithLambda() {
        // given

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoize.toIntBiFunction(Integer::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunctionWithLambda() {
        // given

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoize.toLongBiFunction(Long::sum);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunctionWithLambda() {
        // given

        // when
        final ToDoubleFunction<String> memoize = CaffeineMemoize.toDoubleFunction(a -> 123.456D);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunctionWithLambda() {
        // given

        // when
        final ToIntFunction<String> memoize = CaffeineMemoize.toIntFunction(a -> 123);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunctionWithLambda() {
        // given

        // when
        final ToLongFunction<String> memoize = CaffeineMemoize.toLongFunction(a -> 123L);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicateWithLambda() {
        // given

        // when
        final Predicate<String> memoize = CaffeineMemoize.predicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicateWithLambda() {
        // given

        // when
        final LongPredicate memoize = CaffeineMemoize.longPredicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicateWithLambda() {
        // given

        // when
        final IntPredicate memoize = CaffeineMemoize.intPredicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicateWithLambda() {
        // given

        // when
        final DoublePredicate memoize = CaffeineMemoize.doublePredicate(a -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumerWithLambda() {
        // given

        // when
        final Consumer<String> memoize = CaffeineMemoize.consumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumerWithLambda() {
        // given

        // when
        final DoubleConsumer memoize = CaffeineMemoize.doubleConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumerWithLambda() {
        // given

        // when
        final IntConsumer memoize = CaffeineMemoize.intConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumerWithLambda() {
        // given

        // when
        final LongConsumer memoize = CaffeineMemoize.longConsumer(System.out::println);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicateWithLambda() {
        // given

        // when
        final BiPredicate<Long, Long> memoize = CaffeineMemoize.biPredicate((first, second) -> true);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunctionWithLambda() {
        // given

        // when
        final BiFunction<Long, Long, String> memoize = CaffeineMemoize.biFunction((first, second) -> "test");

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiConsumerWithLambda() {
        // given

        // when
        final BiConsumer<Long, Long> memoize = CaffeineMemoize
                .biConsumer((first, second) -> System.out.println(first + " " + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumerWithLambda() {
        // given

        // when
        final ObjDoubleConsumer<Long> memoize = CaffeineMemoize
                .objDoubleConsumer((first, second) -> System.out.println(first + " " + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumerWithLambda() {
        // given

        // when
        final ObjIntConsumer<Long> memoize = CaffeineMemoize
                .objIntConsumer((first, second) -> System.out.println(first + " " + second));

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumerWithLambda() {
        // given

        // when
        final ObjLongConsumer<Long> memoize = CaffeineMemoize
                .objLongConsumer((first, second) -> System.out.println(first + " " + second));

        // then
        Assertions.assertNotNull(memoize);
    }

}
