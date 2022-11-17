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

import java.util.concurrent.TimeoutException;

import static wtf.metio.memoization.tck.TestSupport.*;

abstract class UsingDefaultsTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeAction() throws Throwable {
        // given
        final Action action = Mockito.mock(Action.class);

        // when
        final var memoize = action(action);

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
    }

    protected abstract Action action(Action action);

    @Test
    final void shouldMemoizeCancellable() throws Throwable {
        // given
        final Cancellable cancellable = Mockito.mock(Cancellable.class);

        // when
        final var memoize = cancellable(cancellable);

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
    }

    protected abstract Cancellable cancellable(Cancellable cancellable);

    @Test
    final void shouldMemoizeBooleanSupplier() throws Throwable {
        // given
        final BooleanSupplier supplier = Mockito.mock(BooleanSupplier.class);

        // when
        final BooleanSupplier memoize = booleanSupplier(supplier);

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
    }

    protected abstract BooleanSupplier booleanSupplier(BooleanSupplier supplier);

    @Test
    final void shouldMemoizeLongConsumer() throws Throwable {
        // given
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);

        // when
        final LongConsumer memoize = longConsumer(consumer);

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
    }

    protected abstract LongConsumer longConsumer(LongConsumer consumer);

    @Test
    final void shouldMemoizeFunction() throws Throwable {
        // given
        final Function<String, String> function = Mockito.mock(Function.class);
        Mockito.when(function.apply("1")).thenReturn("test");

        // when
        final var memoize = function(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply("1");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1");
    }

    protected abstract <TYPE1, OUTPUT> Function<TYPE1, OUTPUT> function(Function<TYPE1, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction3() throws Throwable {
        // given
        final Function3<String, String, String, String> function = Mockito.mock(Function3.class);
        Mockito.when(function.apply("1", "2", "3")).thenReturn("test");

        // when
        final var memoize = function3(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction4() throws Throwable {
        // given
        final Function4<String, String, String, String, String> function = Mockito.mock(Function4.class);
        Mockito.when(function.apply("1", "2", "3", "4")).thenReturn("test");

        // when
        final var memoize = function4(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction5() throws Throwable {
        // given
        final Function5<String, String, String, String, String, String> function = Mockito.mock(Function5.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5")).thenReturn("test");

        // when
        final var memoize = function5(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction6() throws Throwable {
        // given
        final Function6<String, String, String, String, String, String, String> function = Mockito.mock(Function6.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6")).thenReturn("test");

        // when
        final var memoize = function6(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction7() throws Throwable {
        // given
        final Function7<String, String, String, String, String, String, String, String> function = Mockito.mock(Function7.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("test");

        // when
        final var memoize = function7(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction8() throws Throwable {
        // given
        final Function8<String, String, String, String, String, String, String, String, String> function = Mockito.mock(Function8.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("test");

        // when
        final var memoize = function8(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction9() throws Throwable {
        // given
        final Function9<String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function9.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("test");

        // when
        final var memoize = function9(function);

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
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function);

    @Test
    final void shouldMemoizePredicate() throws Throwable {
        // given
        final Predicate<String> predicate = Mockito.mock(Predicate.class);

        // when
        final Predicate<String> memoize = predicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.test("test");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test("test");
    }

    protected abstract <INPUT> Predicate<INPUT> predicate(Predicate<INPUT> predicate);

    @Test
    final void shouldMemoizeBiPredicate() throws Throwable {
        // given
        final BiPredicate<Long, Long> biPredicate = Mockito.mock(BiPredicate.class);

        // when
        final BiPredicate<Long, Long> memoize = biPredicate(biPredicate);

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
        Mockito.verify(biPredicate).test(1L, 2L);
    }

    protected abstract <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(BiPredicate<FIRST, SECOND> predicate);

    @Test
    final void shouldMemoizeBiFunction() throws Throwable {
        // given
        final BiFunction<Long, Long, String> function = Mockito.mock(BiFunction.class);
        Mockito.when(function.apply(1L, 2L)).thenReturn("test");

        // when
        final BiFunction<Long, Long, String> memoize = biFunction(function);

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
    }

    protected abstract <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            BiFunction<FIRST, SECOND, OUTPUT> biFunction);

    @Test
    final void shouldMemoizeBiConsumer() throws Throwable {
        // given
        final BiConsumer<Long, Long> consumer = Mockito.mock(BiConsumer.class);

        // when
        final BiConsumer<Long, Long> memoize = biConsumer(consumer);

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
    }

    protected abstract <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            BiConsumer<FIRST, SECOND> biConsumer);

    @Test
    final void shouldMemoizeIntFunction() throws Throwable {
        // given
        final IntFunction<String> function = Mockito.mock(IntFunction.class);
        Mockito.when(function.apply(1)).thenReturn("test");

        // when
        final IntFunction<String> memoize = intFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.apply(1);
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1);
    }

    protected abstract <OUTPUT> IntFunction<OUTPUT> intFunction(IntFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeSupplier() throws Throwable {
        // given
        final Supplier<String> supplier = Mockito.mock(Supplier.class);
        Mockito.when(supplier.get()).thenReturn("test");

        // when
        final Supplier<String> memoize = supplier(supplier);

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
    }

    protected abstract <OUTPUT> Supplier<OUTPUT> supplier(Supplier<OUTPUT> supplier);

}
