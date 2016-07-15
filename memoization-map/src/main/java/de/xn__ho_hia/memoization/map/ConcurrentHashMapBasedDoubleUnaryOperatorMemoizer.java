/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoubleUnaryOperatorMemoizer
        extends ConcurrentHashMapBasedMemoizer<Double, Double>
        implements DoubleUnaryOperator {

    private final DoubleUnaryOperator operator;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedDoubleUnaryOperatorMemoizer(
            final Map<Double, Double> preComputedValues,
            final DoubleUnaryOperator operator) {
        super(preComputedValues);
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleUnaryOperator - provide an actual DoubleUnaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double operand) {
        final Double key = Double.valueOf(operand);
        return computeIfAbsent(key, givenKey -> Double.valueOf(operator.applyAsDouble(operand)))
                .doubleValue();
    }

}
