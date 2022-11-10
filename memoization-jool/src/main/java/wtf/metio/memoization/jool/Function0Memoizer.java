/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function0;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class Function0Memoizer<KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function0<OUTPUT> {

    private final Supplier<KEY> keySupplier;
    private final Function0<OUTPUT> function;

    Function0Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Supplier<KEY> keySupplier,
            final Function0<OUTPUT> function) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier, "Provide a key supplier.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function0 - provide an actual Function0 to fix this.");
    }

    @Override
    public OUTPUT get() {
        return computeIfAbsent(keySupplier.get(), key -> function.get());
    }

}
