/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedException;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class CallableMemoizer<OUTPUT, KEY>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Callable<OUTPUT> {

    private final Supplier<KEY> keySupplier;
    private final Callable<OUTPUT> callable;

    public CallableMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Supplier<KEY> keySupplier,
            final Callable<OUTPUT> callable) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.staticKey()'.");
        this.callable = requireNonNull(callable,
                "Cannot memoize a NULL Callable - provide an actual Callable to fix this.");
    }

    @Override
    public OUTPUT call() throws Exception {
        try {
            return computeIfAbsent(keySupplier.get(), givenKey -> {
                try {
                    return callable.call();
                } catch (final Exception exception) {
                    throw new WrappedException(exception);
                }
            });
        } catch (final WrappedException exception) {
            throw exception.wrappedException();
        }
    }

}
