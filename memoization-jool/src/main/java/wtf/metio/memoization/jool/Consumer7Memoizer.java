/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer7;
import org.jooq.lambda.function.Function7;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer7Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> {

    private final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction;
    private final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer;

    Consumer7Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction,
            final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer7 - provide an actual Consumer7 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7), key -> {
            consumer.accept(v1, v2, v3, v4, v5, v6, v7);
            return key;
        });
    }

}
