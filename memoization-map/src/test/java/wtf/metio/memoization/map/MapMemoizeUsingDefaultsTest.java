/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import wtf.metio.memoization.tck.UsingDefaultsTCK;

import java.util.function.*;

class MapMemoizeUsingDefaultsTest extends UsingDefaultsTCK {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return MapMemoize.function(function);
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return MapMemoize.intFunction(function);
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return MapMemoize.longFunction(function);
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return MapMemoize.doubleFunction(function);
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return MapMemoize.doubleToIntFunction(function);
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return MapMemoize.doubleToLongFunction(function);
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return MapMemoize.doubleUnaryOperator(operator);
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return MapMemoize.doubleBinaryOperator(operator);
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return MapMemoize.intBinaryOperator(operator);
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return MapMemoize.intToDoubleFunction(function);
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return MapMemoize.intToLongFunction(function);
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return MapMemoize.intUnaryOperator(operator);
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return MapMemoize.longBinaryOperator(operator);
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return MapMemoize.longToDoubleFunction(function);
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return MapMemoize.longToIntFunction(function);
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return MapMemoize.longUnaryOperator(operator);
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return MapMemoize.supplier(supplier);
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return MapMemoize.booleanSupplier(supplier);
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return MapMemoize.doubleSupplier(supplier);
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return MapMemoize.intSupplier(supplier);
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return MapMemoize.longSupplier(supplier);
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return MapMemoize.toDoubleBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return MapMemoize.toIntBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return MapMemoize.toLongBiFunction(function);
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return MapMemoize.toDoubleFunction(function);
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return MapMemoize.toIntFunction(function);
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return MapMemoize.toLongFunction(function);
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return MapMemoize.predicate(predicate);
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return MapMemoize.longPredicate(predicate);
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return MapMemoize.intPredicate(predicate);
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return MapMemoize.doublePredicate(predicate);
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return MapMemoize.consumer(consumer);
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return MapMemoize.doubleConsumer(consumer);
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return MapMemoize.intConsumer(consumer);
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return MapMemoize.longConsumer(consumer);
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return MapMemoize.biPredicate(predicate);
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return MapMemoize.biFunction(biFunction);
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return MapMemoize.biConsumer(biConsumer);
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return MapMemoize.objDoubleConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return MapMemoize.objIntConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return MapMemoize.objLongConsumer(consumer);
    }

}
