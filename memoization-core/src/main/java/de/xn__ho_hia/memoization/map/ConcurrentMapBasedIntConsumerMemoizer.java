/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntConsumerMemoizer
        extends ConcurrentMapBasedMemoizer<Integer, Integer>
        implements IntConsumer {

    private final IntConsumer consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedIntConsumerMemoizer(
            final ConcurrentMap<Integer, Integer> cache,
            final IntConsumer consumer) {
        super(cache);
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final int value) {
        final Integer key = Integer.valueOf(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return givenKey;
        });
    }

}
