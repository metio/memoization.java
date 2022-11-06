/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.cache2k;

import wtf.metio.memoization.core.*;
import wtf.metio.memoization.tck.UsingCustomKeyFunctionTest;

import java.util.function.*;

class Cache2kUsingCustomKeyFunctionTest extends UsingCustomKeyFunctionTest {

    @Override
    protected <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.function(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function, final IntFunction<KEY> keyFunction) {
        return Cache2kMemoize.intFunction(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function, final LongFunction<KEY> keyFunction) {
        return Cache2kMemoize.longFunction(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function, final DoubleFunction<KEY> keyFunction) {
        return Cache2kMemoize.doubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function, final DoubleFunction<KEY> keyFunction) {
        return Cache2kMemoize.doubleToIntFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function, final DoubleFunction<KEY> keyFunction) {
        return Cache2kMemoize.doubleToLongFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator, final DoubleFunction<KEY> keyFunction) {
        return Cache2kMemoize.doubleUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator, final DoubleBinaryFunction<KEY> keyFunction) {
        return Cache2kMemoize.doubleBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator, final IntBinaryFunction<KEY> keyFunction) {
        return Cache2kMemoize.intBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function, final IntFunction<KEY> keyFunction) {
        return Cache2kMemoize.intToDoubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> IntToLongFunction intToLongFunction(final IntToLongFunction function, final IntFunction<KEY> keyFunction) {
        return Cache2kMemoize.intToLongFunction(function, keyFunction);
    }

    @Override
    protected <KEY> IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator, final IntFunction<KEY> keyFunction) {
        return Cache2kMemoize.intUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator, final LongBinaryFunction<KEY> keyFunction) {
        return Cache2kMemoize.longBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function, final LongFunction<KEY> keyFunction) {
        return Cache2kMemoize.longToDoubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> LongToIntFunction longToIntFunction(final LongToIntFunction function, final LongFunction<KEY> keyFunction) {
        return Cache2kMemoize.longToIntFunction(function, keyFunction);
    }

    @Override
    protected <KEY> LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator, final LongFunction<KEY> keyFunction) {
        return Cache2kMemoize.longUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier, final Supplier<KEY> keySupplier) {
        return Cache2kMemoize.supplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> BooleanSupplier booleanSupplier(final BooleanSupplier supplier, final Supplier<KEY> keySupplier) {
        return Cache2kMemoize.booleanSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> DoubleSupplier doubleSupplier(final DoubleSupplier supplier, final Supplier<KEY> keySupplier) {
        return Cache2kMemoize.doubleSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> IntSupplier intSupplier(final IntSupplier supplier, final Supplier<KEY> keySupplier) {
        return Cache2kMemoize.intSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> LongSupplier longSupplier(final LongSupplier supplier, final Supplier<KEY> keySupplier) {
        return Cache2kMemoize.longSupplier(supplier, keySupplier);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Cache2kMemoize.toDoubleBiFunction(function, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Cache2kMemoize.toIntBiFunction(function, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Cache2kMemoize.toLongBiFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.toDoubleFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.toIntFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.toLongFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> Predicate<INPUT> predicate(final Predicate<INPUT> predicate, final Function<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.predicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> LongPredicate longPredicate(final LongPredicate predicate, final LongFunction<KEY> keyFunction) {
        return Cache2kMemoize.longPredicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> IntPredicate intPredicate(final IntPredicate predicate, final IntFunction<KEY> keyFunction) {
        return Cache2kMemoize.intPredicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> DoublePredicate doublePredicate(final DoublePredicate predicate, final DoubleFunction<KEY> keyFunction) {
        return Cache2kMemoize.doublePredicate(predicate, keyFunction);
    }

    @Override
    protected <INPUT, KEY> Consumer<INPUT> consumer(final Consumer<INPUT> consumer, final Function<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.consumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> DoubleConsumer doubleConsumer(final DoubleConsumer consumer, final DoubleFunction<KEY> keyFunction) {
        return Cache2kMemoize.doubleConsumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> IntConsumer intConsumer(final IntConsumer consumer, final IntFunction<KEY> keyFunction) {
        return Cache2kMemoize.intConsumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> LongConsumer longConsumer(final LongConsumer consumer, final LongFunction<KEY> keyFunction) {
        return Cache2kMemoize.longConsumer(consumer, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Cache2kMemoize.biPredicate(predicate, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Cache2kMemoize.biFunction(biFunction, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Cache2kMemoize.biConsumer(biConsumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer, final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.objDoubleConsumer(consumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer, final ObjIntFunction<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.objIntConsumer(consumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer, final ObjLongFunction<INPUT, KEY> keyFunction) {
        return Cache2kMemoize.objLongConsumer(consumer, keyFunction);
    }

}
