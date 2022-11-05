/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.ObjLongFunction;

import java.util.function.ObjLongConsumer;

final class GuavaCacheBasedObjLongConsumerMemoizer<FIRST, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, KEY>
        implements ObjLongConsumer<FIRST> {

    private final ObjLongFunction<FIRST, KEY> keyFunction;
    private final ObjLongConsumer<FIRST> biConsumer;

    GuavaCacheBasedObjLongConsumerMemoizer(
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
        get(key, givenKey -> {
            biConsumer.accept(first, value);
            return givenKey;
        });
    }

}
