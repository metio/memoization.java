/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.jooq.lambda.function.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

class MemoizeJoolUsingCustomKeyFunctionTest extends UsingCustomKeyFunctionTCK {

    @Override
    protected <KEY> Consumer0 consumer0(final Consumer0 consumer, final Supplier<KEY> keySupplier) {
        return MemoizeJool.consumer0(consumer, keySupplier);
    }

    @Override
    protected <KEY, TYPE1> Consumer1<TYPE1> consumer1(final Consumer1<TYPE1> consumer, final Function<TYPE1, KEY> keyFunction) {
        return MemoizeJool.consumer1(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2> Consumer2<TYPE1, TYPE2> consumer2(final Consumer2<TYPE1, TYPE2> consumer, final BiFunction<TYPE1, TYPE2, KEY> keyFunction) {
        return MemoizeJool.consumer2(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3> Consumer3<TYPE1, TYPE2, TYPE3> consumer3(final Consumer3<TYPE1, TYPE2, TYPE3> consumer, final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return MemoizeJool.consumer3(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4> Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer4(final Consumer4<TYPE1, TYPE2, TYPE3, TYPE4> consumer, final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return MemoizeJool.consumer4(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer5(final Consumer5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5> consumer, final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return MemoizeJool.consumer5(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer6(final Consumer6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6> consumer, final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return MemoizeJool.consumer6(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer7(final Consumer7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7> consumer, final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return MemoizeJool.consumer7(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer8(final Consumer8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8> consumer, final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return MemoizeJool.consumer8(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer9(final Consumer9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9> consumer, final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction) {
        return MemoizeJool.consumer9(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer10(final Consumer10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10> consumer, final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction) {
        return MemoizeJool.consumer10(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer11(final Consumer11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11> consumer, final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction) {
        return MemoizeJool.consumer11(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer12(final Consumer12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12> consumer, final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction) {
        return MemoizeJool.consumer12(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer13(final Consumer13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13> consumer, final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction) {
        return MemoizeJool.consumer13(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer14(final Consumer14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14> consumer, final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction) {
        return MemoizeJool.consumer14(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer15(final Consumer15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15> consumer, final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction) {
        return MemoizeJool.consumer15(consumer, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer16(final Consumer16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16> consumer, final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction) {
        return MemoizeJool.consumer16(consumer, keyFunction);
    }

    @Override
    protected <KEY, OUTPUT> Function0<OUTPUT> function0(
            final Function0<OUTPUT> function, final Supplier<KEY> keySupplier) {
        return MemoizeJool.function0(function, keySupplier);
    }

    @Override
    protected <INPUT, KEY, OUTPUT> Function1<INPUT, OUTPUT> function1(
            final Function1<INPUT, OUTPUT> function, final Function<INPUT, KEY> keyFunction) {
        return MemoizeJool.function1(function, keyFunction);
    }

    @Override
    protected <TYPE1, TYPE2, OUTPUT> Function2<TYPE1, TYPE2, OUTPUT> function2(final Function2<TYPE1, TYPE2, OUTPUT> function, final BiFunction<TYPE1, TYPE2, OUTPUT> keyFunction) {
        return MemoizeJool.function2(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, OUTPUT> Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function3(final Function3<TYPE1, TYPE2, TYPE3, OUTPUT> function, final Function3<TYPE1, TYPE2, TYPE3, KEY> keyFunction) {
        return MemoizeJool.function3(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function4(final Function4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function, final Function4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction) {
        return MemoizeJool.function4(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function5(final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, OUTPUT> function, final Function5<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, KEY> keyFunction) {
        return MemoizeJool.function5(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function6(final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, OUTPUT> function, final Function6<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, KEY> keyFunction) {
        return MemoizeJool.function6(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function7(final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, OUTPUT> function, final Function7<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, KEY> keyFunction) {
        return MemoizeJool.function7(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function8(final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function, final Function8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction) {
        return MemoizeJool.function8(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function9(final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function, final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction) {
        return MemoizeJool.function9(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function10(final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, OUTPUT> function, final Function10<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, KEY> keyFunction) {
        return MemoizeJool.function10(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function11(final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, OUTPUT> function, final Function11<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, KEY> keyFunction) {
        return MemoizeJool.function11(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function12(final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, OUTPUT> function, final Function12<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, KEY> keyFunction) {
        return MemoizeJool.function12(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function13(final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, OUTPUT> function, final Function13<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, KEY> keyFunction) {
        return MemoizeJool.function13(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function14(final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, OUTPUT> function, final Function14<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, KEY> keyFunction) {
        return MemoizeJool.function14(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function15(final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, OUTPUT> function, final Function15<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, KEY> keyFunction) {
        return MemoizeJool.function15(function, keyFunction);
    }

    @Override
    protected <KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function16(final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, OUTPUT> function, final Function16<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, TYPE10, TYPE11, TYPE12, TYPE13, TYPE14, TYPE15, TYPE16, KEY> keyFunction) {
        return MemoizeJool.function16(function, keyFunction);
    }

}
