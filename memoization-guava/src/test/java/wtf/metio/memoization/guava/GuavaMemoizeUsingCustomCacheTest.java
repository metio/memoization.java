/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.CacheBuilder;
import wtf.metio.memoization.tck.UsingDefaultsTCK;

import java.util.function.*;

class GuavaMemoizeUsingCustomCacheTest extends UsingDefaultsTCK {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return GuavaMemoize.function(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return GuavaMemoize.intFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return GuavaMemoize.longFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return GuavaMemoize.doubleFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return GuavaMemoize.doubleToIntFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return GuavaMemoize.doubleToLongFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return GuavaMemoize.doubleUnaryOperator(operator, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return GuavaMemoize.doubleBinaryOperator(operator, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return GuavaMemoize.intBinaryOperator(operator, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return GuavaMemoize.intToDoubleFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return GuavaMemoize.intToLongFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return GuavaMemoize.intUnaryOperator(operator, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return GuavaMemoize.longBinaryOperator(operator, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return GuavaMemoize.longToDoubleFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return GuavaMemoize.longToIntFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return GuavaMemoize.longUnaryOperator(operator, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return GuavaMemoize.supplier(supplier, CacheBuilder.newBuilder().build());
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return GuavaMemoize.booleanSupplier(supplier, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return GuavaMemoize.doubleSupplier(supplier, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return GuavaMemoize.intSupplier(supplier, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return GuavaMemoize.longSupplier(supplier, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(final ToDoubleBiFunction<FIRST, SECOND> function) {
        return GuavaMemoize.toDoubleBiFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(final ToIntBiFunction<FIRST, SECOND> function) {
        return GuavaMemoize.toIntBiFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(final ToLongBiFunction<FIRST, SECOND> function) {
        return GuavaMemoize.toLongBiFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return GuavaMemoize.toDoubleFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return GuavaMemoize.toIntFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return GuavaMemoize.toLongFunction(function, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return GuavaMemoize.predicate(predicate, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return GuavaMemoize.longPredicate(predicate, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return GuavaMemoize.intPredicate(predicate, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return GuavaMemoize.doublePredicate(predicate, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return GuavaMemoize.consumer(consumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return GuavaMemoize.doubleConsumer(consumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return GuavaMemoize.intConsumer(consumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return GuavaMemoize.longConsumer(consumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return GuavaMemoize.biPredicate(predicate, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return GuavaMemoize.biFunction(biFunction, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return GuavaMemoize.biConsumer(biConsumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return GuavaMemoize.objDoubleConsumer(consumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return GuavaMemoize.objIntConsumer(consumer, CacheBuilder.newBuilder().build());
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return GuavaMemoize.objLongConsumer(consumer, CacheBuilder.newBuilder().build());
    }

}
