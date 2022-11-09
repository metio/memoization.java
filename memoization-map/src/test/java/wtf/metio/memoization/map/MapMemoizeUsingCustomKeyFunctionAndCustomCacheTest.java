/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import wtf.metio.memoization.core.*;
import wtf.metio.memoization.tck.UsingCustomKeyFunctionTCK;

import java.util.Collections;
import java.util.function.*;

class MapMemoizeUsingCustomKeyFunctionAndCustomCacheTest extends UsingCustomKeyFunctionTCK {

    @Override
    protected <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function, final Function<INPUT, KEY> keyFunction) {
        return MapMemoize.function(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function, final IntFunction<KEY> keyFunction) {
        return MapMemoize.intFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function, final LongFunction<KEY> keyFunction) {
        return MapMemoize.longFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function, final DoubleFunction<KEY> keyFunction) {
        return MapMemoize.doubleFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function, final DoubleFunction<KEY> keyFunction) {
        return MapMemoize.doubleToIntFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function, final DoubleFunction<KEY> keyFunction) {
        return MapMemoize.doubleToLongFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator, final DoubleFunction<KEY> keyFunction) {
        return MapMemoize.doubleUnaryOperator(operator, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator, final DoubleBinaryFunction<KEY> keyFunction) {
        return MapMemoize.doubleBinaryOperator(operator, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator, final IntBinaryFunction<KEY> keyFunction) {
        return MapMemoize.intBinaryOperator(operator, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function, final IntFunction<KEY> keyFunction) {
        return MapMemoize.intToDoubleFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function, final IntFunction<KEY> keyFunction) {
        return MapMemoize.intToLongFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator, final IntFunction<KEY> keyFunction) {
        return MapMemoize.intUnaryOperator(operator, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator, final LongBinaryFunction<KEY> keyFunction) {
        return MapMemoize.longBinaryOperator(operator, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function, final LongFunction<KEY> keyFunction) {
        return MapMemoize.longToDoubleFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function, final LongFunction<KEY> keyFunction) {
        return MapMemoize.longToIntFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator, final LongFunction<KEY> keyFunction) {
        return MapMemoize.longUnaryOperator(operator, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY, OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier, final Supplier<KEY> keySupplier) {
        return MapMemoize.supplier(supplier, keySupplier, Collections.emptyMap());
    }

    @Override
    protected <KEY> BooleanSupplier booleanSupplier(final BooleanSupplier supplier, final Supplier<KEY> keySupplier) {
        return MapMemoize.booleanSupplier(supplier, keySupplier, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoubleSupplier doubleSupplier(final DoubleSupplier supplier, final Supplier<KEY> keySupplier) {
        return MapMemoize.doubleSupplier(supplier, keySupplier, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntSupplier intSupplier(final IntSupplier supplier, final Supplier<KEY> keySupplier) {
        return MapMemoize.intSupplier(supplier, keySupplier, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongSupplier longSupplier(final LongSupplier supplier, final Supplier<KEY> keySupplier) {
        return MapMemoize.longSupplier(supplier, keySupplier, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MapMemoize.toDoubleBiFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MapMemoize.toIntBiFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MapMemoize.toLongBiFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return MapMemoize.toDoubleFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return MapMemoize.toIntFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return MapMemoize.toLongFunction(function, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate, final Function<INPUT, KEY> keyFunction) {
        return MapMemoize.predicate(predicate, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongPredicate longPredicate(final LongPredicate predicate, final LongFunction<KEY> keyFunction) {
        return MapMemoize.longPredicate(predicate, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntPredicate intPredicate(final IntPredicate predicate, final IntFunction<KEY> keyFunction) {
        return MapMemoize.intPredicate(predicate, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate, final DoubleFunction<KEY> keyFunction) {
        return MapMemoize.doublePredicate(predicate, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer, final Function<INPUT, KEY> keyFunction) {
        return MapMemoize.consumer(consumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer, final DoubleFunction<KEY> keyFunction) {
        return MapMemoize.doubleConsumer(consumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> IntConsumer intConsumer(final IntConsumer consumer, final IntFunction<KEY> keyFunction) {
        return MapMemoize.intConsumer(consumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <KEY> LongConsumer longConsumer(final LongConsumer consumer, final LongFunction<KEY> keyFunction) {
        return MapMemoize.longConsumer(consumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MapMemoize.biPredicate(predicate, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MapMemoize.biFunction(biFunction, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MapMemoize.biConsumer(biConsumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer, final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return MapMemoize.objDoubleConsumer(consumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer, final ObjIntFunction<INPUT, KEY> keyFunction) {
        return MapMemoize.objIntConsumer(consumer, keyFunction, Collections.emptyMap());
    }

    @Override
    protected <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer, final ObjLongFunction<INPUT, KEY> keyFunction) {
        return MapMemoize.objLongConsumer(consumer, keyFunction, Collections.emptyMap());
    }

}
