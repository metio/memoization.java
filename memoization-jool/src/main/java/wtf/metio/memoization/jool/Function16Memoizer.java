/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function16;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Function16Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> {

    private final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction;
    private final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function;

    Function16Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction,
            final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function16 - provide an actual Function16 to fix this.");
    }

    @Override
    public OUTPUT apply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7, final TYPE8 v8, final TYPE9 v9, final TYPE10 v10, final TYPE11 v11, final TYPE12 v12, final TYPE13 v13, final TYPE14 v14, final TYPE15 v15, final TYPE16 v16) {
        return computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16),
                key -> function.apply(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16));
    }

}
