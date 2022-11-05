/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

class GuavaMemoizeDefaultsTest {

    @Test
    void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);

        // when
        final var memoize = GuavaMemoize.biConsumer(biConsumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";

        // when
        final var memoize = GuavaMemoize.biFunction(bifunction);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<String, String> biPredicate = (a, b) -> true;

        // when
        final var memoize = GuavaMemoize.biPredicate(biPredicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final var memoize = GuavaMemoize.booleanSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final var memoize = GuavaMemoize.consumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator operator = (a, b) -> 123.456D;

        // when
        final var memoize = GuavaMemoize.doubleBinaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final var memoize = GuavaMemoize.doubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";

        // when
        final var memoize = GuavaMemoize.doubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = a -> true;

        // when
        final var memoize = GuavaMemoize.doublePredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final var memoize = GuavaMemoize.doubleSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;

        // when
        final var memoize = GuavaMemoize.doubleToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123;

        // when
        final var memoize = GuavaMemoize.doubleToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator function = a -> 123.456D;

        // when
        final var memoize = GuavaMemoize.doubleUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final var memoize = GuavaMemoize.function(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator function = (a, b) -> 123;

        // when
        final var memoize = GuavaMemoize.intBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final var memoize = GuavaMemoize.intConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntFunction() {
        // given
        final IntFunction<String> function = a -> "test";

        // when
        final var memoize = GuavaMemoize.intFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = a -> true;

        // when
        final var memoize = GuavaMemoize.intPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final var memoize = GuavaMemoize.intSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;

        // when
        final var memoize = GuavaMemoize.intToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction function = a -> 123;

        // when
        final var memoize = GuavaMemoize.intToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator function = a -> 123;

        // when
        final var memoize = GuavaMemoize.intUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator operator = (a, b) -> 123;

        // when
        final var memoize = GuavaMemoize.longBinaryOperator(operator);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final var memoize = GuavaMemoize.longConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongFunction() {
        // given
        final LongFunction<String> function = a -> "test";

        // when
        final var memoize = GuavaMemoize.longFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = a -> true;

        // when
        final var memoize = GuavaMemoize.longPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123;

        // when
        final var memoize = GuavaMemoize.longSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction function = a -> 123D;

        // when
        final var memoize = GuavaMemoize.longToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction function = a -> 123;

        // when
        final var memoize = GuavaMemoize.longToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator function = a -> 123;

        // when
        final var memoize = GuavaMemoize.longUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<String> consumer = (a, b) -> System.out.println(a + b);

        // when
        final var memoize = GuavaMemoize.objDoubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjIntConsumer() {
        // given
        final ObjIntConsumer<String> consumer = (a, b) -> System.out.println(a + b);

        // when
        final var memoize = GuavaMemoize.objIntConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeObjLongConsumer() {
        // given
        final ObjLongConsumer<String> consumer = (a, b) -> System.out.println(a + b);

        // when
        final var memoize = GuavaMemoize.objLongConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = a -> true;

        // when
        final var memoize = GuavaMemoize.predicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final var memoize = GuavaMemoize.supplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<String, String> function = (a, b) -> 123;

        // when
        final var memoize = GuavaMemoize.toDoubleBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = a -> 123;

        // when
        final var memoize = GuavaMemoize.toDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<String, String> function = (a, b) -> 123;

        // when
        final var memoize = GuavaMemoize.toIntBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = a -> 123;

        // when
        final var memoize = GuavaMemoize.toIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<String, String> function = (a, b) -> 123;

        // when
        final var memoize = GuavaMemoize.toLongBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    @Test
    void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = a -> 123;

        // when
        final var memoize = GuavaMemoize.toLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

}
