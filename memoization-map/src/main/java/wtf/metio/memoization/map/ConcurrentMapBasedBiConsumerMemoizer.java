/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedBiConsumerMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, KEY>
        implements BiConsumer<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiConsumer<FIRST, SECOND> biConsumer;

    public ConcurrentMapBasedBiConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiConsumer<FIRST, SECOND> biConsumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biConsumer = requireNonNull(biConsumer,
                "Cannot memoize a NULL BiConsumer - provide an actual BiConsumer to fix this.");
    }

    @Override
    public void accept(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        computeIfAbsent(key, givenKey -> {
            biConsumer.accept(first, second);
            return givenKey;
        });
    }

}
