/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.*;

abstract class MemoizeUsingCustomCacheTCK extends UsingDefaultsTCK {

    protected abstract <K, V> Map<K, V> cache();

    @Override
    protected final <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return Memoize.function(function, cache());
    }

    @Override
    protected final <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return Memoize.intFunction(function, cache());
    }

    @Override
    protected final <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return Memoize.longFunction(function, cache());
    }

    @Override
    protected final <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return Memoize.doubleFunction(function, cache());
    }

    @Override
    protected final DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return Memoize.doubleToIntFunction(function, cache());
    }

    @Override
    protected final DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return Memoize.doubleToLongFunction(function, cache());
    }

    @Override
    protected final DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return Memoize.doubleUnaryOperator(operator, cache());
    }

    @Override
    protected final DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return Memoize.doubleBinaryOperator(operator, cache());
    }

    @Override
    protected final IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return Memoize.intBinaryOperator(operator, cache());
    }

    @Override
    protected final IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return Memoize.intToDoubleFunction(function, cache());
    }

    @Override
    protected final IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return Memoize.intToLongFunction(function, cache());
    }

    @Override
    protected final IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return Memoize.intUnaryOperator(operator, cache());
    }

    @Override
    protected final LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return Memoize.longBinaryOperator(operator, cache());
    }

    @Override
    protected final LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return Memoize.longToDoubleFunction(function, cache());
    }

    @Override
    protected final LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return Memoize.longToIntFunction(function, cache());
    }

    @Override
    protected final LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return Memoize.longUnaryOperator(operator, cache());
    }

    @Override
    protected final <OUTPUT> Callable<OUTPUT> callable(final Callable<OUTPUT> callable) {
        return Memoize.callable(callable, cache());
    }

    @Override
    protected final Runnable runnable(final Runnable runnable) {
        return Memoize.runnable(runnable, cache());
    }

    @Override
    protected final <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return Memoize.supplier(supplier, cache());
    }

    @Override
    protected final BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return Memoize.booleanSupplier(supplier, cache());
    }

    @Override
    protected final DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return Memoize.doubleSupplier(supplier, cache());
    }

    @Override
    protected final IntSupplier intSupplier(final IntSupplier supplier) {
        return Memoize.intSupplier(supplier, cache());
    }

    @Override
    protected final LongSupplier longSupplier(final LongSupplier supplier) {
        return Memoize.longSupplier(supplier, cache());
    }

    @Override
    protected final <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return Memoize.toDoubleBiFunction(function, cache());
    }

    @Override
    protected final <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return Memoize.toIntBiFunction(function, cache());
    }

    @Override
    protected final <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return Memoize.toLongBiFunction(function, cache());
    }

    @Override
    protected final <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return Memoize.toDoubleFunction(function, cache());
    }

    @Override
    protected final <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return Memoize.toIntFunction(function, cache());
    }

    @Override
    protected final <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return Memoize.toLongFunction(function, cache());
    }

    @Override
    protected final <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return Memoize.predicate(predicate, cache());
    }

    @Override
    protected final LongPredicate longPredicate(final LongPredicate predicate) {
        return Memoize.longPredicate(predicate, cache());
    }

    @Override
    protected final IntPredicate intPredicate(final IntPredicate predicate) {
        return Memoize.intPredicate(predicate, cache());
    }

    @Override
    protected final DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return Memoize.doublePredicate(predicate, cache());
    }

    @Override
    protected final <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return Memoize.consumer(consumer, cache());
    }

    @Override
    protected final DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return Memoize.doubleConsumer(consumer, cache());
    }

    @Override
    protected final IntConsumer intConsumer(final IntConsumer consumer) {
        return Memoize.intConsumer(consumer, cache());
    }

    @Override
    protected final LongConsumer longConsumer(final LongConsumer consumer) {
        return Memoize.longConsumer(consumer, cache());
    }

    @Override
    protected final <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return Memoize.biPredicate(predicate, cache());
    }

    @Override
    protected final <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return Memoize.biFunction(biFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return Memoize.biConsumer(biConsumer, cache());
    }

    @Override
    protected final <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return Memoize.objDoubleConsumer(consumer, cache());
    }

    @Override
    protected final <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return Memoize.objIntConsumer(consumer, cache());
    }

    @Override
    protected final <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return Memoize.objLongConsumer(consumer, cache());
    }

}
