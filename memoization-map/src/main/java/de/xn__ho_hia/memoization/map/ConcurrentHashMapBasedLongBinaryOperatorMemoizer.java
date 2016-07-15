/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongBinaryOperator;

import de.xn__ho_hia.memoization.shared.LongBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongBinaryOperatorMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Long>
        implements LongBinaryOperator {

    private final LongBinaryFunction<KEY> keyFunction;
    private final LongBinaryOperator      operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedLongBinaryOperatorMemoizer(
            final Map<KEY, Long> preComputedValues,
            final LongBinaryFunction<KEY> keyFunction,
            final LongBinaryOperator operator) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL LongBinaryOperator - provide an actual LongBinaryOperator to fix this.");
    }

    @Override
    public long applyAsLong(final long left, final long right) {
        final KEY key = keyFunction.apply(left, right);
        return computeIfAbsent(key, givenKey -> Long.valueOf(operator.applyAsLong(left, right)))
                .longValue();
    }

}
