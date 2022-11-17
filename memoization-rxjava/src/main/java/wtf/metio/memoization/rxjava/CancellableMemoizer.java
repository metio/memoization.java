/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Supplier;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class CancellableMemoizer<KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements Cancellable {

    private final Supplier<KEY> keySupplier;
    private final Cancellable cancellable;

    CancellableMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Supplier<KEY> keySupplier,
            final Cancellable cancellable) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier, "Provide a key supplier.");
        this.cancellable = requireNonNull(cancellable,
                "Cannot memoize a NULL Cancellable - provide an actual Cancellable to fix this.");
    }

    @Override
    public void cancel() throws Throwable {
        try {
            computeIfAbsent(keySupplier.get(), key -> {
                try {
                    cancellable.cancel();
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
