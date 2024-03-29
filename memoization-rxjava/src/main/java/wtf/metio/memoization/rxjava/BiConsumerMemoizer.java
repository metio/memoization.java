/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class BiConsumerMemoizer<FIRST, SECOND, KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements BiConsumer<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiConsumer<FIRST, SECOND> biConsumer;

    BiConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiConsumer<FIRST, SECOND> biConsumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.biConsumer = requireNonNull(biConsumer,
                "Cannot memoize a NULL BiConsumer - provide an actual BiConsumer to fix this.");
    }

    @Override
    public void accept(final FIRST first, final SECOND second) throws Throwable {
        try {
            computeIfAbsent(keyFunction.apply(first, second), key -> {
                try {
                    biConsumer.accept(first, second);
                    return key;
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
