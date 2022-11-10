/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class RunnableMemoizer<KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements Runnable {

    private final Supplier<KEY> keySupplier;
    private final Runnable runnable;

    public RunnableMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Supplier<KEY> keySupplier,
            final Runnable runnable) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.staticKey()'.");
        this.runnable = requireNonNull(runnable,
                "Cannot memoize a NULL Runnable - provide an actual Runnable to fix this.");
    }

    @Override
    public void run() {
        computeIfAbsent(keySupplier.get(), key -> {
            runnable.run();
            return key;
        });
    }

}
