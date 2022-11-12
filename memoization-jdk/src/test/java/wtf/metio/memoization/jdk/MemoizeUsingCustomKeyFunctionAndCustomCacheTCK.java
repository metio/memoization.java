/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.*;

abstract class MemoizeUsingCustomKeyFunctionAndCustomCacheTCK extends UsingCustomKeyFunctionTCK {

    protected abstract <K, V> Map<K, V> cache();

    @Override
    protected final <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Memoize.function(function, keyFunction, cache());
    }

    @Override
    protected final <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function, final IntFunction<KEY> keyFunction) {
        return Memoize.intFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function, final LongFunction<KEY> keyFunction) {
        return Memoize.longFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function, final DoubleFunction<KEY> keyFunction) {
        return Memoize.doubleFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function, final DoubleFunction<KEY> keyFunction) {
        return Memoize.doubleToIntFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function, final DoubleFunction<KEY> keyFunction) {
        return Memoize.doubleToLongFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator, final DoubleFunction<KEY> keyFunction) {
        return Memoize.doubleUnaryOperator(operator, keyFunction, cache());
    }

    @Override
    protected final <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator, final DoubleBinaryFunction<KEY> keyFunction) {
        return Memoize.doubleBinaryOperator(operator, keyFunction, cache());
    }

    @Override
    protected final <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator, final IntBinaryFunction<KEY> keyFunction) {
        return Memoize.intBinaryOperator(operator, keyFunction, cache());
    }

    @Override
    protected final <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function, final IntFunction<KEY> keyFunction) {
        return Memoize.intToDoubleFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function, final IntFunction<KEY> keyFunction) {
        return Memoize.intToLongFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator, final IntFunction<KEY> keyFunction) {
        return Memoize.intUnaryOperator(operator, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator, final LongBinaryFunction<KEY> keyFunction) {
        return Memoize.longBinaryOperator(operator, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function, final LongFunction<KEY> keyFunction) {
        return Memoize.longToDoubleFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function, final LongFunction<KEY> keyFunction) {
        return Memoize.longToIntFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator, final LongFunction<KEY> keyFunction) {
        return Memoize.longUnaryOperator(operator, keyFunction, cache());
    }

    @Override
    protected final <KEY, OUTPUT> Callable<OUTPUT> callable(final Callable<OUTPUT> callable, final Supplier<KEY> keySupplier) {
        return Memoize.callable(callable, keySupplier, cache());
    }

    @Override
    protected final <KEY> Runnable runnable(final Runnable runnable, final Supplier<KEY> keySupplier) {
        return Memoize.runnable(runnable, keySupplier, cache());
    }

    @Override
    protected final <KEY, OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier, final Supplier<KEY> keySupplier) {
        return Memoize.supplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <KEY> BooleanSupplier booleanSupplier(final BooleanSupplier supplier, final Supplier<KEY> keySupplier) {
        return Memoize.booleanSupplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <KEY> DoubleSupplier doubleSupplier(final DoubleSupplier supplier, final Supplier<KEY> keySupplier) {
        return Memoize.doubleSupplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <KEY> IntSupplier intSupplier(final IntSupplier supplier, final Supplier<KEY> keySupplier) {
        return Memoize.intSupplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <KEY> LongSupplier longSupplier(final LongSupplier supplier, final Supplier<KEY> keySupplier) {
        return Memoize.longSupplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Memoize.toDoubleBiFunction(function, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Memoize.toIntBiFunction(function, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Memoize.toLongBiFunction(function, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Memoize.toDoubleFunction(function, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Memoize.toIntFunction(function, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return Memoize.toLongFunction(function, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate, final Function<INPUT, KEY> keyFunction) {
        return Memoize.predicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongPredicate longPredicate(final LongPredicate predicate, final LongFunction<KEY> keyFunction) {
        return Memoize.longPredicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <KEY> IntPredicate intPredicate(final IntPredicate predicate, final IntFunction<KEY> keyFunction) {
        return Memoize.intPredicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate, final DoubleFunction<KEY> keyFunction) {
        return Memoize.doublePredicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer, final Function<INPUT, KEY> keyFunction) {
        return Memoize.consumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer, final DoubleFunction<KEY> keyFunction) {
        return Memoize.doubleConsumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <KEY> IntConsumer intConsumer(final IntConsumer consumer, final IntFunction<KEY> keyFunction) {
        return Memoize.intConsumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongConsumer longConsumer(final LongConsumer consumer, final LongFunction<KEY> keyFunction) {
        return Memoize.longConsumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Memoize.biPredicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Memoize.biFunction(biFunction, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return Memoize.biConsumer(biConsumer, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer, final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return Memoize.objDoubleConsumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer, final ObjIntFunction<INPUT, KEY> keyFunction) {
        return Memoize.objIntConsumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer, final ObjLongFunction<INPUT, KEY> keyFunction) {
        return Memoize.objLongConsumer(consumer, keyFunction, cache());
    }

}
