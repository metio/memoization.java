/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.*;

import java.util.function.Supplier;

class MemoizeLambdaUsingCustomKeyFunctionTest extends UsingCustomKeyFunctionTCK {

    @Override
    protected <KEY, OUTPUT> Fn0<OUTPUT> function0(final Fn0<OUTPUT> function, final Supplier<KEY> keySupplier) {
        return MemoizeLambda.fn0(function, keySupplier);
    }

    @Override
    protected <INPUT, KEY, OUTPUT> Fn1<INPUT, OUTPUT> function1(final Fn1<INPUT, OUTPUT> function, final Fn1<INPUT, KEY> keyFunction) {
        return MemoizeLambda.fn1(function, keyFunction);
    }

    @Override
    protected <TYPE1, TYPE2, OUTPUT> Fn2<TYPE1, TYPE2, OUTPUT> function2(final Fn2<TYPE1, TYPE2, OUTPUT> function, final Fn2<TYPE1, TYPE2, OUTPUT> keyFunction) {
        return MemoizeLambda.fn2(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function, final Fn3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return MemoizeLambda.fn3(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function, final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return MemoizeLambda.fn4(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function, final Fn5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return MemoizeLambda.fn5(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function, final Fn6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return MemoizeLambda.fn6(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function, final Fn7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return MemoizeLambda.fn7(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function, final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return MemoizeLambda.fn8(function, keyFunction);
    }

}
