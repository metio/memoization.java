/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;

import javax.cache.Cache;

final class JCacheBasedLongConsumerMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Long>
        implements LongConsumer {

    private final LongFunction<KEY> keyFunction;
    private final LongConsumer      consumer;

    JCacheBasedLongConsumerMemoizer(
            final Cache<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final long input) {
        final KEY key = keyFunction.apply(input);
        invoke(key, givenKey -> {
            consumer.accept(input);
            return Long.valueOf(input);
        });
    }

}
