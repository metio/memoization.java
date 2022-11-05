/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wtf.metio.memoization.core.ObjLongFunction;

import java.util.function.ObjLongConsumer;

class GuavaCacheBasedObjLongConsumerMemoizerTest {

    @Test
    void shouldAcceptCacheAndKeyFunctionAndBiConsumer() {
        // given
        final ObjLongConsumer<String> biConsumer = (first, second) -> System.out.println(first + second);
        final ObjLongFunction<String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedObjLongConsumerMemoizer<>(cache, keyFunction, biConsumer);

        // then
        Assertions.assertNotNull(memoizer);
    }

    @Test
    void shouldAcceptInput() {
        // given
        final ObjLongConsumer<String> biConsumer = Mockito.mock(ObjLongConsumer.class);
        final ObjLongFunction<String, String> keyFunction = (first, second) -> second + first;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final var memoizer = new GuavaCacheBasedObjLongConsumerMemoizer<>(cache, keyFunction, biConsumer);

        // then
        memoizer.accept("first", 123);
        Mockito.verify(biConsumer).accept("first", 123);
    }

}
