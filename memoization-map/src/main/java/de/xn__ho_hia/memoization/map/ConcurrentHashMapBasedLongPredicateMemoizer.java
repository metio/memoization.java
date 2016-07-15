/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongPredicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongPredicateMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Boolean>
        implements LongPredicate {

    private final LongPredicate predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedLongPredicateMemoizer(
            final Map<Long, Boolean> cache,
            final LongPredicate predicate) {
        super(cache);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final long value) {
        return computeIfAbsent(Long.valueOf(value), key -> Boolean.valueOf(predicate.test(value))).booleanValue();
    }

}
