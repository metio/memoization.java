/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

class ConcurrentMapBasedToDoubleFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, Double> cache = null;
        final ToDoubleFunction<String> function = input -> 123.456D;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble("123");
    }

    @Test
    void shouldUseSetCacheKeyAndValue() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble("123");
        Assertions.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assertions.assertEquals("123", memoizer.viewCacheForTest().keySet().iterator().next());
        Assertions.assertEquals(123.456D, memoizer.viewCacheForTest().values().iterator().next(), 0.0D);
    }

    @Test
    void shouldUseCallWrappedFunction() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = Mockito.mock(ToDoubleFunction.class);
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        memoizer.applyAsDouble("123");
        Mockito.verify(function).applyAsDouble("123");
    }

    @Test
    void shouldUseReturnFunctionResult() {
        // given
        final ConcurrentMap<String, Double> cache = new ConcurrentHashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedToDoubleFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals(123.456D, memoizer.applyAsDouble("123"), 0.0D);
    }

}
