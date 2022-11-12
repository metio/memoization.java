/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import java.util.function.*;

import static wtf.metio.memoization.tck.TestSupport.*;

public abstract class UsingCustomKeyFunctionTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.apply("something");
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
    final void shouldMemoizeIntFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.apply(123);
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
    final void shouldMemoizeLongFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongFunction<String> function = Mockito.mock(LongFunction.class);
        Mockito.when(function.apply(123L)).thenReturn("value");
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(123L)).thenReturn("key");

        // when
        final LongFunction<String> memoize = longFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply(123L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(123L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(123L);
    }

    protected abstract <KEY, OUTPUT> LongFunction<OUTPUT> longFunction(
            LongFunction<OUTPUT> function,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleFunction<String> function = Mockito.mock(DoubleFunction.class);
        Mockito.when(function.apply(1.0D)).thenReturn("value");
        final DoubleFunction<String> keyFunction = Mockito.mock(DoubleFunction.class);
        Mockito.when(keyFunction.apply(1.0D)).thenReturn("key");

        // when
        final DoubleFunction<String> memoize = doubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D);
    }

    protected abstract <KEY, OUTPUT> DoubleFunction<OUTPUT> doubleFunction(
            DoubleFunction<OUTPUT> function,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleToIntFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleToIntFunction function = Mockito.mock(DoubleToIntFunction.class);
        Mockito.when(function.applyAsInt(1.0D)).thenReturn(123);
        final DoubleFunction<String> keyFunction = Mockito.mock(DoubleFunction.class);
        Mockito.when(keyFunction.apply(1.0D)).thenReturn("key");

        // when
        final DoubleToIntFunction memoize = doubleToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D);
    }

    protected abstract <KEY> DoubleToIntFunction doubleToIntFunction(
            DoubleToIntFunction function,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleToLongFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleToLongFunction function = Mockito.mock(DoubleToLongFunction.class);
        Mockito.when(function.applyAsLong(1.0D)).thenReturn(123L);
        final DoubleFunction<String> keyFunction = Mockito.mock(DoubleFunction.class);
        Mockito.when(keyFunction.apply(1.0D)).thenReturn("key");

        // when
        final DoubleToLongFunction memoize = doubleToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D);
    }

    protected abstract <KEY> DoubleToLongFunction doubleToLongFunction(
            DoubleToLongFunction function,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleUnaryOperatorWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleUnaryOperator function = Mockito.mock(DoubleUnaryOperator.class);
        Mockito.when(function.applyAsDouble(1.0D)).thenReturn(2.0D);
        final DoubleFunction<String> keyFunction = Mockito.mock(DoubleFunction.class);
        Mockito.when(keyFunction.apply(1.0D)).thenReturn("key");

        // when
        final DoubleUnaryOperator memoize = doubleUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D);
    }

    protected abstract <KEY> DoubleUnaryOperator doubleUnaryOperator(
            DoubleUnaryOperator operator,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoubleBinaryOperatorWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleBinaryOperator function = Mockito.mock(DoubleBinaryOperator.class);
        Mockito.when(function.applyAsDouble(1.0D, 2.0D)).thenReturn(3.0D);
        final DoubleBinaryFunction<String> keyFunction = Mockito.mock(DoubleBinaryFunction.class);
        Mockito.when(keyFunction.apply(1.0D, 2.0D)).thenReturn("key");

        // when
        final DoubleBinaryOperator memoize = doubleBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1.0D, 2.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1.0D, 2.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D, 2.0D);
    }

    protected abstract <KEY> DoubleBinaryOperator doubleBinaryOperator(
            DoubleBinaryOperator operator,
            DoubleBinaryFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntBinaryOperatorWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntBinaryOperator function = Mockito.mock(IntBinaryOperator.class);
        Mockito.when(function.applyAsInt(1, 2)).thenReturn(3);
        final IntBinaryFunction<String> keyFunction = Mockito.mock(IntBinaryFunction.class);
        Mockito.when(keyFunction.apply(1, 2)).thenReturn("key");

        // when
        final IntBinaryOperator memoize = intBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1, 2);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1, 2);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1, 2);
    }

    protected abstract <KEY> IntBinaryOperator intBinaryOperator(
            IntBinaryOperator operator,
            IntBinaryFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntToDoubleFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntToDoubleFunction function = Mockito.mock(IntToDoubleFunction.class);
        Mockito.when(function.applyAsDouble(1)).thenReturn(3.0D);
        final IntFunction<String> keyFunction = Mockito.mock(IntFunction.class);
        Mockito.when(keyFunction.apply(1)).thenReturn("key");

        // when
        final IntToDoubleFunction memoize = intToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1);
    }

    protected abstract <KEY> IntToDoubleFunction intToDoubleFunction(
            IntToDoubleFunction function,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntToLongFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntToLongFunction function = Mockito.mock(IntToLongFunction.class);
        Mockito.when(function.applyAsLong(1)).thenReturn(3L);
        final IntFunction<String> keyFunction = Mockito.mock(IntFunction.class);
        Mockito.when(keyFunction.apply(1)).thenReturn("key");

        // when
        final IntToLongFunction memoize = intToLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1);
    }

    protected abstract <KEY> IntToLongFunction intToLongFunction(
            IntToLongFunction function,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntUnaryOperatorWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntUnaryOperator function = Mockito.mock(IntUnaryOperator.class);
        Mockito.when(function.applyAsInt(1)).thenReturn(3);
        final IntFunction<String> keyFunction = Mockito.mock(IntFunction.class);
        Mockito.when(keyFunction.apply(1)).thenReturn("key");

        // when
        final IntUnaryOperator memoize = intUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1);
    }

    protected abstract <KEY> IntUnaryOperator intUnaryOperator(
            IntUnaryOperator operator,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongBinaryOperatorWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongBinaryOperator function = Mockito.mock(LongBinaryOperator.class);
        Mockito.when(function.applyAsLong(1L, 2L)).thenReturn(3L);
        final LongBinaryFunction<String> keyFunction = Mockito.mock(LongBinaryFunction.class);
        Mockito.when(keyFunction.apply(1L, 2L)).thenReturn("key");

        // when
        final LongBinaryOperator memoize = longBinaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1L, 2L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2L);
    }

    protected abstract <KEY> LongBinaryOperator longBinaryOperator(
            LongBinaryOperator operator,
            LongBinaryFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongToDoubleFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongToDoubleFunction function = Mockito.mock(LongToDoubleFunction.class);
        Mockito.when(function.applyAsDouble(1L)).thenReturn(3.0D);
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(1L)).thenReturn("key");

        // when
        final LongToDoubleFunction memoize = longToDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L);
    }

    protected abstract <KEY> LongToDoubleFunction longToDoubleFunction(
            LongToDoubleFunction function,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongToIntFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongToIntFunction function = Mockito.mock(LongToIntFunction.class);
        Mockito.when(function.applyAsInt(1L)).thenReturn(2);
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(1L)).thenReturn("key");

        // when
        final LongToIntFunction memoize = longToIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L);
    }

    protected abstract <KEY> LongToIntFunction longToIntFunction(
            LongToIntFunction function,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongUnaryOperatorWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongUnaryOperator function = Mockito.mock(LongUnaryOperator.class);
        Mockito.when(function.applyAsLong(1L)).thenReturn(2L);
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(1L)).thenReturn("key");

        // when
        final LongUnaryOperator memoize = longUnaryOperator(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L);
    }

    protected abstract <KEY> LongUnaryOperator longUnaryOperator(
            LongUnaryOperator operator,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeCallableWithKeyFunction() throws Exception {
        // given
        final Callable<String> callable = Mockito.mock(Callable.class);
        Mockito.when(callable.call()).thenReturn("test");
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Callable<String> memoize = callable(callable, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.call();
            } catch (Exception e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(callable).call();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY, OUTPUT> Callable<OUTPUT> callable(
            Callable<OUTPUT> callable, Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeRunnableWithKeyFunction() throws Exception {
        // given
        final Runnable runnable = Mockito.mock(Runnable.class);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Runnable memoize = runnable(runnable, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.run();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(runnable).run();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> Runnable runnable(Runnable runnable, Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeSupplierWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.get();
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
    final void shouldMemoizeBooleanSupplierWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.getAsBoolean();
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
    final void shouldMemoizeDoubleSupplierWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleSupplier supplier = Mockito.mock(DoubleSupplier.class);
        Mockito.when(supplier.getAsDouble()).thenReturn(1.0D);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final DoubleSupplier memoize = doubleSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsDouble();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsDouble();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> DoubleSupplier doubleSupplier(
            DoubleSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeIntSupplierWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntSupplier supplier = Mockito.mock(IntSupplier.class);
        Mockito.when(supplier.getAsInt()).thenReturn(1);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final IntSupplier memoize = intSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsInt();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsInt();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> IntSupplier intSupplier(
            IntSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeLongSupplierWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongSupplier supplier = Mockito.mock(LongSupplier.class);
        Mockito.when(supplier.getAsLong()).thenReturn(1L);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final LongSupplier memoize = longSupplier(supplier, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsLong();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsLong();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> LongSupplier longSupplier(
            LongSupplier supplier,
            Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeToDoubleBiFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ToDoubleBiFunction<Double, Double> function = Mockito.mock(ToDoubleBiFunction.class);
        Mockito.when(function.applyAsDouble(1.0D, 0.0D)).thenReturn(2.0D);
        final BiFunction<Double, Double, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1.0D, 0.0D)).thenReturn("key");

        // when
        final ToDoubleBiFunction<Double, Double> memoize = toDoubleBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1.0D, 0.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1.0D, 0.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D, 0.0D);
    }

    protected abstract <FIRST, SECOND, KEY> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            ToDoubleBiFunction<FIRST, SECOND> function,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeToIntBiFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ToIntBiFunction<Integer, Integer> function = Mockito.mock(ToIntBiFunction.class);
        Mockito.when(function.applyAsInt(1, 0)).thenReturn(1);
        final BiFunction<Integer, Integer, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1, 0)).thenReturn("key");

        // when
        final ToIntBiFunction<Integer, Integer> memoize = toIntBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1, 0);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1, 0);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1, 0);
    }

    protected abstract <FIRST, SECOND, KEY> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            ToIntBiFunction<FIRST, SECOND> function,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeToLongBiFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ToLongBiFunction<Long, Long> function = Mockito.mock(ToLongBiFunction.class);
        Mockito.when(function.applyAsLong(1L, 0L)).thenReturn(1L);
        final BiFunction<Long, Long, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1L, 0L)).thenReturn("key");

        // when
        final ToLongBiFunction<Long, Long> memoize = toLongBiFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1L, 0L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1L, 0L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 0L);
    }

    protected abstract <FIRST, SECOND, KEY> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            ToLongBiFunction<FIRST, SECOND> function,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeToDoubleFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ToDoubleFunction<String> function = Mockito.mock(ToDoubleFunction.class);
        Mockito.when(function.applyAsDouble("something")).thenReturn(1.0D);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final ToDoubleFunction<String> memoize = toDoubleFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble("something");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble("something");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY> ToDoubleFunction<INPUT> toDoubleFunction(
            ToDoubleFunction<INPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeToIntFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ToIntFunction<String> function = Mockito.mock(ToIntFunction.class);
        Mockito.when(function.applyAsInt("something")).thenReturn(1);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final ToIntFunction<String> memoize = toIntFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt("something");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt("something");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY> ToIntFunction<INPUT> toIntFunction(
            ToIntFunction<INPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeToLongFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ToLongFunction<String> function = Mockito.mock(ToLongFunction.class);
        Mockito.when(function.applyAsLong("something")).thenReturn(1L);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final ToLongFunction<String> memoize = toLongFunction(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong("something");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong("something");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY> ToLongFunction<INPUT> toLongFunction(
            ToLongFunction<INPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizePredicateWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.test("something");
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
    final void shouldMemoizeLongPredicateWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongPredicate predicate = Mockito.mock(LongPredicate.class);
        Mockito.when(predicate.test(1L)).thenReturn(true);
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(1L)).thenReturn("key");

        // when
        final LongPredicate memoize = longPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(1L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L);
    }

    protected abstract <KEY> LongPredicate longPredicate(
            LongPredicate predicate,
            LongFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntPredicateWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntPredicate predicate = Mockito.mock(IntPredicate.class);
        Mockito.when(predicate.test(1)).thenReturn(true);
        final IntFunction<String> keyFunction = Mockito.mock(IntFunction.class);
        Mockito.when(keyFunction.apply(1)).thenReturn("key");

        // when
        final IntPredicate memoize = intPredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(1);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1);
    }

    protected abstract <KEY> IntPredicate intPredicate(
            IntPredicate predicate,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeDoublePredicateWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoublePredicate predicate = Mockito.mock(DoublePredicate.class);
        Mockito.when(predicate.test(0.0D)).thenReturn(true);
        final DoubleFunction<String> keyFunction = Mockito.mock(DoubleFunction.class);
        Mockito.when(keyFunction.apply(0.0D)).thenReturn("key");

        // when
        final DoublePredicate memoize = doublePredicate(predicate, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(0.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(0.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(0.0D);
    }

    protected abstract <KEY> DoublePredicate doublePredicate(
            DoublePredicate predicate,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer<String> consumer = Mockito.mock(Consumer.class);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final Consumer<String> memoize = consumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("something");
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
    final void shouldMemoizeDoubleConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);
        final DoubleFunction<String> keyFunction = Mockito.mock(DoubleFunction.class);
        Mockito.when(keyFunction.apply(1.0D)).thenReturn("key");

        // when
        final DoubleConsumer memoize = doubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1.0D);
    }

    protected abstract <KEY> DoubleConsumer doubleConsumer(
            DoubleConsumer consumer,
            DoubleFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeIntConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final IntConsumer consumer = Mockito.mock(IntConsumer.class);
        final IntFunction<String> keyFunction = Mockito.mock(IntFunction.class);
        Mockito.when(keyFunction.apply(1)).thenReturn("key");

        // when
        final IntConsumer memoize = intConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1);
    }

    protected abstract <KEY> IntConsumer intConsumer(
            IntConsumer consumer,
            IntFunction<KEY> keyFunction);

    @Test
    final void shouldMemoizeLongConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);
        final LongFunction<String> keyFunction = Mockito.mock(LongFunction.class);
        Mockito.when(keyFunction.apply(1L)).thenReturn("key");

        // when
        final LongConsumer memoize = longConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L);
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
    final void shouldMemoizeBiPredicateWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.test(1L, 2L);
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
    final void shouldMemoizeBiFunctionWithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.apply(1L, 2L);
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
    final void shouldMemoizeBiConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final BiConsumer<Long, Long> consumer = Mockito.mock(BiConsumer.class);
        final BiFunction<Long, Long, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply(1L, 2L)).thenReturn("key");

        // when
        final BiConsumer<Long, Long> memoize = biConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2L);
    }

    protected abstract <FIRST, SECOND, KEY> BiConsumer<FIRST, SECOND> biConsumer(
            BiConsumer<FIRST, SECOND> biConsumer,
            BiFunction<FIRST, SECOND, KEY> keyFunction);

    @Test
    final void shouldMemoizeObjDoubleConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ObjDoubleConsumer<Long> consumer = Mockito.mock(ObjDoubleConsumer.class);
        final ObjDoubleFunction<Long, String> keyFunction = Mockito.mock(ObjDoubleFunction.class);
        Mockito.when(keyFunction.apply(1L, 2.0D)).thenReturn("key");

        // when
        final ObjDoubleConsumer<Long> memoize = objDoubleConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2.0D);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2.0D);
    }

    protected abstract <INPUT, KEY> ObjDoubleConsumer<INPUT> objDoubleConsumer(
            ObjDoubleConsumer<INPUT> consumer,
            ObjDoubleFunction<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeObjIntConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ObjIntConsumer<Long> consumer = Mockito.mock(ObjIntConsumer.class);
        final ObjIntFunction<Long, String> keyFunction = Mockito.mock(ObjIntFunction.class);
        Mockito.when(keyFunction.apply(1L, 2)).thenReturn("key");

        // when
        final ObjIntConsumer<Long> memoize = objIntConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 2);
    }

    protected abstract <INPUT, KEY> ObjIntConsumer<INPUT> objIntConsumer(
            ObjIntConsumer<INPUT> consumer,
            ObjIntFunction<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeObjLongConsumerWithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final ObjLongConsumer<Long> consumer = Mockito.mock(ObjLongConsumer.class);
        final ObjLongFunction<Long, String> keyFunction = Mockito.mock(ObjLongFunction.class);
        Mockito.when(keyFunction.apply(1L, 0L)).thenReturn("key");

        // when
        final ObjLongConsumer<Long> memoize = objLongConsumer(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 0L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 0L);
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply(1L, 0L);
    }

    protected abstract <INPUT, KEY> ObjLongConsumer<INPUT> objLongConsumer(
            ObjLongConsumer<INPUT> consumer,
            ObjLongFunction<INPUT, KEY> keyFunction);

}
