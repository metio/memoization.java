/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.LongConsumer;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;

import static java.util.Objects.requireNonNull;

final class LongConsumerMemoizer<KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements LongConsumer {

    private final LongFunction<KEY> keyFunction;
    private final LongConsumer consumer;

    LongConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final LongFunction<KEY> keyFunction,
            final LongConsumer consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL LongConsumer - provide an actual LongConsumer to fix this.");
    }

    @Override
    public void accept(final long value) throws Throwable {
        try {
            computeIfAbsent(keyFunction.apply(value), key -> {
                try {
                    consumer.accept(value);
                    return key;
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
