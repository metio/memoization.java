/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import javax.cache.Cache;

final class JCacheBasedBiConsumerMemoizer<FIRST, SECOND, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, KEY>
        implements BiConsumer<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiConsumer<FIRST, SECOND>      biConsumer;

    JCacheBasedBiConsumerMemoizer(
            final Cache<KEY, KEY> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiConsumer<FIRST, SECOND> biConsumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biConsumer = biConsumer;
    }

    @Override
    public void accept(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        invoke(key, givenKey -> {
            biConsumer.accept(first, second);
            return givenKey;
        });
    }

}
