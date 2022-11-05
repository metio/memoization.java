/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

class CaffeineMemoizeDefaultsTest {

    @Test
    void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = CaffeineMemoize.function(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunction() {
        // given
        final IntFunction<String> function = a -> "test";

        // when
        final IntFunction<String> memoize = CaffeineMemoize.intFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunction() {
        // given
        final LongFunction<String> function = a -> "test";

        // when
        final LongFunction<String> memoize = CaffeineMemoize.longFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";

        // when
        final DoubleFunction<String> memoize = CaffeineMemoize.doubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;

        // when
        final DoubleToIntFunction memoize = CaffeineMemoize.doubleToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;

        // when
        final DoubleToLongFunction memoize = CaffeineMemoize.doubleToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();

        // when
        final DoubleUnaryOperator memoize = CaffeineMemoize.doubleUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator function = Double::sum;

        // when
        final DoubleBinaryOperator memoize = CaffeineMemoize.doubleBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator function = Integer::sum;

        // when
        final IntBinaryOperator memoize = CaffeineMemoize.intBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;

        // when
        final IntToDoubleFunction memoize = CaffeineMemoize.intToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction function = a -> 123L;

        // when
        final IntToLongFunction memoize = CaffeineMemoize.intToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator function = a -> 123;

        // when
        final IntUnaryOperator memoize = CaffeineMemoize.intUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator function = Long::sum;

        // when
        final LongBinaryOperator memoize = CaffeineMemoize.longBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;

        // when
        final LongToDoubleFunction memoize = CaffeineMemoize.longToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction function = a -> 123;

        // when
        final LongToIntFunction memoize = CaffeineMemoize.longToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator function = a -> 123L;

        // when
        final LongUnaryOperator memoize = CaffeineMemoize.longUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = CaffeineMemoize.supplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final BooleanSupplier memoize = CaffeineMemoize.booleanSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final DoubleSupplier memoize = CaffeineMemoize.doubleSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final IntSupplier memoize = CaffeineMemoize.intSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123L;

        // when
        final LongSupplier memoize = CaffeineMemoize.longSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = Double::sum;

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoize.toDoubleBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = Integer::sum;

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoize.toIntBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = Long::sum;

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoize.toLongBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;

        // when
        final ToDoubleFunction<String> memoize = CaffeineMemoize.toDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;

        // when
        final ToIntFunction<String> memoize = CaffeineMemoize.toIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;

        // when
        final ToLongFunction<String> memoize = CaffeineMemoize.toLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = a -> true;

        // when
        final Predicate<String> memoize = CaffeineMemoize.predicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = a -> true;

        // when
        final LongPredicate memoize = CaffeineMemoize.longPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = a -> true;

        // when
        final IntPredicate memoize = CaffeineMemoize.intPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = a -> true;

        // when
        final DoublePredicate memoize = CaffeineMemoize.doublePredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = CaffeineMemoize.consumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final DoubleConsumer memoize = CaffeineMemoize.doubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final IntConsumer memoize = CaffeineMemoize.intConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final LongConsumer memoize = CaffeineMemoize.longConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<Long, Long> biPredicate = (first, second) -> true;

        // when
        final BiPredicate<Long, Long> memoize = CaffeineMemoize.biPredicate(biPredicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunction() {
        // given
        final BiFunction<Long, Long, String> function = (first, second) -> "test";

        // when
        final BiFunction<Long, Long, String> memoize = CaffeineMemoize.biFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<Long, Long> function = (first, second) -> System.out.println(first + " " + second);

        // when
        final BiConsumer<Long, Long> memoize = CaffeineMemoize.biConsumer(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<Long> consumer = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjDoubleConsumer<Long> memoize = CaffeineMemoize.objDoubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumer() {
        // given
        final ObjIntConsumer<Long> consumer = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjIntConsumer<Long> memoize = CaffeineMemoize.objIntConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumer() {
        // given
        final ObjLongConsumer<Long> function = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjLongConsumer<Long> memoize = CaffeineMemoize.objLongConsumer(function);

        // then
        Assertions.assertNotNull(memoize);
    }

}
