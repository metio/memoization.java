/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;

final class GuavaCacheBasedIntConsumerMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements IntConsumer {

    private final IntFunction<KEY> keyFunction;
    private final IntConsumer consumer;

    GuavaCacheBasedIntConsumerMemoizer(
            final Cache<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final int value) {
        final KEY key = keyFunction.apply(value);
        get(key, givenKey -> {
            consumer.accept(value);
            return value;
        });
    }

}
