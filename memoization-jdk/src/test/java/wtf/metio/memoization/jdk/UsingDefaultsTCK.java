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

abstract class UsingDefaultsTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeFunction() throws InterruptedException, TimeoutException {
        // given
        final Function<String, String> function = Mockito.mock(Function.class);
        Mockito.when(function.apply("something")).thenReturn("test");

        // when
        final Function<String, String> memoize = function(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("something");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("something");
    }

    protected abstract <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(Function<INPUT, OUTPUT> function);

    @Test
    final void shouldMemoizeIntFunction() throws InterruptedException, TimeoutException {
        // given
        final IntFunction<String> function = Mockito.mock(IntFunction.class);
        Mockito.when(function.apply(1)).thenReturn("test");

        // when
        final IntFunction<String> memoize = intFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1);
    }

    protected abstract <OUTPUT> IntFunction<OUTPUT> intFunction(IntFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeLongFunction() throws InterruptedException, TimeoutException {
        // given
        final LongFunction<String> function = Mockito.mock(LongFunction.class);
        Mockito.when(function.apply(1L)).thenReturn("test");

        // when
        final LongFunction<String> memoize = longFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1L);
    }

    protected abstract <OUTPUT> LongFunction<OUTPUT> longFunction(LongFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeDoubleFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleFunction<String> function = Mockito.mock(DoubleFunction.class);
        Mockito.when(function.apply(1.0D)).thenReturn("test");

        // when
        final DoubleFunction<String> memoize = doubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1.0D);
    }

    protected abstract <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(DoubleFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeDoubleToIntFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleToIntFunction function = Mockito.mock(DoubleToIntFunction.class);

        // when
        final DoubleToIntFunction memoize = doubleToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1.0D);
    }

    protected abstract DoubleToIntFunction doubleToIntFunction(DoubleToIntFunction function);

    @Test
    final void shouldMemoizeDoubleToLongFunction() throws InterruptedException, TimeoutException {
        // given
        final DoubleToLongFunction function = Mockito.mock(DoubleToLongFunction.class);

        // when
        final DoubleToLongFunction memoize = doubleToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1.0D);
    }

    protected abstract DoubleToLongFunction doubleToLongFunction(DoubleToLongFunction function);

    @Test
    final void shouldMemoizeDoubleUnaryOperator() throws InterruptedException, TimeoutException {
        // given
        final DoubleUnaryOperator function = Mockito.mock(DoubleUnaryOperator.class);

        // when
        final DoubleUnaryOperator memoize = doubleUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1.0D);
    }

    protected abstract DoubleUnaryOperator doubleUnaryOperator(DoubleUnaryOperator operator);

    @Test
    final void shouldMemoizeDoubleBinaryOperator() throws InterruptedException, TimeoutException {
        // given
        final DoubleBinaryOperator function = Mockito.mock(DoubleBinaryOperator.class);

        // when
        final DoubleBinaryOperator memoize = doubleBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1.0D, 2.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1.0D, 2.0D);
    }

    protected abstract DoubleBinaryOperator doubleBinaryOperator(DoubleBinaryOperator operator);

    @Test
    final void shouldMemoizeIntBinaryOperator() throws InterruptedException, TimeoutException {
        // given
        final IntBinaryOperator function = Mockito.mock(IntBinaryOperator.class);

        // when
        final IntBinaryOperator memoize = intBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1, 2);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1, 2);
    }

    protected abstract IntBinaryOperator intBinaryOperator(IntBinaryOperator operator);

    @Test
    final void shouldMemoizeIntToDoubleFunction() throws InterruptedException, TimeoutException {
        // given
        final IntToDoubleFunction function = Mockito.mock(IntToDoubleFunction.class);

        // when
        final IntToDoubleFunction memoize = intToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1);
    }

    protected abstract IntToDoubleFunction intToDoubleFunction(IntToDoubleFunction function);

    @Test
    final void shouldMemoizeIntToLongFunction() throws InterruptedException, TimeoutException {
        // given
        final IntToLongFunction function = Mockito.mock(IntToLongFunction.class);

        // when
        final IntToLongFunction memoize = intToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1);
    }

    protected abstract IntToLongFunction intToLongFunction(IntToLongFunction function);

    @Test
    final void shouldMemoizeIntUnaryOperator() throws InterruptedException, TimeoutException {
        // given
        final IntUnaryOperator function = Mockito.mock(IntUnaryOperator.class);

        // when
        final IntUnaryOperator memoize = intUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1);
    }

    protected abstract IntUnaryOperator intUnaryOperator(IntUnaryOperator operator);

    @Test
    final void shouldMemoizeLongBinaryOperator() throws InterruptedException, TimeoutException {
        // given
        final LongBinaryOperator function = Mockito.mock(LongBinaryOperator.class);

        // when
        final LongBinaryOperator memoize = longBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1L, 2L);
    }

    protected abstract LongBinaryOperator longBinaryOperator(LongBinaryOperator operator);

    @Test
    final void shouldMemoizeLongToDoubleFunction() throws InterruptedException, TimeoutException {
        // given
        final LongToDoubleFunction function = Mockito.mock(LongToDoubleFunction.class);

        // when
        final LongToDoubleFunction memoize = longToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1L);
    }

    protected abstract LongToDoubleFunction longToDoubleFunction(LongToDoubleFunction function);

    @Test
    final void shouldMemoizeLongToIntFunction() throws InterruptedException, TimeoutException {
        // given
        final LongToIntFunction function = Mockito.mock(LongToIntFunction.class);

        // when
        final LongToIntFunction memoize = longToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1L);
    }

    protected abstract LongToIntFunction longToIntFunction(LongToIntFunction function);

    @Test
    final void shouldMemoizeLongUnaryOperator() throws InterruptedException, TimeoutException {
        // given
        final LongUnaryOperator function = Mockito.mock(LongUnaryOperator.class);

        // when
        final LongUnaryOperator memoize = longUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1L);
    }

    protected abstract LongUnaryOperator longUnaryOperator(LongUnaryOperator operator);

    @Test
    final void shouldMemoizeCallable() throws Exception {
        // given
        final Callable<String> callable = Mockito.mock(Callable.class);
        Mockito.when(callable.call()).thenReturn("test");

        // when
        final Callable<String> memoize = callable(callable);

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
    }

    protected abstract <OUTPUT> Callable<OUTPUT> callable(Callable<OUTPUT> callable);

    @Test
    final void shouldMemoizeRunnable() throws Exception {
        // given
        final Runnable runnable = Mockito.mock(Runnable.class);

        // when
        final Runnable memoize = runnable(runnable);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.run();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(runnable).run();
    }

    protected abstract Runnable runnable(Runnable runnable);

    @Test
    final void shouldMemoizeSupplier() throws InterruptedException, TimeoutException {
        // given
        final Supplier<String> supplier = Mockito.mock(Supplier.class);
        Mockito.when(supplier.get()).thenReturn("test");

        // when
        final Supplier<String> memoize = supplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.get();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).get();
    }

    protected abstract <OUTPUT> Supplier<OUTPUT> supplier(Supplier<OUTPUT> supplier);

    @Test
    final void shouldMemoizeBooleanSupplier() throws InterruptedException, TimeoutException {
        // given
        final BooleanSupplier supplier = Mockito.mock(BooleanSupplier.class);

        // when
        final BooleanSupplier memoize = booleanSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsBoolean();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsBoolean();
    }

    protected abstract BooleanSupplier booleanSupplier(BooleanSupplier supplier);

    @Test
    final void shouldMemoizeDoubleSupplier() throws InterruptedException, TimeoutException {
        // given
        final DoubleSupplier supplier = Mockito.mock(DoubleSupplier.class);

        // when
        final DoubleSupplier memoize = doubleSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsDouble();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsDouble();
    }

    protected abstract DoubleSupplier doubleSupplier(DoubleSupplier supplier);

    @Test
    final void shouldMemoizeIntSupplier() throws InterruptedException, TimeoutException {
        // given
        final IntSupplier supplier = Mockito.mock(IntSupplier.class);

        // when
        final IntSupplier memoize = intSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsInt();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsInt();
    }

    protected abstract IntSupplier intSupplier(IntSupplier supplier);

    @Test
    final void shouldMemoizeLongSupplier() throws InterruptedException, TimeoutException {
        // given
        final LongSupplier supplier = Mockito.mock(LongSupplier.class);

        // when
        final LongSupplier memoize = longSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.getAsLong();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(supplier).getAsLong();
    }

    protected abstract LongSupplier longSupplier(LongSupplier supplier);

    @Test
    final void shouldMemoizeToDoubleBiFunction() throws InterruptedException, TimeoutException {
        // given
        final ToDoubleBiFunction<Double, Double> function = Mockito.mock(ToDoubleBiFunction.class);

        // when
        final ToDoubleBiFunction<Double, Double> memoize = toDoubleBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble(1.0D, 2.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble(1.0D, 2.0D);
    }

    protected abstract <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            ToDoubleBiFunction<FIRST, SECOND> function);

    @Test
    final void shouldMemoizeToIntBiFunction() throws InterruptedException, TimeoutException {
        // given
        final ToIntBiFunction<Integer, Integer> function = Mockito.mock(ToIntBiFunction.class);

        // when
        final ToIntBiFunction<Integer, Integer> memoize = toIntBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt(1, 2);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt(1, 2);
    }

    protected abstract <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            ToIntBiFunction<FIRST, SECOND> function);

    @Test
    final void shouldMemoizeToLongBiFunction() throws InterruptedException, TimeoutException {
        // given
        final ToLongBiFunction<Long, Long> function = Mockito.mock(ToLongBiFunction.class);

        // when
        final ToLongBiFunction<Long, Long> memoize = toLongBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong(1L, 2L);
    }

    protected abstract <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            ToLongBiFunction<FIRST, SECOND> function);

    @Test
    final void shouldMemoizeToDoubleFunction() throws InterruptedException, TimeoutException {
        // given
        final ToDoubleFunction<String> function = Mockito.mock(ToDoubleFunction.class);

        // when
        final ToDoubleFunction<String> memoize = toDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsDouble("1.0");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsDouble("1.0");
    }

    protected abstract <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(ToDoubleFunction<INPUT> function);

    @Test
    final void shouldMemoizeToIntFunction() throws InterruptedException, TimeoutException {
        // given
        final ToIntFunction<String> function = Mockito.mock(ToIntFunction.class);

        // when
        final ToIntFunction<String> memoize = toIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsInt("1");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsInt("1");
    }

    protected abstract <INPUT> ToIntFunction<INPUT> toIntFunction(ToIntFunction<INPUT> function);

    @Test
    final void shouldMemoizeToLongFunction() throws InterruptedException, TimeoutException {
        // given
        final ToLongFunction<String> function = Mockito.mock(ToLongFunction.class);

        // when
        final ToLongFunction<String> memoize = toLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.applyAsLong("1");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).applyAsLong("1");
    }

    protected abstract <INPUT> ToLongFunction<INPUT> toLongFunction(ToLongFunction<INPUT> function);

    @Test
    final void shouldMemoizePredicate() throws InterruptedException, TimeoutException {
        // given
        final Predicate<String> predicate = Mockito.mock(Predicate.class);

        // when
        final Predicate<String> memoize = predicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test("test");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test("test");
    }

    protected abstract <INPUT> Predicate<INPUT> predicate(Predicate<INPUT> predicate);

    @Test
    final void shouldMemoizeLongPredicate() throws InterruptedException, TimeoutException {
        // given
        final LongPredicate predicate = Mockito.mock(LongPredicate.class);

        // when
        final LongPredicate memoize = longPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(1L);
    }

    protected abstract LongPredicate longPredicate(LongPredicate predicate);

    @Test
    final void shouldMemoizeIntPredicate() throws InterruptedException, TimeoutException {
        // given
        final IntPredicate predicate = Mockito.mock(IntPredicate.class);

        // when
        final IntPredicate memoize = intPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(1);
    }

    protected abstract IntPredicate intPredicate(IntPredicate predicate);

    @Test
    final void shouldMemoizeDoublePredicate() throws InterruptedException, TimeoutException {
        // given
        final DoublePredicate predicate = Mockito.mock(DoublePredicate.class);

        // when
        final DoublePredicate memoize = doublePredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(predicate).test(1.0D);
    }

    protected abstract DoublePredicate doublePredicate(DoublePredicate predicate);

    @Test
    final void shouldMemoizeConsumer() throws InterruptedException, TimeoutException {
        // given
        final Consumer<String> consumer = Mockito.mock(Consumer.class);

        // when
        final Consumer<String> memoize = consumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("value");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("value");
    }

    protected abstract <INPUT> Consumer<INPUT> consumer(Consumer<INPUT> consumer);

    @Test
    final void shouldMemoizeDoubleConsumer() throws InterruptedException, TimeoutException {
        // given
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);

        // when
        final DoubleConsumer memoize = doubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1.0D);
    }

    protected abstract DoubleConsumer doubleConsumer(DoubleConsumer consumer);

    @Test
    final void shouldMemoizeIntConsumer() throws InterruptedException, TimeoutException {
        // given
        final IntConsumer consumer = Mockito.mock(IntConsumer.class);

        // when
        final IntConsumer memoize = intConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1);
    }

    protected abstract IntConsumer intConsumer(IntConsumer consumer);

    @Test
    final void shouldMemoizeLongConsumer() throws InterruptedException, TimeoutException {
        // given
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);

        // when
        final LongConsumer memoize = longConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L);
    }

    protected abstract LongConsumer longConsumer(LongConsumer consumer);

    @Test
    final void shouldMemoizeBiPredicate() throws InterruptedException, TimeoutException {
        // given
        final BiPredicate<Long, Long> biPredicate = Mockito.mock(BiPredicate.class);

        // when
        final BiPredicate<Long, Long> memoize = biPredicate(biPredicate);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.test(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(biPredicate).test(1L, 2L);
    }

    protected abstract <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(BiPredicate<FIRST, SECOND> predicate);

    @Test
    final void shouldMemoizeBiFunction() throws InterruptedException, TimeoutException {
        // given
        final BiFunction<Long, Long, String> function = Mockito.mock(BiFunction.class);
        Mockito.when(function.apply(1L, 2L)).thenReturn("test");

        // when
        final BiFunction<Long, Long, String> memoize = biFunction(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply(1L, 2L);
    }

    protected abstract <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            BiFunction<FIRST, SECOND, OUTPUT> biFunction);

    @Test
    final void shouldMemoizeBiConsumer() throws InterruptedException, TimeoutException {
        // given
        final BiConsumer<Long, Long> consumer = Mockito.mock(BiConsumer.class);

        // when
        final BiConsumer<Long, Long> memoize = biConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2L);
    }

    protected abstract <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            BiConsumer<FIRST, SECOND> biConsumer);

    @Test
    final void shouldMemoizeObjDoubleConsumer() throws InterruptedException, TimeoutException {
        // given
        final ObjDoubleConsumer<Long> consumer = Mockito.mock(ObjDoubleConsumer.class);

        // when
        final ObjDoubleConsumer<Long> memoize = objDoubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2.0D);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2.0D);
    }

    protected abstract <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(ObjDoubleConsumer<INPUT> consumer);

    @Test
    final void shouldMemoizeObjIntConsumer() throws InterruptedException, TimeoutException {
        // given
        final ObjIntConsumer<Long> consumer = Mockito.mock(ObjIntConsumer.class);

        // when
        final ObjIntConsumer<Long> memoize = objIntConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2);
    }

    protected abstract <INPUT> ObjIntConsumer<INPUT> objIntConsumer(ObjIntConsumer<INPUT> consumer);

    @Test
    final void shouldMemoizeObjLongConsumer() throws InterruptedException, TimeoutException {
        // given
        final ObjLongConsumer<Long> consumer = Mockito.mock(ObjLongConsumer.class);

        // when
        final ObjLongConsumer<Long> memoize = objLongConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept(1L, 2L);
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept(1L, 2L);
    }

    protected abstract <INPUT> ObjLongConsumer<INPUT> objLongConsumer(ObjLongConsumer<INPUT> consumer);

}
