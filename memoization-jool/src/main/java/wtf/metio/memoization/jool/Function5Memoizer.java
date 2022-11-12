/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function5;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Function5Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> {

    private final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction;
    private final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function;

    Function5Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction,
            final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function5 - provide an actual Function5 to fix this.");
    }

    @Override
    public OUTPUT apply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5) {
        return computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5), key -> function.apply(v1, v2, v3, v4, v5));
    }

}
