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
