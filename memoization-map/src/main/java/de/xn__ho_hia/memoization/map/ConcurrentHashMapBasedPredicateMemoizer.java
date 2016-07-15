/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Predicate;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedPredicateMemoizer<VALUE>
        extends ConcurrentHashMapBasedMemoizer<VALUE, Boolean>
        implements Predicate<VALUE> {

    private final Predicate<VALUE> predicate;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedPredicateMemoizer(
            final Map<VALUE, Boolean> preComputedValues,
            final Predicate<VALUE> predicate) {
        super(preComputedValues);
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final VALUE input) {
        return computeIfAbsent(input, predicate::test).booleanValue();
    }

}
