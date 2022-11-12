/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer11;
import org.jooq.lambda.function.Function11;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer11Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> {

    private final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction;
    private final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer;

    Consumer11Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction,
            final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer11 - provide an actual Consumer11 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7, final TYPE8 v8, final TYPE9 v9, final TYPE10 v10, final TYPE11 v11) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11), key -> {
            consumer.accept(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11);
            return key;
        });
    }

}
