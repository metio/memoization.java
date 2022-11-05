/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedPredicateMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements Predicate<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Predicate<INPUT> predicate;

    public ConcurrentMapBasedPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final Function<INPUT, KEY> keyFunction,
            final Predicate<INPUT> predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final INPUT input) {
        final KEY key = keyFunction.apply(input);
        return computeIfAbsent(key, givenKey -> predicate.test(input));
    }

}
