/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer4;
import org.jooq.lambda.function.Function4;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer4Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> {

    private final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction;
    private final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer;

    Consumer4Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction,
            final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer4 - provide an actual Consumer4 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3, v4), key -> {
            consumer.accept(v1, v2, v3, v4);
            return key;
        });
    }

}
