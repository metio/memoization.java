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
        Supplier<Integer> keySupplier;

        // when
        keySupplier = MemoizationDefaults.staticKey();

        // then
        Assertions.assertNotNull(keySupplier);
    }

    @Test
    void shouldDefineDefaultKey() {
        // given
        final Supplier<Integer> keySupplier = MemoizationDefaults.staticKey();

        // when
        final Integer defaultSuppliedKey = keySupplier.get();

        // then
        Assertions.assertNotNull(defaultSuppliedKey);
        Assertions.assertEquals(1, defaultSuppliedKey);
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
