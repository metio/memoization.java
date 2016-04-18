/*
 * ysura GmbH ("COMPANY") CONFIDENTIAL
 * Unpublished Copyright (c) 2012-2015 ysura GmbH, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of COMPANY. The intellectual and technical concepts contained
 * herein are proprietary to COMPANY and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from COMPANY.  Access to the source code contained herein is hereby forbidden to anyone except current COMPANY employees, managers or contractors who have executed
 * Confidentiality and Non-disclosure agreements explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
 * information that is confidential and/or proprietary, and is a trade secret, of COMPANY. ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC PERFORMANCE,
 * OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS SOURCE CODE WITHOUT THE EXPRESS WRITTEN CONSENT OF COMPANY IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES. THE RECEIPT OR POSSESSION OF THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
 * TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 */
package com.github.sebhoss.utils.memoization.shared;

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
