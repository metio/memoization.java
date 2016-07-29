/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;

import javax.cache.Cache;

final class JCacheBasedIntConsumerMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Integer>
        implements IntConsumer {

    private final IntFunction<KEY> keyFunction;
    private final IntConsumer      consumer;

    JCacheBasedIntConsumerMemoizer(
            final Cache<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntConsumer consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final int input) {
        final KEY key = keyFunction.apply(input);
        invoke(key, givenKey -> {
            consumer.accept(input);
            return Integer.valueOf(input);
        });
    }

}
