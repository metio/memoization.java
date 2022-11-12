/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.*;

class MemoizeLambdaUsingDefaultsTest extends UsingDefaultsTCK {

    @Override
    protected <OUTPUT> Fn0<OUTPUT> function0(final Fn0<OUTPUT> function) {
        return MemoizeLambda.fn0(function);
    }

    @Override
    protected <INPUT, OUTPUT> Fn1<INPUT, OUTPUT> function1(final Fn1<INPUT, OUTPUT> function) {
        return MemoizeLambda.fn1(function);
    }

    @Override
    protected <TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> function2(final Fn2<TYPE1, TYPE2, OUTPUT> function) {
        return MemoizeLambda.fn2(function);
    }

    @Override
    protected <TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function) {
        return MemoizeLambda.fn3(function);
    }

    @Override
    protected <TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function) {
        return MemoizeLambda.fn4(function);
    }

    @Override
    protected <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function) {
        return MemoizeLambda.fn5(function);
    }

    @Override
    protected <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function) {
        return MemoizeLambda.fn6(function);
    }

    @Override
    protected <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function) {
        return MemoizeLambda.fn7(function);
    }

    @Override
    protected <TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function) {
        return MemoizeLambda.fn8(function);
    }

}
