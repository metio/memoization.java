/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Consumer;

import javax.cache.Cache;

final class JCacheBasedConsumerMemoizer<VALUE>
        extends AbstractJCacheBasedMemoizer<VALUE, VALUE>
        implements Consumer<VALUE> {

    private final Consumer<VALUE> consumer;

    JCacheBasedConsumerMemoizer(
            final Cache<VALUE, VALUE> cache,
            final Consumer<VALUE> consumer) {
        super(cache);
        this.consumer = consumer;
    }

    @Override
    public void accept(final VALUE input) {
        invoke(input, key -> {
            consumer.accept(key);
            return key;
        });
    }

}
