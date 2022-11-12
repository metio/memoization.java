/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer2;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

final class Consumer2Memoizer<KEY, TYPE1, TYPE2>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer2<TYPE1, TYPE2> {

    private final BiFunction<TYPE1, TYPE2, KEY> keyFunction;
    private final Consumer2<TYPE1, TYPE2> consumer;

    Consumer2Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final BiFunction<TYPE1, TYPE2, KEY> keyFunction,
            final Consumer2<TYPE1, TYPE2> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer2 - provide an actual Consumer2 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2) {
        computeIfAbsent(keyFunction.apply(v1, v2), key -> {
            consumer.accept(v1, v2);
            return key;
        });
    }

}
