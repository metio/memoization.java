/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class MemoizingConsumerTest {

    private MemoizingConsumer<String, String> memoizingConsumer;

    @BeforeEach
    void setUp() {
        final TestMemoizingConsumer consumer = new TestMemoizingConsumer(Mockito.mock(Consumer.class));
        memoizingConsumer = Mockito.spy(consumer);
    }

    @Test
    void shouldMemoizeByCallingPredefinedMethods() {
        // given
        final String input = "test";

        // when
        memoizingConsumer.accept(input);

        // then
        Mockito.verify(memoizingConsumer).getKeyFunction();
        Mockito.verify(memoizingConsumer).consumer();
        Mockito.verify(memoizingConsumer).getMemoizingFunction();
        Mockito.verify(memoizingConsumer.consumer()).accept(input);
    }

    public static class TestMemoizingConsumer implements MemoizingConsumer<String, String> {

        private final Consumer<String> consumer;

        /**
         * @param consumer The consumer to wrap.
         */
        public TestMemoizingConsumer(final Consumer<String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public Function<String, String> getKeyFunction() {
            return Function.identity();
        }

        @Override
        public BiFunction<String, Function<String, String>, String> getMemoizingFunction() {
            return (a, b) -> {
                final String calculatedValue = b.apply(a);
                return calculatedValue.trim();
            };
        }

        @Override
        public Consumer<String> consumer() {
            return consumer;
        }

    }

}
