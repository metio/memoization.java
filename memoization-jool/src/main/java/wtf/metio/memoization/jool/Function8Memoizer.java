/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function8;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Function8Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> {

    private final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction;
    private final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function;

    Function8Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction,
            final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function8 - provide an actual Function8 to fix this.");
    }

    @Override
    public OUTPUT apply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7, final TYPE8 v8) {
        return computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7, v8),
                key -> function.apply(v1, v2, v3, v4, v5, v6, v7, v8));
    }

}
