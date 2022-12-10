/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.*;

class MemoizeRxUsingDefaultsTest extends UsingDefaultsTCK {

    @Override
    protected final Action action(final Action action) {
        return MemoizeRx.action(action);
    }

    @Override
    protected final Cancellable cancellable(final Cancellable cancellable) {
        return MemoizeRx.cancellable(cancellable);
    }

    @Override
    protected final BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return MemoizeRx.booleanSupplier(supplier);
    }

    @Override
    protected final LongConsumer longConsumer(final LongConsumer consumer) {
        return MemoizeRx.longConsumer(consumer);
    }

    @Override
    protected <TYPE1, OUTPUT> Function<TYPE1, OUTPUT> function(final Function<TYPE1, OUTPUT> function) {
        return MemoizeRx.function(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function) {
        return MemoizeRx.function3(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function) {
        return MemoizeRx.function4(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function) {
        return MemoizeRx.function5(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function) {
        return MemoizeRx.function6(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function) {
        return MemoizeRx.function7(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function) {
        return MemoizeRx.function8(function);
    }

    @Override
    protected final <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function) {
        return MemoizeRx.function9(function);
    }

    @Override
    protected final <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return MemoizeRx.predicate(predicate);
    }

    @Override
    protected final <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(
            final BiPredicate<FIRST, SECOND> predicate) {
        return MemoizeRx.biPredicate(predicate);
    }

    @Override
    protected final <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return MemoizeRx.biFunction(biFunction);
    }

    @Override
    protected final <VALUE> Consumer<VALUE> consumer(final Consumer<VALUE> consumer) {
        return MemoizeRx.consumer(consumer);
    }

    @Override
    protected final <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            final BiConsumer<FIRST, SECOND> biConsumer) {
        return MemoizeRx.biConsumer(biConsumer);
    }

    @Override
    protected final <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return MemoizeRx.intFunction(function);
    }

    @Override
    protected final <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return MemoizeRx.supplier(supplier);
    }

}
