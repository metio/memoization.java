/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedLongConsumerMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements LongConsumer {

    private final LongFunction<KEY> keyFunction;
    private final LongConsumer      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
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
            return Long.valueOf(value);
        });
    }

}
