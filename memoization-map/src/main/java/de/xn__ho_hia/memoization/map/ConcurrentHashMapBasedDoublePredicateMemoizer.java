/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoublePredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoublePredicateMemoizer
        extends ConcurrentHashMapBasedMemoizer<Double, Boolean>
        implements DoublePredicate {

    private final DoublePredicate predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedDoublePredicateMemoizer(
            final Map<Double, Boolean> cache,
            final DoublePredicate predicate) {
        super(cache);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final double value) {
        return computeIfAbsent(Double.valueOf(value), key -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
