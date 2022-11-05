/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class MemoizationDefaultsTest {

    @Test
    void shouldDefineDefaultKeySupplier() {
        // given
        Supplier<String> keySupplier;

        // when
        keySupplier = MemoizationDefaults.defaultKeySupplier();

        // then
        Assertions.assertNotNull(keySupplier);
    }

    @Test
    void shouldDefineDefaultKey() {
        // given
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final String defaultSuppliedKey = keySupplier.get();

        // then
        Assertions.assertNotNull(defaultSuppliedKey);
        Assertions.assertEquals("SUPPLIED", defaultSuppliedKey);
    }

    @Test
    void shouldDefineHashCodeKeyFunction() {
        // given
        BiFunction<Object, Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        Assertions.assertNotNull(keyFunction);
    }

    @Test
    void shouldConcatHashCodesWithWhitespaceInbetween() {
        // given
        final BiFunction<Object, Object, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(1, 2);

        // when
        Assertions.assertEquals("1 2", calculatedKey);
    }

    @Test
    void shouldDefineObjDoubleConsumerHashCodeKeyFunction() {
        // given
        ObjDoubleFunction<Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction();

        // when
        Assertions.assertNotNull(keyFunction);
    }

    @Test
    void shouldConcatObjDoubleConsumerHashCodesWithWhitespaceInbetween() {
        // given
        final ObjDoubleFunction<Object, String> keyFunction = MemoizationDefaults
                .objDoubleConsumerHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(1, 123.456D);

        // when
        Assertions.assertEquals("1 1522623320", calculatedKey);
    }

    @Test
    void shouldDefineObjIntConsumerHashCodeKeyFunction() {
        // given
        ObjIntFunction<Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.objIntConsumerHashCodeKeyFunction();

        // when
        Assertions.assertNotNull(keyFunction);
    }

    @Test
    void shouldConcatObjIntConsumerHashCodesWithWhitespaceInbetween() {
        // given
        final ObjIntFunction<Object, String> keyFunction = MemoizationDefaults
                .objIntConsumerHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(1, 123);

        // when
        Assertions.assertEquals("1 123", calculatedKey);
    }

    @Test
    void shouldDefineObjLongConsumerHashCodeKeyFunction() {
        // given
        ObjLongFunction<Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.objLongConsumerHashCodeKeyFunction();

        // when
        Assertions.assertNotNull(keyFunction);
    }

    @Test
    void shouldConcatObjLongConsumerHashCodesWithWhitespaceInbetween() {
        // given
        final ObjLongFunction<Object, String> keyFunction = MemoizationDefaults
                .objLongConsumerHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(1, 123L);

        // when
        Assertions.assertEquals("1 123", calculatedKey);
    }

    @Test
    void shouldConcatDoubleBinaryOperatorHashCodesWithWhitespaceInbetween() {
        // given
        final DoubleBinaryFunction<String> keyFunction = MemoizationDefaults
                .doubleBinaryOperatorHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(123.456D, 789.123D);

        // when
        Assertions.assertEquals("1522623320 -1478220865", calculatedKey);
    }

    @Test
    void shouldConcatIntBinaryOperatorHashCodesWithWhitespaceInbetween() {
        // given
        final IntBinaryFunction<String> keyFunction = MemoizationDefaults
                .intBinaryOperatorHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(123, 456);

        // when
        Assertions.assertEquals("123 456", calculatedKey);
    }

    @Test
    void shouldConcatLongBinaryOperatorHashCodesWithWhitespaceInbetween() {
        // given
        final LongBinaryFunction<String> keyFunction = MemoizationDefaults
                .longBinaryOperatorHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(123L, 456L);

        // when
        Assertions.assertEquals("123 456", calculatedKey);
    }

    @Test
    void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Given
        final Constructor<MemoizationDefaults> constructor = MemoizationDefaults.class.getDeclaredConstructor();

        // When
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // Then
        Assertions.assertTrue(isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
