/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import wtf.metio.memoization.tck.ClassStructureTCK;

class MemoizeLambdaTest extends ClassStructureTCK {

    @Override
    protected Class<?> getMemoizerClass() {
        return MemoizeLambda.class;
    }

}
