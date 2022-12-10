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
    protected final <KEY> Action action(final Action action, final Supplier<KEY> keySupplier) {
        return MemoizeRx.action(action, keySupplier, cache());
    }

    @Override
    protected final <KEY> Cancellable cancellable(final Cancellable cancellable, final Supplier<KEY> keySupplier) {
        return MemoizeRx.cancellable(cancellable, keySupplier, cache());
    }

    @Override
    protected final <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            final Function<INPUT, OUTPUT> function,
            final Function<INPUT, KEY> keyFunction) {
        return MemoizeRx.function(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, KEY, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return MemoizeRx.function3(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, KEY, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return MemoizeRx.function4(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return MemoizeRx.function5(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return MemoizeRx.function6(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return MemoizeRx.function7(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return MemoizeRx.function8(function, keyFunction, cache());
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction) {
        return MemoizeRx.function9(function, keyFunction, cache());
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
