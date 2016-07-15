/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongConsumerMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Long>
        implements LongConsumer {

    private final LongConsumer consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedLongConsumerMemoizer(
            final Map<Long, Long> preComputedValues,
            final LongConsumer consumer) {
        super(preComputedValues);
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final long value) {
        final Long key = Long.valueOf(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return givenKey;
        });
    }

}
