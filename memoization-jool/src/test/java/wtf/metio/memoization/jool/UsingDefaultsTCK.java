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

import static wtf.metio.memoization.tck.TestSupport.*;

public abstract class UsingDefaultsTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeConsumer0() throws InterruptedException, TimeoutException {
        // given
        final Consumer0 consumer = Mockito.mock(Consumer0.class);

        // when
        final var memoize = consumer0(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept();
    }

    protected abstract Consumer0 consumer0(Consumer0 consumer);

    @Test
    final void shouldMemoizeConsumer1() throws InterruptedException, TimeoutException {
        // given
        final Consumer1<String> consumer = Mockito.mock(Consumer1.class);

        // when
        final var memoize = consumer1(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1");
    }

    protected abstract <TYPE1> Consumer1<TYPE1> consumer1(Consumer1<TYPE1> consumer);

    @Test
    final void shouldMemoizeConsumer2() throws InterruptedException, TimeoutException {
        // given
        final Consumer2<String, String> consumer = Mockito.mock(Consumer2.class);

        // when
        final var memoize = consumer2(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2");
    }

    protected abstract <TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(Consumer2<TYPE1, TYPE2> consumer);

    @Test
    final void shouldMemoizeConsumer3() throws InterruptedException, TimeoutException {
        // given
        final Consumer3<String, String, String> consumer = Mockito.mock(Consumer3.class);

        // when
        final var memoize = consumer3(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3");
    }

    protected abstract <TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(Consumer3<TYPE1, TYPE2, TYPE3> consumer);

    @Test
    final void shouldMemoizeConsumer4() throws InterruptedException, TimeoutException {
        // given
        final Consumer4<String, String, String, String> consumer = Mockito.mock(Consumer4.class);

        // when
        final var memoize = consumer4(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(
            Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer);

    @Test
    final void shouldMemoizeConsumer5() throws InterruptedException, TimeoutException {
        // given
        final Consumer5<String, String, String, String, String> consumer = Mockito.mock(Consumer5.class);

        // when
        final var memoize = consumer5(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(
            Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer);

    @Test
    final void shouldMemoizeConsumer6() throws InterruptedException, TimeoutException {
        // given
        final Consumer6<String, String, String, String, String, String> consumer = Mockito.mock(Consumer6.class);

        // when
        final var memoize = consumer6(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(
            Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer);

    @Test
    final void shouldMemoizeConsumer7() throws InterruptedException, TimeoutException {
        // given
        final Consumer7<String, String, String, String, String, String, String> consumer = Mockito.mock(Consumer7.class);

        // when
        final var memoize = consumer7(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(
            Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer);

    @Test
    final void shouldMemoizeConsumer8() throws InterruptedException, TimeoutException {
        // given
        final Consumer8<String, String, String, String, String, String, String, String> consumer = Mockito.mock(Consumer8.class);

        // when
        final var memoize = consumer8(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(
            Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer);

    @Test
    final void shouldMemoizeConsumer9() throws InterruptedException, TimeoutException {
        // given
        final Consumer9<String, String, String, String, String, String, String, String, String> consumer = Mockito.mock(Consumer9.class);

        // when
        final var memoize = consumer9(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(
            Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer);

    @Test
    final void shouldMemoizeConsumer10() throws InterruptedException, TimeoutException {
        // given
        final Consumer10<String, String, String, String, String, String, String, String, String, String> consumer = Mockito.mock(Consumer10.class);

        // when
        final var memoize = consumer10(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(
            Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer);

    @Test
    final void shouldMemoizeConsumer11() throws InterruptedException, TimeoutException {
        // given
        final Consumer11<String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer11.class);

        // when
        final var memoize = consumer11(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(
            Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer);

    @Test
    final void shouldMemoizeConsumer12() throws InterruptedException, TimeoutException {
        // given
        final Consumer12<String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer12.class);

        // when
        final var memoize = consumer12(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(
            Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer);

    @Test
    final void shouldMemoizeConsumer13() throws InterruptedException, TimeoutException {
        // given
        final Consumer13<String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer13.class);

        // when
        final var memoize = consumer13(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(
            Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer);

    @Test
    final void shouldMemoizeConsumer14() throws InterruptedException, TimeoutException {
        // given
        final Consumer14<String, String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer14.class);

        // when
        final var memoize = consumer14(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(
            Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer);

    @Test
    final void shouldMemoizeConsumer15() throws InterruptedException, TimeoutException {
        // given
        final Consumer15<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer15.class);

        // when
        final var memoize = consumer15(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(
            Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer);

    @Test
    final void shouldMemoizeConsumer16() throws InterruptedException, TimeoutException {
        // given
        final Consumer16<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> consumer =
                Mockito.mock(Consumer16.class);

        // when
        final var memoize = consumer16(consumer);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(consumer).accept("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(
            Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer);

    @Test
    final void shouldMemoizeFunction0() throws InterruptedException, TimeoutException {
        // given
        final Function0<String> function = Mockito.mock(Function0.class);
        Mockito.when(function.get()).thenReturn("value");

        // when
        final var memoize = function0(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.get();
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).get();
    }

    protected abstract <OUTPUT> Function0<OUTPUT> function0(Function0<OUTPUT> function);

    @Test
    final void shouldMemoizeFunction1() throws InterruptedException, TimeoutException {
        // given
        final Function1<String, String> function = Mockito.mock(Function1.class);
        Mockito.when(function.apply("something")).thenReturn("test");

        // when
        final var memoize = function1(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("something");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("something");
    }

    protected abstract <INPUT, OUTPUT> Function1<INPUT, OUTPUT> function1(Function1<INPUT, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction2() throws InterruptedException, TimeoutException {
        // given
        final Function2<String, String, String> function = Mockito.mock(Function2.class);
        Mockito.when(function.apply("something", "else")).thenReturn("test");

        // when
        final var memoize = function2(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("something", "else");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("something", "else");
    }

    protected abstract <TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(Function2<TYPE1, TYPE2, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction3() throws InterruptedException, TimeoutException {
        // given
        final Function3<String, String, String, String> function = Mockito.mock(Function3.class);
        Mockito.when(function.apply("1", "2", "3")).thenReturn("test");

        // when
        final var memoize = function3(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction4() throws InterruptedException, TimeoutException {
        // given
        final Function4<String, String, String, String, String> function = Mockito.mock(Function4.class);
        Mockito.when(function.apply("1", "2", "3", "4")).thenReturn("test");

        // when
        final var memoize = function4(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction5() throws InterruptedException, TimeoutException {
        // given
        final Function5<String, String, String, String, String, String> function = Mockito.mock(Function5.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5")).thenReturn("test");

        // when
        final var memoize = function5(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction6() throws InterruptedException, TimeoutException {
        // given
        final Function6<String, String, String, String, String, String, String> function = Mockito.mock(Function6.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6")).thenReturn("test");

        // when
        final var memoize = function6(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction7() throws InterruptedException, TimeoutException {
        // given
        final Function7<String, String, String, String, String, String, String, String> function = Mockito.mock(Function7.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("test");

        // when
        final var memoize = function7(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction8() throws InterruptedException, TimeoutException {
        // given
        final Function8<String, String, String, String, String, String, String, String, String> function = Mockito.mock(Function8.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("test");

        // when
        final var memoize = function8(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction9() throws InterruptedException, TimeoutException {
        // given
        final Function9<String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function9.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).thenReturn("test");

        // when
        final var memoize = function9(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(
            Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction10() throws InterruptedException, TimeoutException {
        // given
        final Function10<String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function10.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")).thenReturn("test");

        // when
        final var memoize = function10(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(
            Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction11() throws InterruptedException, TimeoutException {
        // given
        final Function11<String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function11.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")).thenReturn("test");

        // when
        final var memoize = function11(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(
            Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction12() throws InterruptedException, TimeoutException {
        // given
        final Function12<String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function12.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")).thenReturn("test");

        // when
        final var memoize = function12(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(
            Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction13() throws InterruptedException, TimeoutException {
        // given
        final Function13<String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function13.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")).thenReturn("test");

        // when
        final var memoize = function13(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(
            Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction14() throws InterruptedException, TimeoutException {
        // given
        final Function14<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function14.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14")).thenReturn("test");

        // when
        final var memoize = function14(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(
            Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction15() throws InterruptedException, TimeoutException {
        // given
        final Function15<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function15.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")).thenReturn("test");

        // when
        final var memoize = function15(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(
            Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function);

    @Test
    final void shouldMemoizeFunction16() throws InterruptedException, TimeoutException {
        // given
        final Function16<String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String> function =
                Mockito.mock(Function16.class);
        Mockito.when(function.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")).thenReturn("test");

        // when
        final var memoize = function16(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            memoize.apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).apply("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(
            Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function);

}
