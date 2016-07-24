/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntBinaryOperator;

import de.xn__ho_hia.memoization.shared.IntBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntBinaryOperatorMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements IntBinaryOperator {

    private final IntBinaryFunction<KEY> keyFunction;
    private final IntBinaryOperator      operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentMapBasedIntBinaryOperatorMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final IntBinaryFunction<KEY> keyFunction,
            final IntBinaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntBinaryOperator - provide an actual IntBinaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int left, final int right) {
        final KEY key = keyFunction.apply(left, right);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(operator.applyAsInt(left, right)))
                .intValue();
    }

}
