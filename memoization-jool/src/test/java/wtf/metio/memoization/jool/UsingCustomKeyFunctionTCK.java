/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jool;

import net.jodah.concurrentunit.Waiter;
import org.jooq.lambda.function.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static wtf.metio.memoization.tck.TestSupport.*;

public abstract class UsingCustomKeyFunctionTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeConsumer0WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer0 consumer = Mockito.mock(Consumer0.class);
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Consumer0 memoize = consumer0(consumer, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY> Consumer0 consumer0(Consumer0 consumer, Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeConsumer1WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer1<String> consumer = Mockito.mock(Consumer1.class);
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("1")).thenReturn("key");

        // when
        final var memoize = consumer1(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1");
    }

    protected abstract <KEY, TYPE1> Consumer1<TYPE1> consumer1(
            Consumer1<TYPE1> consumer,
            Function<TYPE1, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer2WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer2<String, String> consumer = Mockito.mock(Consumer2.class);
        final BiFunction<String, String, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply("1", "2")).thenReturn("key");

        // when
        final var memoize = consumer2(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2");
    }

    protected abstract <KEY, TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(
            Consumer2<TYPE1, TYPE2> consumer,
            BiFunction<TYPE1, TYPE2, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer3WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer3<String, String, String> consumer = Mockito.mock(Consumer3.class);
        final Function3<String, String, String, String> keyFunction = Mockito.mock(Function3.class);
        Mockito.when(keyFunction.apply("1", "2", "3")).thenReturn("key");

        // when
        final var memoize = consumer3(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(
            Consumer3<TYPE1, TYPE2, TYPE3> consumer,
            Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer4WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer4<String, String, String, String> consumer = Mockito.mock(Consumer4.class);
        final Function4<String, String, String, String, String> keyFunction = Mockito.mock(Function4.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4")).thenReturn("key");

        // when
        final var memoize = consumer4(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(
            Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer,
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer5WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer5<String, String, String, String, String> consumer = Mockito.mock(Consumer5.class);
        final Function5<String, String, String, String, String, String> keyFunction = Mockito.mock(Function5.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5")).thenReturn("key");

        // when
        final var memoize = consumer5(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(
            Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer,
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer6WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer6<String, String, String, String, String, String> consumer = Mockito.mock(Consumer6.class);
        final Function6<String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function6.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6")).thenReturn("key");

        // when
        final var memoize = consumer6(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(
            Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer,
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer7WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer7<String, String, String, String, String, String, String> consumer = Mockito.mock(Consumer7.class);
        final Function7<String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function7.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("key");

        // when
        final var memoize = consumer7(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(
            Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer,
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer8WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer8<String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer8.class);
        final Function8<String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function8.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("key");

        // when
        final var memoize = consumer8(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(
            Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer,
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer9WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer9<String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer9.class);
        final Function9<String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function9.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("key");

        // when
        final var memoize = consumer9(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(
            Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer,
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer10WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer10<String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer10.class);
        final Function10<String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function10.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")).thenReturn("key");

        // when
        final var memoize = consumer10(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(
            Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer,
            Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer11WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer11<String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer11.class);
        final Function11<String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function11.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")).thenReturn("key");

        // when
        final var memoize = consumer11(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(
            Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer,
            Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer12WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer12<String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer12.class);
        final Function12<String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function12.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")).thenReturn("key");

        // when
        final var memoize = consumer12(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(
            Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer,
            Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer13WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer13<String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer13.class);
        final Function13<String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function13.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")).thenReturn("key");

        // when
        final var memoize = consumer13(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(
            Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer,
            Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer14WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer14<String, String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer14.class);
        final Function14<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function14.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14")).thenReturn("key");

        // when
        final var memoize = consumer14(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(
            Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer,
            Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer15WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer15<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer15.class);
        final Function15<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function15.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")).thenReturn("key");

        // when
        final var memoize = consumer15(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(
            Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer,
            Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction);

    @Test
    final void shouldMemoizeConsumer16WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Consumer16<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer16.class);
        final Function16<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function16.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")).thenReturn("key");

        // when
        final var memoize = consumer16(consumer, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(
            Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer,
            Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction0WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function0<String> function = Mockito.mock(Function0.class);
        Mockito.when(function.get()).thenReturn("value");
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Function0<String> memoize = function0(function, keySupplier);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.get();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).get();
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY, OUTPUT> Function0<OUTPUT> function0(Function0<OUTPUT> function, Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeFunction1WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function1<String, String> function = Mockito.mock(Function1.class);
        Mockito.when(function.apply("something")).thenReturn("test");
        final Function<String, String> keyFunction = Mockito.mock(Function.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final Function1<String, String> memoize = function1(function, keyFunction);

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

    protected abstract <INPUT, KEY, OUTPUT> Function1<INPUT, OUTPUT> function1(
            Function1<INPUT, OUTPUT> function,
            Function<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction2WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function2<String, String, String> function = Mockito.mock(Function2.class);
        Mockito.when(function.apply("something", "else")).thenReturn("value");
        final BiFunction<String, String, String> keyFunction = Mockito.mock(BiFunction.class);
        Mockito.when(keyFunction.apply("something", "else")).thenReturn("key");

        // when
        final Function2<String, String, String> memoize = function2(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("something", "else");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("something", "else");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something", "else");
    }

    protected abstract <TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(
            Function2<TYPE1, TYPE2, OUTPUT> function,
            BiFunction<TYPE1, TYPE2, OUTPUT> keyFunction);

    @Test
    final void shouldMemoizeFunction3WithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.apply("1", "2", "3");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction4WithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.apply("1", "2", "3", "4");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction5WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function5<String, String, String, String, String, String> function = Mockito.mock(Function5.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5")).thenReturn("test");
        final Function5<String, String, String, String, String, String> keyFunction = Mockito.mock(Function5.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5")).thenReturn("key");

        // when
        final Function5<String, String, String, String, String, String> memoize = function5(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction6WithKeyFunction() throws InterruptedException, TimeoutException {
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
            memoize.apply("1", "2", "3", "4", "5", "6");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction7WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function7<String, String, String, String, String, String, String, String> function = Mockito.mock(Function7.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("test");
        final Function7<String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function7.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("key");

        // when
        final Function7<String, String, String, String, String, String, String, String> memoize = function7(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction8WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function8<String, String, String, String, String, String, String, String, String> function = Mockito.mock(Function8.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("test");
        final Function8<String, String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Function8.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("key");

        // when
        final Function8<String, String, String, String, String, String, String, String, String> memoize = function8(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction9WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function9<String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function9.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("test");
        final Function9<String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function9.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("key");

        // when
        final var memoize = function9(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function,
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction10WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function10<String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function10.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")).thenReturn("test");
        final Function10<String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function10.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")).thenReturn("key");

        // when
        final var memoize = function10(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(
            Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function,
            Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction11WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function11<String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function11.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")).thenReturn("test");
        final Function11<String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function11.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")).thenReturn("key");

        // when
        final var memoize = function11(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(
            Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function,
            Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction12WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function12<String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function12.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")).thenReturn("test");
        final Function12<String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function12.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")).thenReturn("key");

        // when
        final var memoize = function12(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(
            Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function,
            Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction13WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function13<String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function13.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")).thenReturn("test");
        final Function13<String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function13.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")).thenReturn("key");

        // when
        final var memoize = function13(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(
            Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function,
            Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction14WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function14<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function14.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14")).thenReturn("test");
        final Function14<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function14.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14")).thenReturn("key");

        // when
        final var memoize = function14(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(
            Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function,
            Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction15WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function15<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function15.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")).thenReturn("test");
        final Function15<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function15.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")).thenReturn("key");

        // when
        final var memoize = function15(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(
            Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function,
            Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction);

    @Test
    final void shouldMemoizeFunction16WithKeyFunction() throws InterruptedException, TimeoutException {
        // given
        final Function16<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function16.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")).thenReturn("test");
        final Function16<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> keyFunction =
                Mockito.mock(Function16.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")).thenReturn("key");

        // when
        final var memoize = function16(function, keyFunction);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(
            Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function,
            Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction);

}
