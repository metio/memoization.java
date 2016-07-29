/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedLongConsumerMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongConsumer {

    private final LongFunction<KEY> keyFunction;
    private final LongConsumer      consumer;

    GuavaCacheBasedLongConsumerMemoizer(
            final Cache<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final long value) {
        final KEY key = keyFunction.apply(value);
        get(key, givenKey -> {
            consumer.accept(value);
            return Long.valueOf(value);
        });
    }

}
