/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import wtf.metio.memoization.tck.UsingDefaultsTest;

import java.util.function.*;

class CaffeineUsingDefaultsTest extends UsingDefaultsTest {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return CaffeineMemoize.function(function);
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return CaffeineMemoize.intFunction(function);
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return CaffeineMemoize.longFunction(function);
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return CaffeineMemoize.doubleFunction(function);
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return CaffeineMemoize.doubleToIntFunction(function);
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return CaffeineMemoize.doubleToLongFunction(function);
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return CaffeineMemoize.doubleUnaryOperator(operator);
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return CaffeineMemoize.doubleBinaryOperator(operator);
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return CaffeineMemoize.intBinaryOperator(operator);
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return CaffeineMemoize.intToDoubleFunction(function);
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return CaffeineMemoize.intToLongFunction(function);
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return CaffeineMemoize.intUnaryOperator(operator);
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return CaffeineMemoize.longBinaryOperator(operator);
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return CaffeineMemoize.longToDoubleFunction(function);
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return CaffeineMemoize.longToIntFunction(function);
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return CaffeineMemoize.longUnaryOperator(operator);
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return CaffeineMemoize.supplier(supplier);
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return CaffeineMemoize.booleanSupplier(supplier);
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return CaffeineMemoize.doubleSupplier(supplier);
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return CaffeineMemoize.intSupplier(supplier);
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return CaffeineMemoize.longSupplier(supplier);
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return CaffeineMemoize.toDoubleBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return CaffeineMemoize.toIntBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return CaffeineMemoize.toLongBiFunction(function);
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return CaffeineMemoize.toDoubleFunction(function);
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return CaffeineMemoize.toIntFunction(function);
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return CaffeineMemoize.toLongFunction(function);
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return CaffeineMemoize.predicate(predicate);
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return CaffeineMemoize.longPredicate(predicate);
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return CaffeineMemoize.intPredicate(predicate);
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return CaffeineMemoize.doublePredicate(predicate);
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return CaffeineMemoize.consumer(consumer);
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return CaffeineMemoize.doubleConsumer(consumer);
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return CaffeineMemoize.intConsumer(consumer);
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return CaffeineMemoize.longConsumer(consumer);
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return CaffeineMemoize.biPredicate(predicate);
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return CaffeineMemoize.biFunction(biFunction);
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return CaffeineMemoize.biConsumer(biConsumer);
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return CaffeineMemoize.objDoubleConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return CaffeineMemoize.objIntConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return CaffeineMemoize.objLongConsumer(consumer);
    }

}
