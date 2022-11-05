/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;

class ConcurrentMapBasedLongFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final LongFunction<String> function = input -> "output";
        final LongFunction<String> keyFunction = input -> "key";

        // when
        final var memoizer = new ConcurrentMapBasedLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final LongFunction<String> function = input -> "output";
        final LongFunction<String> keyFunction = input -> "key";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final LongFunction<String> function = null;
        final LongFunction<String> keyFunction = input -> "key";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedLongFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunctionCall() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final LongFunction<String> function = input -> "output";
        final LongFunction<String> keyFunction = input -> "key";

        // when
        final var memoizer = new ConcurrentMapBasedLongFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals("output", memoizer.apply(123));
    }

}
