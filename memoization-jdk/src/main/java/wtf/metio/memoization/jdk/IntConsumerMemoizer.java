/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

import static java.util.Objects.requireNonNull;

final class IntConsumerMemoizer<KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements IntConsumer {

    private final IntFunction<KEY> keyFunction;
    private final IntConsumer consumer;

    IntConsumerMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntConsumer consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final int value) {
        computeIfAbsent(keyFunction.apply(value), key -> {
            consumer.accept(value);
            return value;
        });
    }

}
