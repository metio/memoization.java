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
import java.util.function.IntFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntConsumerMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements IntConsumer {

    private final IntFunction<KEY> keyFunction;
    private final IntConsumer      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
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
            return Integer.valueOf(value);
        });
    }

}
