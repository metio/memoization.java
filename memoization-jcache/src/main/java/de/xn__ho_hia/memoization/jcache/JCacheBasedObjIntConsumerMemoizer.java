/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.ObjIntConsumer;

import javax.cache.Cache;

import de.xn__ho_hia.memoization.shared.ObjIntFunction;

final class JCacheBasedObjIntConsumerMemoizer<FIRST, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, KEY>
        implements ObjIntConsumer<FIRST> {

    private final ObjIntFunction<FIRST, KEY> keyFunction;
    private final ObjIntConsumer<FIRST>      biConsumer;

    JCacheBasedObjIntConsumerMemoizer(
            final Cache<KEY, KEY> cache,
            final ObjIntFunction<FIRST, KEY> keyFunction,
            final ObjIntConsumer<FIRST> biConsumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biConsumer = biConsumer;
    }

    @Override
    public void accept(final FIRST first, final int value) {
        final KEY key = keyFunction.apply(first, value);
        invoke(key, givenKey -> {
            biConsumer.accept(first, value);
            return givenKey;
        });
    }

}
