/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedPredicateMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements Predicate<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Predicate<INPUT>     predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
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
        return computeIfAbsent(key, givenKey -> Boolean.valueOf(predicate.test(input))).booleanValue();
    }

}
