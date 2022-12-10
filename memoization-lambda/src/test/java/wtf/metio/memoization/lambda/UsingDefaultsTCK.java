/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.*;
import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static wtf.metio.memoization.tck.TestSupport.*;

abstract class UsingDefaultsTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeFn0() throws Throwable {
        // given
        final Fn0<String> function = Mockito.mock(Fn0.class);
        Mockito.when(function.checkedApply()).thenReturn("value");

        // when
        final var memoize = function0(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply();
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply();
    }

    protected abstract <OUTPUT> Fn0<OUTPUT> function0(Fn0<OUTPUT> function);

    @Test
    final void shouldMemoizeFn1() throws Throwable {
        // given
        final Fn1<String, String> function = Mockito.mock(Fn1.class);
        Mockito.when(function.checkedApply("something")).thenReturn("test");

        // when
        final var memoize = function1(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("something");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("something");
    }

    protected abstract <INPUT, OUTPUT> Fn1<INPUT, OUTPUT> function1(Fn1<INPUT, OUTPUT> function);

    @Test
    final void shouldMemoizeFn2() throws Throwable {
        // given
        final Fn2<String, String, String> function = Mockito.mock(Fn2.class);
        Mockito.when(function.checkedApply("something", "else")).thenReturn("test");

        // when
        final var memoize = function2(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("something", "else");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("something", "else");
    }

    protected abstract <TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> function2(Fn2<TYPE1, TYPE2, OUTPUT> function);

    @Test
    final void shouldMemoizeFn3() throws Throwable {
        // given
        final Fn3<String, String, String, String> function = Mockito.mock(Fn3.class);
        Mockito.when(function.checkedApply("1", "2", "3")).thenReturn("test");

        // when
        final var memoize = function3(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("1", "2", "3");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("1", "2", "3");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function);

    @Test
    final void shouldMemoizeFn4() throws Throwable {
        // given
        final Fn4<String, String, String, String, String> function = Mockito.mock(Fn4.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4")).thenReturn("test");

        // when
        final var memoize = function4(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("1", "2", "3", "4");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("1", "2", "3", "4");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function);

    @Test
    final void shouldMemoizeFn5() throws Throwable {
        // given
        final Fn5<String, String, String, String, String, String> function = Mockito.mock(Fn5.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5")).thenReturn("test");

        // when
        final var memoize = function5(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("1", "2", "3", "4", "5");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("1", "2", "3", "4", "5");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function);

    @Test
    final void shouldMemoizeFn6() throws Throwable {
        // given
        final Fn6<String, String, String, String, String, String, String> function = Mockito.mock(Fn6.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5", "6")).thenReturn("test");

        // when
        final var memoize = function6(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("1", "2", "3", "4", "5", "6");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("1", "2", "3", "4", "5", "6");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function);

    @Test
    final void shouldMemoizeFn7() throws Throwable {
        // given
        final Fn7<String, String, String, String, String, String, String, String> function = Mockito.mock(Fn7.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5", "6", "7")).thenReturn("test");

        // when
        final var memoize = function7(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("1", "2", "3", "4", "5", "6", "7");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function);

    @Test
    final void shouldMemoizeFn8() throws Throwable {
        // given
        final Fn8<String, String, String, String, String, String, String, String, String> function = Mockito.mock(Fn8.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("test");

        // when
        final var memoize = function8(function);

        // then
        Assertions.assertNotNull(memoize);
        threadedRun(DEFAULT_RUNS, () -> {
            try {
                memoize.checkedApply("1", "2", "3", "4", "5", "6", "7", "8");
            } catch (Throwable e) {
                waiter.fail(e);
            }
            waiter.resume();
        });
        waiter.await(DEFAULT_WAIT, DEFAULT_RUNS);
        Mockito.verify(function).checkedApply("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function);

}
