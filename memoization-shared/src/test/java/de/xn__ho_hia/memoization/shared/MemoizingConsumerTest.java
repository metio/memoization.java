/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 *
 */
public class MemoizingConsumerTest {

    private MemoizingConsumer<String, String> memoizingConsumer;

    /**
     *
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        final TestMemoizingConsumer consumer = new TestMemoizingConsumer(Mockito.mock(Consumer.class));
        memoizingConsumer = Mockito.spy(consumer);
    }

    /**
     *
     */
    @Test
    public void shouldMemoizeByCallingPredefinedMethods() {
        // given
        final String input = "test"; //$NON-NLS-1$

        // when
        memoizingConsumer.accept(input);

        // then
        Mockito.verify(memoizingConsumer).getKeyFunction();
        Mockito.verify(memoizingConsumer).getConsumer();
        Mockito.verify(memoizingConsumer).getMemoizingFunction();
        Mockito.verify(memoizingConsumer.getConsumer()).accept(input);
    }

    /**
     *
     *
     */
    public static class TestMemoizingConsumer implements MemoizingConsumer<String, String> {

        private final Consumer<String> consumer;

        /**
         * @param consumer
         *            The consumer to wrap.
         */
        public TestMemoizingConsumer(final Consumer<String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public Function<String, String> getKeyFunction() {
            return Function.identity();
        }

        @Override
        public Consumer<String> getConsumer() {
            return consumer;
        }

        @Override
        public BiFunction<String, Function<String, String>, String> getMemoizingFunction() {
            return (a, b) -> {
                final String calculatedValue = b.apply(a);
                return calculatedValue.trim();
            };
        }

    }

}
