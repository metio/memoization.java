/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function3;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Function3Memoizer<KEY, TYPE1, TYPE2, TYPE3, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function3<TYPE1, TYPE2, TYPE3, OUTPUT> {

    private final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction;
    private final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function;

    Function3Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction,
            final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function3 - provide an actual Function3 to fix this.");
    }

    @Override
    public OUTPUT apply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3) {
        return computeIfAbsent(keyFunction.apply(v1, v2, v3), key -> function.apply(v1, v2, v3));
    }

}
