/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongConsumerMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements LongConsumer {

    private final LongFunction<KEY> keyFunction;
    private final LongConsumer consumer;

    public ConcurrentMapBasedLongConsumerMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final long value) {
        final KEY key = keyFunction.apply(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return value;
        });
    }

}
