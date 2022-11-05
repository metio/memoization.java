/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;

final class GuavaCacheBasedLongConsumerMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongConsumer {

    private final LongFunction<KEY> keyFunction;
    private final LongConsumer consumer;

    GuavaCacheBasedLongConsumerMemoizer(
            final Cache<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final long value) {
        final KEY key = keyFunction.apply(value);
        get(key, givenKey -> {
            consumer.accept(value);
            return value;
        });
    }

}
