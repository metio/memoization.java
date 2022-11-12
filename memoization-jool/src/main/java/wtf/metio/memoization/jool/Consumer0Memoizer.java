/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer0;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class Consumer0Memoizer<KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer0 {

    private final Supplier<KEY> keySupplier;
    private final Consumer0 consumer;

    Consumer0Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Supplier<KEY> keySupplier,
            final Consumer0 consumer) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key supplier, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer0 - provide an actual Consumer0 to fix this.");
    }

    @Override
    public void accept() {
        computeIfAbsent(keySupplier.get(), key -> {
            consumer.accept();
            return key;
        });
    }

}
