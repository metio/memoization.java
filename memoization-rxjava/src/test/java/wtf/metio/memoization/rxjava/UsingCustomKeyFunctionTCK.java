/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.*;
import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.LongFunction;

import static wtf.metio.memoization.tck.TestSupport.*;

abstract class UsingCustomKeyFunctionTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeFunctionWithKeyFunction() throws Throwable {
        // given
        final Function<String, String> function = Mockito.mock(Function.class);
        Mockito.when(function.apply("something")).thenReturn("value");
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final Function<String, String> memoize = function(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("something");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("something");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY, OUTPUT> Function<INPUT, OUTPUT> function(
            Function<INPUT, OUTPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeIntFunctionWithKeyFunction() throws Throwable {
        // given
        final IntFunction<String> function = Mockito.mock(IntFunction.class);
        Mockito.when(function.apply(123)).thenReturn("value");
        final IntFunction<String> keyFunction = Mockito.mock(IntFunction.class);
        Mockito.when(keyFunction.apply(123)).thenReturn("key");

        // when
        final IntFunction<String> memoize = intFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply(123);
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(123);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(123);
    }

    protected abstract <KEY, OUTPUT> IntFunction<OUTPUT> intFunction(
            IntFunction<OUTPUT> function,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeSupplierWithKeyFunction() throws Throwable {
        // given
        final Supplier<String> supplier = Mockito.mock(Supplier.class);
        Mockito.when(supplier.get()).thenReturn("value");
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Supplier<String> memoize = supplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.get();
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).get();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY, OUTPUT> Supplier<OUTPUT> supplier(
            Supplier<OUTPUT> supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeBooleanSupplierWithKeyFunction() throws Throwable {
        // given
        final BooleanSupplier supplier = Mockito.mock(BooleanSupplier.class);
        Mockito.when(supplier.getAsBoolean()).thenReturn(true);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final BooleanSupplier memoize = booleanSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.getAsBoolean();
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsBoolean();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> BooleanSupplier booleanSupplier(
            BooleanSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizePredicateWithKeyFunction() throws Throwable {
        // given
        final Predicate<String> predicate = Mockito.mock(Predicate.class);
        Mockito.when(predicate.test("something")).thenReturn(true);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final Predicate<String> memoize = predicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.test("something");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test("something");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY> Predicate<INPUT> predicate(
            Predicate<INPUT> predicate,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumerWithKeyFunction() throws Throwable {
        // given
        final Consumer<String> consumer = Mockito.mock(Consumer.class);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final Consumer<String> memoize = consumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.accept("something");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("something");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY> Consumer<INPUT> consumer(
            Consumer<INPUT> consumer,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeLongConsumerWithKeyFunction() throws Throwable {
        // given
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(1L)).thenReturn("key");

        // when
        final LongConsumer memoize = longConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.accept(1L);
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L);
    }

    protected abstract <KEY> LongConsumer longConsumer(
            LongConsumer consumer,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeBiPredicateWithKeyFunction() throws Throwable {
        // given
        final BiPredicate<Long, Long> predicate = Mockito.mock(BiPredicate.class);
        Mockito.when(predicate.test(1L, 2L)).thenReturn(true);
        final BiFunction<Long, Long, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1L, 2L)).thenReturn("key");

        // when
        final BiPredicate<Long, Long> memoize = biPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.test(1L, 2L);
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(1L, 2L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2L);
    }

    protected abstract <FIRST, SECOND, KEY> BiPredicate<FIRST, SECOND> biPredicate(
            BiPredicate<FIRST, SECOND> predicate,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeBiFunctionWithKeyFunction() throws Throwable {
        // given
        final BiFunction<Long, Long, String> function = Mockito.mock(BiFunction.class);
        Mockito.when(function.apply(1L, 2L)).thenReturn("value");
        final BiFunction<Long, Long, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1L, 2L)).thenReturn("key");

        // when
        final BiFunction<Long, Long, String> memoize = biFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply(1L, 2L);
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1L, 2L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2L);
    }

    protected abstract <FIRST, SECOND, KEY, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            BiFunction<FIRST, SECOND, OUTPUT> biFunction,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeBiConsumerWithKeyFunction() throws Throwable {
        // given
        final BiConsumer<Long, Long> consumer = Mockito.mock(BiConsumer.class);
        final BiFunction<Long, Long, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1L, 2L)).thenReturn("key");

        // when
        final BiConsumer<Long, Long> memoize = biConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.accept(1L, 2L);
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2L);
    }

    protected abstract <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            BiConsumer<FIRST, SECOND> biConsumer,
            BiFunction<FIRST, SECOND, KEY> keyFunction);


}
