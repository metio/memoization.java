/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;

class ConcurrentMapBasedIntFunctionMemoizerTest {

    @Test
    void shouldAcceptCacheAndFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final IntFunction<String> function = input -> "output";
        final IntFunction<String> keyFunction = input -> "key";

        // when
        final var memoizer = new ConcurrentMapBasedIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    
    void shouldRequireNonNullCache() {
        // given
        final ConcurrentMap<String, String> cache = null;
        final IntFunction<String> function = input -> "output";
        final IntFunction<String> keyFunction = input -> "key";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    
    void shouldRequireNonNullFunction() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final IntFunction<String> function = null;
        final IntFunction<String> keyFunction = input -> "key";

        // when
        // then
        Assertions.assertThrows(NullPointerException.class,
                () -> new ConcurrentMapBasedIntFunctionMemoizer<>(cache, keyFunction, function));
    }

    @Test
    void shouldMemoizeFunctionCall() {
        // given
        final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();
        final IntFunction<String> function = input -> "output";
        final IntFunction<String> keyFunction = input -> "key";

        // when
        final var memoizer = new ConcurrentMapBasedIntFunctionMemoizer<>(cache, keyFunction, function);

        // then
        Assertions.assertEquals("output", memoizer.apply(123));
    }

}
