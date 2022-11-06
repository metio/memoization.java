/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import wtf.metio.memoization.core.*;
import wtf.metio.memoization.tck.UsingCustomKeyFunctionTest;

import java.util.function.*;

class CaffeineUsingCustomKeyFunctionTest extends UsingCustomKeyFunctionTest {

    @Override
    protected <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function, final Function<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.function(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function, final IntFunction<KEY> keyFunction) {
        return CaffeineMemoize.intFunction(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            final LongFunction<OUTPUT> function, final LongFunction<KEY> keyFunction) {
        return CaffeineMemoize.longFunction(function, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            final DoubleFunction<OUTPUT> function, final DoubleFunction<KEY> keyFunction) {
        return CaffeineMemoize.doubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleToIntFunction doubleToIntFunction(
            final DoubleToIntFunction function, final DoubleFunction<KEY> keyFunction) {
        return CaffeineMemoize.doubleToIntFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleToLongFunction doubleToLongFunction(
            final DoubleToLongFunction function, final DoubleFunction<KEY> keyFunction) {
        return CaffeineMemoize.doubleToLongFunction(function, keyFunction);
    }

    @Override
    protected <KEY> DoubleUnaryOperator doubleUnaryOperator(
            final DoubleUnaryOperator operator, final DoubleFunction<KEY> keyFunction) {
        return CaffeineMemoize.doubleUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> DoubleBinaryOperator doubleBinaryOperator(
            final DoubleBinaryOperator operator, final DoubleBinaryFunction<KEY> keyFunction) {
        return CaffeineMemoize.doubleBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> IntBinaryOperator intBinaryOperator(
            final IntBinaryOperator operator, final IntBinaryFunction<KEY> keyFunction) {
        return CaffeineMemoize.intBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> IntToDoubleFunction intToDoubleFunction(
            final IntToDoubleFunction function, final IntFunction<KEY> keyFunction) {
        return CaffeineMemoize.intToDoubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> IntToLongFunction intToLongFunction(
            final IntToLongFunction function, final IntFunction<KEY> keyFunction) {
        return CaffeineMemoize.intToLongFunction(function, keyFunction);
    }

    @Override
    protected <KEY> IntUnaryOperator intUnaryOperator(
            final IntUnaryOperator operator, final IntFunction<KEY> keyFunction) {
        return CaffeineMemoize.intUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> LongBinaryOperator longBinaryOperator(
            final LongBinaryOperator operator, final LongBinaryFunction<KEY> keyFunction) {
        return CaffeineMemoize.longBinaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY> LongToDoubleFunction longToDoubleFunction(
            final LongToDoubleFunction function, final LongFunction<KEY> keyFunction) {
        return CaffeineMemoize.longToDoubleFunction(function, keyFunction);
    }

    @Override
    protected <KEY> LongToIntFunction longToIntFunction(
            final LongToIntFunction function, final LongFunction<KEY> keyFunction) {
        return CaffeineMemoize.longToIntFunction(function, keyFunction);
    }

    @Override
    protected <KEY> LongUnaryOperator longUnaryOperator(
            final LongUnaryOperator operator, final LongFunction<KEY> keyFunction) {
        return CaffeineMemoize.longUnaryOperator(operator, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier, final Supplier<KEY> keySupplier) {
        return CaffeineMemoize.supplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> BooleanSupplier booleanSupplier(final BooleanSupplier supplier, final Supplier<KEY> keySupplier) {
        return CaffeineMemoize.booleanSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> DoubleSupplier doubleSupplier(final DoubleSupplier supplier, final Supplier<KEY> keySupplier) {
        return CaffeineMemoize.doubleSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> IntSupplier intSupplier(final IntSupplier supplier, final Supplier<KEY> keySupplier) {
        return CaffeineMemoize.intSupplier(supplier, keySupplier);
    }

    @Override
    protected <KEY> LongSupplier longSupplier(final LongSupplier supplier, final Supplier<KEY> keySupplier) {
        return CaffeineMemoize.longSupplier(supplier, keySupplier);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return CaffeineMemoize.toDoubleBiFunction(function, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return CaffeineMemoize.toIntBiFunction(function, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return CaffeineMemoize.toLongBiFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            final ToDoubleFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.toDoubleFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            final ToIntFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.toIntFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            final ToLongFunction<INPUT> function, final Function<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.toLongFunction(function, keyFunction);
    }

    @Override
    protected <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate, final Function<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.predicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> LongPredicate longPredicate(final LongPredicate predicate, final LongFunction<KEY> keyFunction) {
        return CaffeineMemoize.longPredicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> IntPredicate intPredicate(final IntPredicate predicate, final IntFunction<KEY> keyFunction) {
        return CaffeineMemoize.intPredicate(predicate, keyFunction);
    }

    @Override
    protected <KEY> DoublePredicate doublePredicate(
            final DoublePredicate predicate, final DoubleFunction<KEY> keyFunction) {
        return CaffeineMemoize.doublePredicate(predicate, keyFunction);
    }

    @Override
    protected <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer, final Function<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.consumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> DoubleConsumer doubleConsumer(
            final DoubleConsumer consumer, final DoubleFunction<KEY> keyFunction) {
        return CaffeineMemoize.doubleConsumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> IntConsumer intConsumer(final IntConsumer consumer, final IntFunction<KEY> keyFunction) {
        return CaffeineMemoize.intConsumer(consumer, keyFunction);
    }

    @Override
    protected <KEY> LongConsumer longConsumer(final LongConsumer consumer, final LongFunction<KEY> keyFunction) {
        return CaffeineMemoize.longConsumer(consumer, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return CaffeineMemoize.biPredicate(predicate, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return CaffeineMemoize.biFunction(biFunction, keyFunction);
    }

    @Override
    protected <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer, final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return CaffeineMemoize.biConsumer(biConsumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            final ObjDoubleConsumer<INPUT> consumer, final ObjDoubleFunction<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.objDoubleConsumer(consumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            final ObjIntConsumer<INPUT> consumer, final ObjIntFunction<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.objIntConsumer(consumer, keyFunction);
    }

    @Override
    protected <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            final ObjLongConsumer<INPUT> consumer, final ObjLongFunction<INPUT, KEY> keyFunction) {
        return CaffeineMemoize.objLongConsumer(consumer, keyFunction);
    }

}
