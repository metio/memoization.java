/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedDoubleConsumerMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements DoubleConsumer {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleConsumer      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
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
            return Double.valueOf(value);
        });
    }

}
