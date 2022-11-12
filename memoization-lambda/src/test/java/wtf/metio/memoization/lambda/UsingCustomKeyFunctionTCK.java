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

import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import static wtf.metio.memoization.tck.TestSupport.*;

public abstract class UsingCustomKeyFunctionTCK {

    private Waiter waiter;

    @BeforeEach
    void setUp() {
        waiter = new Waiter();
    }

    @Test
    final void shouldMemoizeFn0WithKeyFunction() throws Throwable {
        // given
        final Fn0<String> function = Mockito.mock(Fn0.class);
        Mockito.when(function.checkedApply()).thenReturn("value");
        final Supplier<String> keySupplier = Mockito.mock(Supplier.class);
        Mockito.when(keySupplier.get()).thenReturn("key");

        // when
        final Fn0<String> memoize = function0(function, keySupplier);

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
        Mockito.verify(keySupplier, Mockito.times(DEFAULT_RUNS)).get();
    }

    protected abstract <KEY, OUTPUT> Fn0<OUTPUT> function0(Fn0<OUTPUT> function, Supplier<KEY> keySupplier);

    @Test
    final void shouldMemoizeFn1WithKeyFunction() throws Throwable {
        // given
        final Fn1<String, String> function = Mockito.mock(Fn1.class);
        Mockito.when(function.checkedApply("something")).thenReturn("test");
        final Fn1<String, String> keyFunction = Mockito.mock(Fn1.class);
        Mockito.when(keyFunction.apply("something")).thenReturn("key");

        // when
        final Fn1<String, String> memoize = function1(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something");
    }

    protected abstract <INPUT, KEY, OUTPUT> Fn1<INPUT, OUTPUT> function1(
            Fn1<INPUT, OUTPUT> function,
            Fn1<INPUT, KEY> keyFunction);

    @Test
    final void shouldMemoizeFn2WithKeyFunction() throws Throwable {
        // given
        final Fn2<String, String, String> function = Mockito.mock(Fn2.class);
        Mockito.when(function.checkedApply("something", "else")).thenReturn("value");
        final Fn2<String, String, String> keyFunction = Mockito.mock(Fn2.class);
        Mockito.when(keyFunction.apply("something", "else")).thenReturn("key");

        // when
        final Fn2<String, String, String> memoize = function2(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("something", "else");
    }

    protected abstract <TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> function2(
            Fn2<TYPE1, TYPE2, OUTPUT> function,
            Fn2<TYPE1, TYPE2, OUTPUT> keyFunction);

    @Test
    final void shouldMemoizeFn3WithKeyFunction() throws Throwable {
        // given
        final Fn3<String, String, String, String> function = Mockito.mock(Fn3.class);
        Mockito.when(function.checkedApply("1", "2", "3")).thenReturn("value");
        final Fn3<String, String, String, String> keyFunction = Mockito.mock(Fn3.class);
        Mockito.when(keyFunction.apply("1", "2", "3")).thenReturn("key");

        // when
        final Fn3<String, String, String, String> memoize = function3(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(
            Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function,
            Fn3<TYPE1, TYPE2, TYPE3, KEY> keyFunction);

    @Test
    final void shouldMemoizeFn4WithKeyFunction() throws Throwable {
        // given
        final Fn4<String, String, String, String, String> function = Mockito.mock(Fn4.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4")).thenReturn("value");
        final Fn4<String, String, String, String, String> keyFunction = Mockito.mock(Fn4.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4")).thenReturn("key");

        // when
        final Fn4<String, String, String, String, String> memoize = function4(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(
            Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function,
            Fn4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction);

    @Test
    final void shouldMemoizeFn5WithKeyFunction() throws Throwable {
        // given
        final Fn5<String, String, String, String, String, String> function = Mockito.mock(Fn5.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5")).thenReturn("test");
        final Fn5<String, String, String, String, String, String> keyFunction = Mockito.mock(Fn5.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5")).thenReturn("key");

        // when
        final Fn5<String, String, String, String, String, String> memoize = function5(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(
            Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function,
            Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction);

    @Test
    final void shouldMemoizeFn6WithKeyFunction() throws Throwable {
        // given
        final Fn6<String, String, String, String, String, String, String> function = Mockito.mock(Fn6.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5", "6")).thenReturn("value");
        final Fn6<String, String, String, String, String, String, String> keyFunction = Mockito.mock(Fn6.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6")).thenReturn("key");

        // when
        final Fn6<String, String, String, String, String, String, String> memoize = function6(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(
            Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function,
            Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction);

    @Test
    final void shouldMemoizeFn7WithKeyFunction() throws Throwable {
        // given
        final Fn7<String, String, String, String, String, String, String, String> function = Mockito.mock(Fn7.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5", "6", "7")).thenReturn("test");
        final Fn7<String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Fn7.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7")).thenReturn("key");

        // when
        final Fn7<String, String, String, String, String, String, String, String> memoize = function7(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(
            Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function,
            Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction);

    @Test
    final void shouldMemoizeFn8WithKeyFunction() throws Throwable {
        // given
        final Fn8<String, String, String, String, String, String, String, String, String> function = Mockito.mock(Fn8.class);
        Mockito.when(function.checkedApply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("test");
        final Fn8<String, String, String, String, String, String, String, String, String> keyFunction = Mockito.mock(Fn8.class);
        Mockito.when(keyFunction.apply("1", "2", "3", "4", "5", "6", "7", "8")).thenReturn("key");

        // when
        final Fn8<String, String, String, String, String, String, String, String, String> memoize = function8(function, keyFunction);

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
        Mockito.verify(keyFunction, Mockito.times(DEFAULT_RUNS)).apply("1", "2", "3", "4", "5", "6", "7", "8");
    }

    protected abstract <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(
            Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function,
            Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction);

}
