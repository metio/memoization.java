/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoubleConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoubleConsumerMemoizer
        extends ConcurrentHashMapBasedMemoizer<Double, Double>
        implements DoubleConsumer {

    private final DoubleConsumer consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedDoubleConsumerMemoizer(
            final Map<Double, Double> preComputedValues,
            final DoubleConsumer consumer) {
        super(preComputedValues);
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final double value) {
        final Double key = Double.valueOf(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return givenKey;
        });
    }

}
