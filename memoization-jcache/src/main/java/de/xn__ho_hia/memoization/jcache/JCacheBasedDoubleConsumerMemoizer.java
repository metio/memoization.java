/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

import javax.cache.Cache;

final class JCacheBasedDoubleConsumerMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Double>
        implements DoubleConsumer {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleConsumer      consumer;

    JCacheBasedDoubleConsumerMemoizer(
            final Cache<KEY, Double> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final double input) {
        final KEY key = keyFunction.apply(input);
        invoke(key, givenKey -> {
            consumer.accept(input);
            return Double.valueOf(input);
        });
    }

}
