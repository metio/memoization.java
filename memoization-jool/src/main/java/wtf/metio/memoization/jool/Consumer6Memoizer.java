/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer6;
import org.jooq.lambda.function.Function6;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer6Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> {

    private final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction;
    private final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer;

    Consumer6Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction,
            final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer6 - provide an actual Consumer6 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6), key -> {
            consumer.accept(v1, v2, v3, v4, v5, v6);
            return key;
        });
    }

}
