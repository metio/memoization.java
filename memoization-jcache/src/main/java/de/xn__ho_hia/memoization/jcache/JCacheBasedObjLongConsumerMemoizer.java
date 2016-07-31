/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.ObjLongConsumer;

import javax.cache.Cache;

import de.xn__ho_hia.memoization.shared.ObjLongFunction;

final class JCacheBasedObjLongConsumerMemoizer<FIRST, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, KEY>
        implements ObjLongConsumer<FIRST> {

    private final ObjLongFunction<FIRST, KEY> keyFunction;
    private final ObjLongConsumer<FIRST>      biConsumer;

    JCacheBasedObjLongConsumerMemoizer(
            final Cache<KEY, KEY> cache,
            final ObjLongFunction<FIRST, KEY> keyFunction,
            final ObjLongConsumer<FIRST> biConsumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biConsumer = biConsumer;
    }

    @Override
    public void accept(final FIRST first, final long value) {
        final KEY key = keyFunction.apply(first, value);
        invoke(key, givenKey -> {
            biConsumer.accept(first, value);
            return givenKey;
        });
    }

}
