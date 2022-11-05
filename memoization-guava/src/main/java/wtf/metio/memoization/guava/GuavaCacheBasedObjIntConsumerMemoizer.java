/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.ObjIntFunction;

import java.util.function.ObjIntConsumer;

final class GuavaCacheBasedObjIntConsumerMemoizer<FIRST, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, KEY>
        implements ObjIntConsumer<FIRST> {

    private final ObjIntFunction<FIRST, KEY> keyFunction;
    private final ObjIntConsumer<FIRST> biConsumer;

    GuavaCacheBasedObjIntConsumerMemoizer(
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
        get(key, givenKey -> {
            biConsumer.accept(first, value);
            return givenKey;
        });
    }

}
