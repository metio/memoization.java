/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedBiPredicateMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements BiPredicate<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND> biPredicate;

    public ConcurrentMapBasedBiPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiPredicate<FIRST, SECOND> biPredicate) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biPredicate = requireNonNull(biPredicate,
                "Cannot memoize a NULL BiPredicate - provide an actual BiPredicate to fix this.");
    }

    @Override
    public boolean test(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> biPredicate.test(t, u));
    }

}
