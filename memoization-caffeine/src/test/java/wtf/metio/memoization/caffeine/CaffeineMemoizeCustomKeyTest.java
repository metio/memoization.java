/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wtf.metio.memoization.core.*;

import java.util.function.*;

class CaffeineMemoizeCustomKeyTest {

    @Test
    void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Function<String, String> memoize = CaffeineMemoize.function(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunctionWithKeyFunction() {
        // given
        final IntFunction<String> function = a -> "test";
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntFunction<String> memoize = CaffeineMemoize.intFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunctionWithKeyFunction() {
        // given
        final LongFunction<String> function = a -> "test";
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongFunction<String> memoize = CaffeineMemoize.longFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleFunction<String> memoize = CaffeineMemoize.doubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToIntFunction memoize = CaffeineMemoize.doubleToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToLongFunction memoize = CaffeineMemoize.doubleToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleUnaryOperator memoize = CaffeineMemoize.doubleUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator function = Double::sum;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final DoubleBinaryOperator memoize = CaffeineMemoize.doubleBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator function = Integer::sum;
        final IntBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final IntBinaryOperator memoize = CaffeineMemoize.intBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToDoubleFunction memoize = CaffeineMemoize.intToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction function = a -> 123L;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToLongFunction memoize = CaffeineMemoize.intToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperatorWithKeyFunction() {
        // given
        final IntUnaryOperator function = a -> 123;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntUnaryOperator memoize = CaffeineMemoize.intUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator function = Long::sum;
        final LongBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final LongBinaryOperator memoize = CaffeineMemoize.longBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToDoubleFunction memoize = CaffeineMemoize.longToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunctionWithKeyFunction() {
        // given
        final LongToIntFunction function = a -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToIntFunction memoize = CaffeineMemoize.longToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator function = a -> 123L;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongUnaryOperator memoize = CaffeineMemoize.longUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeSupplierWithKeyFunction() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final Supplier<String> memoize = CaffeineMemoize.supplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplierWithKeyFunction() {
        // given
        final BooleanSupplier supplier = () -> false;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final BooleanSupplier memoize = CaffeineMemoize.booleanSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplierWithKeyFunction() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final DoubleSupplier memoize = CaffeineMemoize.doubleSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplierWithKeyFunction() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final IntSupplier memoize = CaffeineMemoize.intSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplierWithKeyFunction() {
        // given
        final LongSupplier supplier = () -> 123L;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final LongSupplier memoize = CaffeineMemoize.longSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunctionWithKeyFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = Double::sum;
        final BiFunction<Double, Double, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoize.toDoubleBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunctionWithKeyFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = Integer::sum;
        final BiFunction<Integer, Integer, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoize.toIntBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunctionWithKeyFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = Long::sum;
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoize.toLongBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunctionWithKeyFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToDoubleFunction<String> memoize = CaffeineMemoize.toDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunctionWithKeyFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToIntFunction<String> memoize = CaffeineMemoize.toIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunctionWithKeyFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToLongFunction<String> memoize = CaffeineMemoize.toLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Predicate<String> memoize = CaffeineMemoize.predicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicateWithKeyFunction() {
        // given
        final LongPredicate predicate = a -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongPredicate memoize = CaffeineMemoize.longPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicateWithKeyFunction() {
        // given
        final IntPredicate predicate = a -> true;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntPredicate memoize = CaffeineMemoize.intPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicateWithKeyFunction() {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoublePredicate memoize = CaffeineMemoize.doublePredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumerWithKeyFunction() {
        // given
        final Consumer<String> consumer = System.out::println;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Consumer<String> memoize = CaffeineMemoize.consumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumerWithKeyFunction() {
        // given
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleConsumer memoize = CaffeineMemoize.doubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumerWithKeyFunction() {
        // given
        final IntConsumer consumer = System.out::println;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntConsumer memoize = CaffeineMemoize.intConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumerWithKeyFunction() {
        // given
        final LongConsumer consumer = System.out::println;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongConsumer memoize = CaffeineMemoize.longConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicateWithKeyFunction() {
        // given
        final BiPredicate<Long, Long> function = (first, second) -> false;
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final BiPredicate<Long, Long> memoize = CaffeineMemoize.biPredicate(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunctionWithKeyFunction() {
        // given
        final BiFunction<Long, Long, String> function = (first, second) -> "test";
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final BiFunction<Long, Long, String> memoize = CaffeineMemoize.biFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiConsumerWithKeyFunction() {
        // given
        final BiConsumer<Long, Long> function = (first, second) -> System.out.println(first + " " + second);
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final BiConsumer<Long, Long> memoize = CaffeineMemoize.biConsumer(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<Long> consumer = (first, second) -> System.out.println(first.toString() + second);
        final ObjDoubleFunction<Long, String> keyFunction = MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction();

        // when
        final ObjDoubleConsumer<Long> memoize = CaffeineMemoize.objDoubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumerWithKeyFunction() {
        // given
        final ObjIntConsumer<Long> consumer = (first, second) -> System.out.println(first.toString() + second);
        final ObjIntFunction<Long, String> keyFunction = MemoizationDefaults.objIntConsumerHashCodeKeyFunction();

        // when
        final ObjIntConsumer<Long> memoize = CaffeineMemoize.objIntConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumerWithKeyFunction() {
        // given
        final ObjLongConsumer<Long> function = (first, second) -> System.out.println(first.toString() + second);
        final ObjLongFunction<Long, String> keyFunction = MemoizationDefaults.objLongConsumerHashCodeKeyFunction();

        // when
        final ObjLongConsumer<Long> memoize = CaffeineMemoize.objLongConsumer(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

}
