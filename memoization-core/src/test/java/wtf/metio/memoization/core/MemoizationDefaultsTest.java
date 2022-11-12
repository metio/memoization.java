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
import java.util.function.Supplier;

class MemoizationDefaultsTest {

    @Test
    void shouldDefineDefaultKeySupplier() {
        // given
        Supplier<String> keySupplier;

        // when
        keySupplier = MemoizationDefaults.staticKey();

        // then
        Assertions.assertNotNull(keySupplier);
    }

    @Test
    void shouldDefineDefaultKey() {
        // given
        final Supplier<String> keySupplier = MemoizationDefaults.staticKey();

        // when
        final String defaultSuppliedKey = keySupplier.get();

        // then
        Assertions.assertNotNull(defaultSuppliedKey);
        Assertions.assertEquals("SUPPLIED", defaultSuppliedKey);
    }

    @Test
    void shouldConcatHashCodesWithWhitespaceInbetween() {
        // given
        // when
        final String calculatedKey = MemoizationDefaults.hashCodes(1, 2);

        // when
        Assertions.assertEquals("1 2", calculatedKey);
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
