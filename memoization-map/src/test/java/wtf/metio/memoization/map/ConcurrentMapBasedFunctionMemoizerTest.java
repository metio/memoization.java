/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

class ConcurrentMapBasedFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> function = input -> "output";
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final Function<String, String> function = input -> "output";
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> function = null;
        final Function<String, String> keyFunction = Function.identity();

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunctionCall() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final Function<String, String> function = input -> "output";
        final Function<String, String> keyFunction = Function.identity();

        // when
        final var memoizer = new ConcurrentMapBasedFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals("output", memoizer.apply("test"));
    }

}
