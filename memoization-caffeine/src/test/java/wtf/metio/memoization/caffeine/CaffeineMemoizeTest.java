/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

class CaffeineMemoizeTest {

    @Test
    void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // given
        final Constructor<CaffeineMemoize> constructor = CaffeineMemoize.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assertions.assertTrue(isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
