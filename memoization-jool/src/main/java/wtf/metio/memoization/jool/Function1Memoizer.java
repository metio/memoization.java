/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.Function1;
import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class Function1Memoizer<KEY, INPUT, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function1<INPUT, OUTPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Function1<INPUT, OUTPUT> function;

    Function1Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Function1<INPUT, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function1 - provide an actual Function1 to fix this.");
    }

    @Override
    public OUTPUT apply(final INPUT input) {
        return computeIfAbsent(keyFunction.apply(input), key -> function.apply(input));
    }

}
