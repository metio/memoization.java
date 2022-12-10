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
    final void shouldMemoizeActionWithKeyFunction() throws Throwable {
        // given
        final Action action = Mockito.mock(Action.class);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Action memoize = action(action, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.run();
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(action).run();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> Action action(Action action, Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeCancellableWithKeyFunction() throws Throwable {
        // given
        final Cancellable cancellable = Mockito.mock(Cancellable.class);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Cancellable memoize = cancellable(cancellable, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.cancel();
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(cancellable).cancel();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> Cancellable cancellable(Cancellable cancellable, Supplier<KEY> keySupplier);

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
    final void shouldMemoizeFunction3WithKeyFunction() throws Throwable {
        // given
        final Function3<String, String, String, String> function = Mockito.mock(Function3.class);
        Mockito.when(function.apply("1", "2", "3")).thenReturn("value");
        final Function3<String, String, String, String> keyFunction = Mockito.mock(Function3.class);
        Mockito.when(keyFunction.apply("1", "2", "3")).thenReturn("key");

        // when
        final Function3<String, String, String, String> memoize = function3(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, KEY, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction4WithKeyFunction() throws Throwable {
        // given
        final Function4<String, String, String, String, String> function = Mockito.mock(Function4.class);
        Mockito.when(function.apply("1", "2", "3", "4")).thenReturn("value");
        final Function4<String, String, String, String, String> keyFunction = Mockito.mock(Function4.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4")).thenReturn("key");

        // when
        final Function4<String, String, String, String, String> memoize = function4(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3", "4");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, KEY, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction5WithKeyFunction() throws Throwable {
        // given
        final Function5<String, String, String, String, String, String> function = Mockito.mock(Function5.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5")).thenReturn("value");
        final Function5<String, String, String, String, String, String> keyFunction = Mockito.mock(Function5.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5")).thenReturn("key");

        // when
        final Function5<String, String, String, String, String, String> memoize = function5(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3", "4", "5");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction6WithKeyFunction() throws Throwable {
        // given
        final Function6<String, String, String, String, String, String, String> function = Mockito.mock(Function6.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6")).thenReturn("value");
        final Function6<String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function6.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6")).thenReturn("key");

        // when
        final Function6<String, String, String, String, String, String, String> memoize = function6(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3", "4", "5", "6");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction7WithKeyFunction() throws Throwable {
        // given
        final Function7<String, String, String, String, String, String, String, String> function = Mockito.mock(Function7.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("value");
        final Function7<String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function7.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("key");

        // when
        final Function7<String, String, String, String, String, String, String, String> memoize = function7(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3", "4", "5", "6", "7");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction8WithKeyFunction() throws Throwable {
        // given
        final Function8<String, String, String, String, String, String, String, String, String> function = Mockito.mock(Function8.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("value");
        final Function8<String, String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function8.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("key");

        // when
        final Function8<String, String, String, String, String, String, String, String, String> memoize = function8(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3", "4", "5", "6", "7", "8");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction9WithKeyFunction() throws Throwable {
        // given
        final Function9<String, String, String, String, String, String, String, String, String, String> function = Mockito.mock(Function9.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("value");
        final Function9<String, String, String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function9.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("key");

        // when
        final Function9<String, String, String, String, String, String, String, String, String, String> memoize = function9(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function,
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction);

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
