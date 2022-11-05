/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

final class GuavaCacheBasedBiConsumerMemoizer<FIRST, SECOND, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, KEY>
        implements BiConsumer<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiConsumer<FIRST, SECOND> biConsumer;

    GuavaCacheBasedBiConsumerMemoizer(
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
        get(key, givenKey -> {
            biConsumer.accept(first, second);
            return givenKey;
        });
    }

}
