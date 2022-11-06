/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.tck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wtf.metio.memoization.core.*;

import java.util.function.*;

public abstract class UsingCustomKeyFunctionTest {

    @Test
    final void shouldMemoizeFunctionWithKeyFunction() {
        // given
        final Function<String, String> function = a -> "test";
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Function<String, String> memoize = function(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            Function<INPUT, OUTPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeIntFunctionWithKeyFunction() {
        // given
        final IntFunction<String> function = a -> "test";
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntFunction<String> memoize = intFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            IntFunction<OUTPUT> function,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongFunctionWithKeyFunction() {
        // given
        final LongFunction<String> function = a -> "test";
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongFunction<String> memoize = longFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            LongFunction<OUTPUT> function,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleFunction<String> memoize = doubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            DoubleFunction<OUTPUT> function,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleToIntFunctionWithKeyFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToIntFunction memoize = doubleToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoubleToIntFunction doubleToIntFunction(
            DoubleToIntFunction function,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleToLongFunctionWithKeyFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleToLongFunction memoize = doubleToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoubleToLongFunction doubleToLongFunction(
            DoubleToLongFunction function,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleUnaryOperator memoize = doubleUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoubleUnaryOperator doubleUnaryOperator(
            DoubleUnaryOperator operator,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() {
        // given
        final DoubleBinaryOperator function = Double::sum;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final DoubleBinaryOperator memoize = doubleBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoubleBinaryOperator doubleBinaryOperator(
            DoubleBinaryOperator operator,
            DoubleBinaryFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntBinaryOperatorWithKeyFunction() {
        // given
        final IntBinaryOperator function = Integer::sum;
        final IntBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final IntBinaryOperator memoize = intBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntBinaryOperator intBinaryOperator(
            IntBinaryOperator operator,
            IntBinaryFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntToDoubleFunctionWithKeyFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToDoubleFunction memoize = intToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntToDoubleFunction intToDoubleFunction(
            IntToDoubleFunction function,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntToLongFunctionWithKeyFunction() {
        // given
        final IntToLongFunction function = a -> 123L;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntToLongFunction memoize = intToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntToLongFunction intToLongFunction(
            IntToLongFunction function,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntUnaryOperatorWithKeyFunction() {
        // given
        final IntUnaryOperator function = a -> 123;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntUnaryOperator memoize = intUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntUnaryOperator intUnaryOperator(
            IntUnaryOperator operator,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongBinaryOperatorWithKeyFunction() {
        // given
        final LongBinaryOperator function = Long::sum;
        final LongBinaryFunction<String> keyFunction = (first, second) -> "key";

        // when
        final LongBinaryOperator memoize = longBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongBinaryOperator longBinaryOperator(
            LongBinaryOperator operator,
            LongBinaryFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongToDoubleFunctionWithKeyFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToDoubleFunction memoize = longToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongToDoubleFunction longToDoubleFunction(
            LongToDoubleFunction function,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongToIntFunctionWithKeyFunction() {
        // given
        final LongToIntFunction function = a -> 123;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongToIntFunction memoize = longToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongToIntFunction longToIntFunction(
            LongToIntFunction function,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongUnaryOperatorWithKeyFunction() {
        // given
        final LongUnaryOperator function = a -> 123L;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongUnaryOperator memoize = longUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongUnaryOperator longUnaryOperator(
            LongUnaryOperator operator,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeSupplierWithKeyFunction() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final Supplier<String> memoize = supplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            Supplier<OUTPUT> supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeBooleanSupplierWithKeyFunction() {
        // given
        final BooleanSupplier supplier = () -> false;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final BooleanSupplier memoize = booleanSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> BooleanSupplier booleanSupplier(
            BooleanSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeDoubleSupplierWithKeyFunction() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final DoubleSupplier memoize = doubleSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoubleSupplier doubleSupplier(
            DoubleSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeIntSupplierWithKeyFunction() {
        // given
        final IntSupplier supplier = () -> 123;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final IntSupplier memoize = intSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntSupplier intSupplier(
            IntSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeLongSupplierWithKeyFunction() {
        // given
        final LongSupplier supplier = () -> 123L;
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final LongSupplier memoize = longSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongSupplier longSupplier(
            LongSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeToDoubleBiFunctionWithKeyFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = Double::sum;
        final BiFunction<Double, Double, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final ToDoubleBiFunction<Double, Double> memoize = toDoubleBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            ToDoubleBiFunction<FIRST, SECOND> function,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeToIntBiFunctionWithKeyFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = Integer::sum;
        final BiFunction<Integer, Integer, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final ToIntBiFunction<Integer, Integer> memoize = toIntBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            ToIntBiFunction<FIRST, SECOND> function,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeToLongBiFunctionWithKeyFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = Long::sum;
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final ToLongBiFunction<Long, Long> memoize = toLongBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            ToLongBiFunction<FIRST, SECOND> function,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeToDoubleFunctionWithKeyFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToDoubleFunction<String> memoize = toDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            ToDoubleFunction<INPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeToIntFunctionWithKeyFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToIntFunction<String> memoize = toIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            ToIntFunction<INPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeToLongFunctionWithKeyFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final ToLongFunction<String> memoize = toLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            ToLongFunction<INPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizePredicateWithKeyFunction() {
        // given
        final Predicate<String> predicate = a -> true;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Predicate<String> memoize = predicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> Predicate<INPUT> predicate(
            Predicate<INPUT> predicate,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeLongPredicateWithKeyFunction() {
        // given
        final LongPredicate predicate = a -> true;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongPredicate memoize = longPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongPredicate longPredicate(
            LongPredicate predicate,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntPredicateWithKeyFunction() {
        // given
        final IntPredicate predicate = a -> true;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntPredicate memoize = intPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntPredicate intPredicate(
            IntPredicate predicate,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoublePredicateWithKeyFunction() {
        // given
        final DoublePredicate predicate = a -> true;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoublePredicate memoize = doublePredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoublePredicate doublePredicate(
            DoublePredicate predicate,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumerWithKeyFunction() {
        // given
        final Consumer<String> consumer = System.out::println;
        final Function<String, String> keyFunction = a -> "key";

        // when
        final Consumer<String> memoize = consumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> Consumer<INPUT> consumer(
            Consumer<INPUT> consumer,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleConsumerWithKeyFunction() {
        // given
        final DoubleConsumer consumer = System.out::println;
        final DoubleFunction<String> keyFunction = a -> "key";

        // when
        final DoubleConsumer memoize = doubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> DoubleConsumer doubleConsumer(
            DoubleConsumer consumer,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntConsumerWithKeyFunction() {
        // given
        final IntConsumer consumer = System.out::println;
        final IntFunction<String> keyFunction = a -> "key";

        // when
        final IntConsumer memoize = intConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> IntConsumer intConsumer(
            IntConsumer consumer,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongConsumerWithKeyFunction() {
        // given
        final LongConsumer consumer = System.out::println;
        final LongFunction<String> keyFunction = a -> "key";

        // when
        final LongConsumer memoize = longConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <KEY> LongConsumer longConsumer(
            LongConsumer consumer,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeBiPredicateWithKeyFunction() {
        // given
        final BiPredicate<Long, Long> function = (first, second) -> false;
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final BiPredicate<Long, Long> memoize = biPredicate(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            BiPredicate<FIRST, SECOND> predicate,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeBiFunctionWithKeyFunction() {
        // given
        final BiFunction<Long, Long, String> function = (first, second) -> "test";
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final BiFunction<Long, Long, String> memoize = biFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeBiConsumerWithKeyFunction() {
        // given
        final BiConsumer<Long, Long> function = (first, second) -> System.out.println(first + " " + second);
        final BiFunction<Long, Long, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final BiConsumer<Long, Long> memoize = biConsumer(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            BiConsumer<FIRST, SECOND> biConsumer,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeObjDoubleConsumerWithKeyFunction() {
        // given
        final ObjDoubleConsumer<Long> consumer = (first, second) -> System.out.println(first.toString() + second);
        final ObjDoubleFunction<Long, String> keyFunction = MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction();

        // when
        final ObjDoubleConsumer<Long> memoize = objDoubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            ObjDoubleConsumer<INPUT> consumer,
            ObjDoubleFunction<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeObjIntConsumerWithKeyFunction() {
        // given
        final ObjIntConsumer<Long> consumer = (first, second) -> System.out.println(first.toString() + second);
        final ObjIntFunction<Long, String> keyFunction = MemoizationDefaults.objIntConsumerHashCodeKeyFunction();

        // when
        final ObjIntConsumer<Long> memoize = objIntConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            ObjIntConsumer<INPUT> consumer,
            ObjIntFunction<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeObjLongConsumerWithKeyFunction() {
        // given
        final ObjLongConsumer<Long> function = (first, second) -> System.out.println(first.toString() + second);
        final ObjLongFunction<Long, String> keyFunction = MemoizationDefaults.objLongConsumerHashCodeKeyFunction();

        // when
        final ObjLongConsumer<Long> memoize = objLongConsumer(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            ObjLongConsumer<INPUT> consumer,
            ObjLongFunction<INPUT, KEY> keyFunction);

}
