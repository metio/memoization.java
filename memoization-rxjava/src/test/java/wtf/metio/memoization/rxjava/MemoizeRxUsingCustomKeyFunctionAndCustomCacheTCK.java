/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.*;

import java.util.Map;
import java.util.function.LongFunction;

abstract class MemoizeRxUsingCustomKeyFunctionAndCustomCacheTCK extends UsingCustomKeyFunctionTCK {

    protected abstract <K, V> Map<K, V> cache();

    @Override
    protected final <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return MemoizeRx.function(function, keyFunction, cache());
    }

    @Override
    protected final <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            final IntFunction<OUTPUT> function,
            final IntFunction<KEY> keyFunction) {
        return MemoizeRx.intFunction(function, keyFunction, cache());
    }

    @Override
    protected final <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            final Supplier<OUTPUT> supplier,
            final Supplier<KEY> keySupplier) {
        return MemoizeRx.supplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <KEY> BooleanSupplier booleanSupplier(
            final BooleanSupplier supplier,
            final Supplier<KEY> keySupplier) {
        return MemoizeRx.booleanSupplier(supplier, keySupplier, cache());
    }

    @Override
    protected final <INPUT, KEY> Predicate<INPUT> predicate(
            final Predicate<INPUT> predicate,
            final Function<INPUT, KEY> keyFunction) {
        return MemoizeRx.predicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <INPUT, KEY> Consumer<INPUT> consumer(
            final Consumer<INPUT> consumer,
            final Function<INPUT, KEY> keyFunction) {
        return MemoizeRx.consumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <KEY> LongConsumer longConsumer(
            final LongConsumer consumer,
            final LongFunction<KEY> keyFunction) {
        return MemoizeRx.longConsumer(consumer, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MemoizeRx.biPredicate(predicate, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MemoizeRx.biFunction(biFunction, keyFunction, cache());
    }

    @Override
    protected final <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer,
            final BiFunction<FIRST, SECOND, KEY> keyFunction) {
        return MemoizeRx.biConsumer(biConsumer, keyFunction, cache());
    }

}
