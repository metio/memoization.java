/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Consumer14;
import org.jooq.lambda.function.Function14;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Consumer14Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> {

    private final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction;
    private final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer;

    Consumer14Memoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction,
            final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer14 - provide an actual Consumer14 to fix this.");
    }

    @Override
    public void accept(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7, final TYPE8 v8, final TYPE9 v9, final TYPE10 v10, final TYPE11 v11, final TYPE12 v12, final TYPE13 v13, final TYPE14 v14) {
        computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14), key -> {
            consumer.accept(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14);
            return key;
        });
    }

}
