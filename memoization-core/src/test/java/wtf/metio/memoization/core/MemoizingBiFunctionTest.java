/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.BiFunction;
import java.util.function.Function;

class MemoizingBiFunctionTest {

    private final MemoizingBiFunction<String, String, String, String> memoizingBiFunction = Mockito
            .spy(TestMemoizingBiFunction.class);

    @Test
    void shouldMemoizeByCallingPredefinedMethods() {
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

    @Test
    void shouldMemoizeAppliedValues() {
        // given
        final String first = "junit";
        final String second = "test";

        // when
        final String calculatedValue = memoizingBiFunction.apply(first, second);

        // then
        Assertions.assertEquals("testjunit", calculatedValue);
    }

    static class TestMemoizingBiFunction implements MemoizingBiFunction<String, String, String, String> {

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
