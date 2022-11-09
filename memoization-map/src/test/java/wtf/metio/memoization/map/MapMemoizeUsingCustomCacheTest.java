/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import wtf.metio.memoization.tck.UsingDefaultsTCK;

import java.util.Collections;
import java.util.function.*;

class MapMemoizeUsingCustomCacheTest extends UsingDefaultsTCK {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return MapMemoize.function(function, Collections.emptyMap());
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return MapMemoize.intFunction(function, Collections.emptyMap());
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return MapMemoize.longFunction(function, Collections.emptyMap());
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return MapMemoize.doubleFunction(function, Collections.emptyMap());
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return MapMemoize.doubleToIntFunction(function, Collections.emptyMap());
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return MapMemoize.doubleToLongFunction(function, Collections.emptyMap());
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return MapMemoize.doubleUnaryOperator(operator, Collections.emptyMap());
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return MapMemoize.doubleBinaryOperator(operator, Collections.emptyMap());
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return MapMemoize.intBinaryOperator(operator, Collections.emptyMap());
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return MapMemoize.intToDoubleFunction(function, Collections.emptyMap());
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return MapMemoize.intToLongFunction(function, Collections.emptyMap());
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return MapMemoize.intUnaryOperator(operator, Collections.emptyMap());
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return MapMemoize.longBinaryOperator(operator, Collections.emptyMap());
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return MapMemoize.longToDoubleFunction(function, Collections.emptyMap());
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return MapMemoize.longToIntFunction(function, Collections.emptyMap());
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return MapMemoize.longUnaryOperator(operator, Collections.emptyMap());
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return MapMemoize.supplier(supplier, Collections.emptyMap());
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return MapMemoize.booleanSupplier(supplier, Collections.emptyMap());
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return MapMemoize.doubleSupplier(supplier, Collections.emptyMap());
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return MapMemoize.intSupplier(supplier, Collections.emptyMap());
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return MapMemoize.longSupplier(supplier, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return MapMemoize.toDoubleBiFunction(function, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return MapMemoize.toIntBiFunction(function, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return MapMemoize.toLongBiFunction(function, Collections.emptyMap());
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return MapMemoize.toDoubleFunction(function, Collections.emptyMap());
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return MapMemoize.toIntFunction(function, Collections.emptyMap());
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return MapMemoize.toLongFunction(function, Collections.emptyMap());
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return MapMemoize.predicate(predicate, Collections.emptyMap());
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return MapMemoize.longPredicate(predicate, Collections.emptyMap());
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return MapMemoize.intPredicate(predicate, Collections.emptyMap());
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return MapMemoize.doublePredicate(predicate, Collections.emptyMap());
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return MapMemoize.consumer(consumer, Collections.emptyMap());
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return MapMemoize.doubleConsumer(consumer, Collections.emptyMap());
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return MapMemoize.intConsumer(consumer, Collections.emptyMap());
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return MapMemoize.longConsumer(consumer, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return MapMemoize.biPredicate(predicate, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return MapMemoize.biFunction(biFunction, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return MapMemoize.biConsumer(biConsumer, Collections.emptyMap());
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return MapMemoize.objDoubleConsumer(consumer, Collections.emptyMap());
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return MapMemoize.objIntConsumer(consumer, Collections.emptyMap());
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return MapMemoize.objLongConsumer(consumer, Collections.emptyMap());
    }

}
