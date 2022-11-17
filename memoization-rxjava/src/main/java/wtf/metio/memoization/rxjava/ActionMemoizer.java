/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Supplier;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class ActionMemoizer<KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements Action {

    private final Supplier<KEY> keySupplier;
    private final Action action;

    ActionMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Supplier<KEY> keySupplier,
            final Action action) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier, "Provide a key supplier.");
        this.action = requireNonNull(action,
                "Cannot memoize a NULL Action - provide an actual Action to fix this.");
    }

    @Override
    public void run() throws Throwable {
        try {
            computeIfAbsent(keySupplier.get(), key -> {
                try {
                    action.run();
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
