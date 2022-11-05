/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.ObjDoubleFunction;

import java.util.function.ObjDoubleConsumer;

final class GuavaCacheBasedObjDoubleConsumerMemoizer<FIRST, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, KEY>
        implements ObjDoubleConsumer<FIRST> {

    private final ObjDoubleFunction<FIRST, KEY> keyFunction;
    private final ObjDoubleConsumer<FIRST> biConsumer;

    GuavaCacheBasedObjDoubleConsumerMemoizer(
            final Cache<KEY, KEY> cache,
            final ObjDoubleFunction<FIRST, KEY> keyFunction,
            final ObjDoubleConsumer<FIRST> biConsumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biConsumer = biConsumer;
    }

    @Override
    public void accept(final FIRST first, final double value) {
        final KEY key = keyFunction.apply(first, value);
        get(key, givenKey -> {
            biConsumer.accept(first, value);
            return givenKey;
        });
    }

}
