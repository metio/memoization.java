/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import java.util.concurrent.Callable;
import java.util.function.*;

class MemoizeUsingDefaultsTest extends UsingDefaultsTCK {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return Memoize.function(function);
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return Memoize.intFunction(function);
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return Memoize.longFunction(function);
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return Memoize.doubleFunction(function);
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return Memoize.doubleToIntFunction(function);
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return Memoize.doubleToLongFunction(function);
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return Memoize.doubleUnaryOperator(operator);
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return Memoize.doubleBinaryOperator(operator);
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return Memoize.intBinaryOperator(operator);
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return Memoize.intToDoubleFunction(function);
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return Memoize.intToLongFunction(function);
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return Memoize.intUnaryOperator(operator);
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return Memoize.longBinaryOperator(operator);
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return Memoize.longToDoubleFunction(function);
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return Memoize.longToIntFunction(function);
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return Memoize.longUnaryOperator(operator);
    }

    @Override
    protected <OUTPUT> Callable<OUTPUT> callable(final Callable<OUTPUT> callable) {
        return Memoize.callable(callable);
    }

    @Override
    protected Runnable runnable(final Runnable runnable) {
        return Memoize.runnable(runnable);
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return Memoize.supplier(supplier);
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return Memoize.booleanSupplier(supplier);
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return Memoize.doubleSupplier(supplier);
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return Memoize.intSupplier(supplier);
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return Memoize.longSupplier(supplier);
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return Memoize.toDoubleBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return Memoize.toIntBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return Memoize.toLongBiFunction(function);
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return Memoize.toDoubleFunction(function);
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return Memoize.toIntFunction(function);
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return Memoize.toLongFunction(function);
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return Memoize.predicate(predicate);
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return Memoize.longPredicate(predicate);
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return Memoize.intPredicate(predicate);
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return Memoize.doublePredicate(predicate);
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return Memoize.consumer(consumer);
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return Memoize.doubleConsumer(consumer);
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return Memoize.intConsumer(consumer);
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return Memoize.longConsumer(consumer);
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return Memoize.biPredicate(predicate);
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return Memoize.biFunction(biFunction);
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return Memoize.biConsumer(biConsumer);
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return Memoize.objDoubleConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return Memoize.objIntConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return Memoize.objLongConsumer(consumer);
    }

}
