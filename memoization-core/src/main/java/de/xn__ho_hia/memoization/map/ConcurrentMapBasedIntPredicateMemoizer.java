/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntPredicateMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements IntPredicate {

    private final IntFunction<KEY> keyFunction;
    private final IntPredicate     predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedIntPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final IntFunction<KEY> keyFunction,
            final IntPredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final int value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
