/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import wtf.metio.memoization.tck.UsingDefaultsTCK;

import java.util.function.*;

class CaffeineUsingCustomCacheTest extends UsingDefaultsTCK {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return CaffeineMemoize.function(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return CaffeineMemoize.intFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return CaffeineMemoize.longFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return CaffeineMemoize.doubleFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return CaffeineMemoize.doubleToIntFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return CaffeineMemoize.doubleToLongFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return CaffeineMemoize.doubleUnaryOperator(operator, Caffeine.newBuilder().build());
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return CaffeineMemoize.doubleBinaryOperator(operator, Caffeine.newBuilder().build());
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return CaffeineMemoize.intBinaryOperator(operator, Caffeine.newBuilder().build());
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return CaffeineMemoize.intToDoubleFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return CaffeineMemoize.intToLongFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return CaffeineMemoize.intUnaryOperator(operator, Caffeine.newBuilder().build());
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return CaffeineMemoize.longBinaryOperator(operator, Caffeine.newBuilder().build());
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return CaffeineMemoize.longToDoubleFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return CaffeineMemoize.longToIntFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return CaffeineMemoize.longUnaryOperator(operator, Caffeine.newBuilder().build());
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return CaffeineMemoize.supplier(supplier, Caffeine.newBuilder().build());
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return CaffeineMemoize.booleanSupplier(supplier, Caffeine.newBuilder().build());
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return CaffeineMemoize.doubleSupplier(supplier, Caffeine.newBuilder().build());
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return CaffeineMemoize.intSupplier(supplier, Caffeine.newBuilder().build());
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return CaffeineMemoize.longSupplier(supplier, Caffeine.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        return CaffeineMemoize.toDoubleBiFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            final ToIntBiFunction<FIRST, SECOND> function) {
        return CaffeineMemoize.toIntBiFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            final ToLongBiFunction<FIRST, SECOND> function) {
        return CaffeineMemoize.toLongBiFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return CaffeineMemoize.toDoubleFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return CaffeineMemoize.toIntFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return CaffeineMemoize.toLongFunction(function, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return CaffeineMemoize.predicate(predicate, Caffeine.newBuilder().build());
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return CaffeineMemoize.longPredicate(predicate, Caffeine.newBuilder().build());
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return CaffeineMemoize.intPredicate(predicate, Caffeine.newBuilder().build());
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return CaffeineMemoize.doublePredicate(predicate, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return CaffeineMemoize.consumer(consumer, Caffeine.newBuilder().build());
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return CaffeineMemoize.doubleConsumer(consumer, Caffeine.newBuilder().build());
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return CaffeineMemoize.intConsumer(consumer, Caffeine.newBuilder().build());
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return CaffeineMemoize.longConsumer(consumer, Caffeine.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return CaffeineMemoize.biPredicate(predicate, Caffeine.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return CaffeineMemoize.biFunction(biFunction, Caffeine.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return CaffeineMemoize.biConsumer(biConsumer, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return CaffeineMemoize.objDoubleConsumer(consumer, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return CaffeineMemoize.objIntConsumer(consumer, Caffeine.newBuilder().build());
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return CaffeineMemoize.objLongConsumer(consumer, Caffeine.newBuilder().build());
    }

}
