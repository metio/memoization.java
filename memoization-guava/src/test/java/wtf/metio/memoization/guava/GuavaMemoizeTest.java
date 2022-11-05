/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class GuavaMemoizeTest {

    @Test
    public void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // given
        final Constructor<GuavaMemoize> constructor = GuavaMemoize.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assertions.assertTrue(isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
