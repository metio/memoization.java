/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.BiFunction;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class BiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends AbstractMemoizer<KEY, VALUE>
        implements BiFunction<FIRST, SECOND, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    BiFunctionMemoizer(
            final ConcurrentMap<KEY, VALUE> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.biFunction = requireNonNull(biFunction,
                "Cannot memoize a NULL BiFunction - provide an actual BiFunction to fix this.");
    }

    @Override
    public VALUE apply(final FIRST first, final SECOND second) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(first, second), key -> {
                try {
                    return biFunction.apply(first, second);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
