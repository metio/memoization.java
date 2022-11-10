/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer1;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class Consumer1Memoizer<KEY, TYPE1>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer1<TYPE1> {

    private final Function<TYPE1, KEY> keyFunction;
    private final Consumer1<TYPE1> consumer;

    Consumer1Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function<TYPE1, KEY> keyFunction,
            final Consumer1<TYPE1> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer1 - provide an actual Consumer1 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1) {
        computeIfAbsent(keyFunction.apply(v1), key -> {
            consumer.accept(v1);
            return key;
        });
    }

}
