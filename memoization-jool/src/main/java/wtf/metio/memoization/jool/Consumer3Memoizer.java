/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer3;
import org.jooq.lambda.function.Function3;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer3Memoizer<KEY, TYPE1, TYPE2, TYPE3>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer3<TYPE1, TYPE2, TYPE3> {

    private final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction;
    private final Consumer3<TYPE1, TYPE2, TYPE3> consumer;

    Consumer3Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction,
            final Consumer3<TYPE1, TYPE2, TYPE3> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer3 - provide an actual Consumer3 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3), key -> {
            consumer.accept(v1, v2, v3);
            return key;
        });
    }

}
