/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntConsumerMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements IntConsumer {

    private final IntFunction<KEY> keyFunction;
    private final IntConsumer consumer;

    ConcurrentMapBasedIntConsumerMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final int value) {
        final KEY key = keyFunction.apply(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return value;
        });
    }

}
