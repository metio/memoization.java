/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

final class GuavaCacheBasedDoubleConsumerMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements DoubleConsumer {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleConsumer consumer;

    GuavaCacheBasedDoubleConsumerMemoizer(
            final Cache<KEY, Double> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final double value) {
        final KEY key = keyFunction.apply(value);
        get(key, givenKey -> {
            consumer.accept(value);
            return value;
        });
    }

}
