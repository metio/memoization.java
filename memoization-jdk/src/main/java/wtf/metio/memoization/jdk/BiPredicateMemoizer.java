/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import static java.util.Objects.requireNonNull;

final class BiPredicateMemoizer<FIRST, SECOND, KEY>
        extends AbstractMemoizer<KEY, Boolean>
        implements BiPredicate<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND> biPredicate;

    public BiPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiPredicate<FIRST, SECOND> biPredicate) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.biPredicate = requireNonNull(biPredicate,
                "Cannot memoize a NULL BiPredicate - provide an actual BiPredicate to fix this.");
    }

    @Override
    public boolean test(final FIRST first, final SECOND second) {
        return computeIfAbsent(keyFunction.apply(first, second), key -> biPredicate.test(first, second));
    }

}
