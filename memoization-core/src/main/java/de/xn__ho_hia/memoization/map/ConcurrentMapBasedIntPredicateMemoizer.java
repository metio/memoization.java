/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntPredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntPredicateMemoizer
        extends ConcurrentMapBasedMemoizer<Integer, Boolean>
        implements IntPredicate {

    private final IntPredicate predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedIntPredicateMemoizer(
            final ConcurrentMap<Integer, Boolean> cache,
            final IntPredicate predicate) {
        super(cache);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final int value) {
        return computeIfAbsent(Integer.valueOf(value), key -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
