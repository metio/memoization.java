/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer5;
import org.jooq.lambda.function.Function5;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer5Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> {

    private final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction;
    private final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer;

    Consumer5Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction,
            final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer5 - provide an actual Consumer5 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5), key -> {
            consumer.accept(v1, v2, v3, v4, v5);
            return key;
        });
    }

}
