/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoubleConsumerMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements DoubleConsumer {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleConsumer consumer;

    ConcurrentMapBasedDoubleConsumerMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final double value) {
        final KEY key = keyFunction.apply(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return value;
        });
    }

}
