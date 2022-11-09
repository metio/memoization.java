/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import wtf.metio.memoization.core.*;
import wtf.metio.memoization.tck.UsingCustomKeyFunctionTCK;

import java.util.function.*;

class GuavaMemoizeUsingCustomKeyFunctionTest extends UsingCustomKeyFunctionTCK {

    @Override
    protected <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function, final Function<INPUT, KEY> keyFunction) {
        return GuavaMemoize.function(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function, final IntFunction<KEY> keyFunction) {
        return GuavaMemoize.intFunction(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function, final LongFunction<KEY> keyFunction) {
        return GuavaMemoize.longFunction(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function, final DoubleFunction<KEY> keyFunction) {
        return GuavaMemoize.doubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function, final DoubleFunction<KEY> keyFunction) {
        return GuavaMemoize.doubleToIntFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function, final DoubleFunction<KEY> keyFunction) {
        return GuavaMemoize.doubleToLongFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator, final DoubleFunction<KEY> keyFunction) {
        return GuavaMemoize.doubleUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator, final DoubleBinaryFunction<KEY> keyFunction) {
        return GuavaMemoize.doubleBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator, final IntBinaryFunction<KEY> keyFunction) {
        return GuavaMemoize.intBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function, final IntFunction<KEY> keyFunction) {
        return GuavaMemoize.intToDoubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function, final IntFunction<KEY> keyFunction) {
        return GuavaMemoize.intToLongFunction(function, keyFunction);
    }

    @Override
    protected <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator, final IntFunction<KEY> keyFunction) {
        return GuavaMemoize.intUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator, final LongBinaryFunction<KEY> keyFunction) {
        return GuavaMemoize.longBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function, final LongFunction<KEY> keyFunction) {
        return GuavaMemoize.longToDoubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function, final LongFunction<KEY> keyFunction) {
        return GuavaMemoize.longToIntFunction(function, keyFunction);
    }

    @Override
    protected <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator, final LongFunction<KEY> keyFunction) {
        return GuavaMemoize.longUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier, final Supplier<KEY> keySupplier) {
        return GuavaMemoize.supplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> BooleanSupplier booleanSupplier(final BooleanSupplier supplier, final Supplier<KEY> keySupplier) {
        return GuavaMemoize.booleanSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> DoubleSupplier doubleSupplier(final DoubleSupplier supplier, final Supplier<KEY> keySupplier) {
        return GuavaMemoize.doubleSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> IntSupplier intSupplier(final IntSupplier supplier, final Supplier<KEY> keySupplier) {
        return GuavaMemoize.intSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> LongSupplier longSupplier(final LongSupplier supplier, final Supplier<KEY> keySupplier) {
        return GuavaMemoize.longSupplier(supplier, keySupplier);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return GuavaMemoize.toDoubleBiFunction(function, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return GuavaMemoize.toIntBiFunction(function, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return GuavaMemoize.toLongBiFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return GuavaMemoize.toDoubleFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return GuavaMemoize.toIntFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return GuavaMemoize.toLongFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate, final Function<INPUT, KEY> keyFunction) {
        return GuavaMemoize.predicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> LongPredicate longPredicate(final LongPredicate predicate, final LongFunction<KEY> keyFunction) {
        return GuavaMemoize.longPredicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> IntPredicate intPredicate(final IntPredicate predicate, final IntFunction<KEY> keyFunction) {
        return GuavaMemoize.intPredicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate, final DoubleFunction<KEY> keyFunction) {
        return GuavaMemoize.doublePredicate(predicate, keyFunction);
    }

    @Override
    protected <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer, final Function<INPUT, KEY> keyFunction) {
        return GuavaMemoize.consumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> DoubleConsumer doubleConsumer(final DoubleConsumer consumer, final DoubleFunction<KEY> keyFunction) {
        return GuavaMemoize.doubleConsumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> IntConsumer intConsumer(final IntConsumer consumer, final IntFunction<KEY> keyFunction) {
        return GuavaMemoize.intConsumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> LongConsumer longConsumer(final LongConsumer consumer, final LongFunction<KEY> keyFunction) {
        return GuavaMemoize.longConsumer(consumer, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return GuavaMemoize.biPredicate(predicate, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return GuavaMemoize.biFunction(biFunction, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return GuavaMemoize.biConsumer(biConsumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer, final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return GuavaMemoize.objDoubleConsumer(consumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer, final ObjIntFunction<INPUT, KEY> keyFunction) {
        return GuavaMemoize.objIntConsumer(consumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer, final ObjLongFunction<INPUT, KEY> keyFunction) {
        return GuavaMemoize.objLongConsumer(consumer, keyFunction);
    }

}
