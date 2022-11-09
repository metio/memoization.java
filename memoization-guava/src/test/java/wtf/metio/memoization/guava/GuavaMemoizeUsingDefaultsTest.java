/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import wtf.metio.memoization.tck.UsingDefaultsTCK;

import java.util.function.*;

class GuavaMemoizeUsingDefaultsTest extends UsingDefaultsTCK {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return GuavaMemoize.function(function);
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return GuavaMemoize.intFunction(function);
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return GuavaMemoize.longFunction(function);
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return GuavaMemoize.doubleFunction(function);
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return GuavaMemoize.doubleToIntFunction(function);
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return GuavaMemoize.doubleToLongFunction(function);
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return GuavaMemoize.doubleUnaryOperator(operator);
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return GuavaMemoize.doubleBinaryOperator(operator);
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return GuavaMemoize.intBinaryOperator(operator);
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return GuavaMemoize.intToDoubleFunction(function);
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return GuavaMemoize.intToLongFunction(function);
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return GuavaMemoize.intUnaryOperator(operator);
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return GuavaMemoize.longBinaryOperator(operator);
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return GuavaMemoize.longToDoubleFunction(function);
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return GuavaMemoize.longToIntFunction(function);
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return GuavaMemoize.longUnaryOperator(operator);
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return GuavaMemoize.supplier(supplier);
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return GuavaMemoize.booleanSupplier(supplier);
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return GuavaMemoize.doubleSupplier(supplier);
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return GuavaMemoize.intSupplier(supplier);
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return GuavaMemoize.longSupplier(supplier);
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return GuavaMemoize.toDoubleBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return GuavaMemoize.toIntBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return GuavaMemoize.toLongBiFunction(function);
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return GuavaMemoize.toDoubleFunction(function);
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return GuavaMemoize.toIntFunction(function);
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return GuavaMemoize.toLongFunction(function);
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return GuavaMemoize.predicate(predicate);
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return GuavaMemoize.longPredicate(predicate);
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return GuavaMemoize.intPredicate(predicate);
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return GuavaMemoize.doublePredicate(predicate);
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return GuavaMemoize.consumer(consumer);
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return GuavaMemoize.doubleConsumer(consumer);
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return GuavaMemoize.intConsumer(consumer);
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return GuavaMemoize.longConsumer(consumer);
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return GuavaMemoize.biPredicate(predicate);
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return GuavaMemoize.biFunction(biFunction);
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return GuavaMemoize.biConsumer(biConsumer);
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return GuavaMemoize.objDoubleConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return GuavaMemoize.objIntConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return GuavaMemoize.objLongConsumer(consumer);
    }

}
