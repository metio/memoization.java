/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function2;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

final class Function2Memoizer<KEY, FIRST, SECOND, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function2<FIRST, SECOND, OUTPUT> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final Function2<FIRST, SECOND, OUTPUT> function;

    Function2Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final Function2<FIRST, SECOND, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function2 - provide an actual Function2 to fix this.");
    }

    @Override
    public OUTPUT apply(final FIRST first, final SECOND second) {
        return computeIfAbsent(keyFunction.apply(first, second), key -> function.apply(first, second));
    }

}
