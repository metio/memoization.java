/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 *
 */
@SuppressWarnings("nls")
public class MemoizingBiFunctionTest {

    private final MemoizingBiFunction<String, String, String, String> memoizingBiFunction = Mockito
            .spy(TestMemoizingBiFunction.class);

    /**
     *
     */
    @Test
    public void shouldMemoizeByCallingPredefinedMethods() {
        // given
        final String first = "junit";
        final String second = "test";

        // when
        memoizingBiFunction.apply(first, second);

        // then
        Mockito.verify(memoizingBiFunction).getKeyFunction();
        Mockito.verify(memoizingBiFunction).getMemoizingFunction();
        Mockito.verify(memoizingBiFunction).getBiFunction();
    }

    /**
     *
     */
    @Test
    public void shouldMemoizeAppliedValues() {
        // given
        final String first = "junit";
        final String second = "test";

        // when
        final String calculatedValue = memoizingBiFunction.apply(first, second);

        // then
        Assert.assertEquals("Calculated key does not match expectations", "testjunit", calculatedValue);
    }

    /**
     *
     *
     */
    public static class TestMemoizingBiFunction implements MemoizingBiFunction<String, String, String, String> {

        @Override
        public BiFunction<String, String, String> getKeyFunction() {
            return (a, b) -> a + b;
        }

        @Override
        public BiFunction<String, Function<String, String>, String> getMemoizingFunction() {
            return (a, b) -> b.apply(a);
        }

        @Override
        public BiFunction<String, String, String> getBiFunction() {
            return (a, b) -> b + a;
        }

    }

}
