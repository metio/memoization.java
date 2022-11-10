/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class FunctionMemoizer<INPUT, KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Function<INPUT, OUTPUT> function;

    FunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Function<INPUT, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function - provide an actual Function to fix this.");
    }

    @Override
    public OUTPUT apply(final INPUT input) {
        return computeIfAbsent(keyFunction.apply(input), givenKey -> function.apply(input));
    }

}
