/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntUnaryOperator;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntUnaryOperatorMemoizer
        extends ConcurrentMapBasedMemoizer<Integer, Integer>
        implements IntUnaryOperator {

    private final IntUnaryOperator operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedIntUnaryOperatorMemoizer(
            final ConcurrentMap<Integer, Integer> cache,
            final IntUnaryOperator operator) {
        super(cache);
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntUnaryOperator - provide an actual IntUnaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int operand) {
        final Integer key = Integer.valueOf(operand);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(operator.applyAsInt(operand)))
                .intValue();
    }

}
