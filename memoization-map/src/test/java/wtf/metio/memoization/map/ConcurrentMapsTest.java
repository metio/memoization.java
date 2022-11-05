/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class ConcurrentMapsTest {

    @Test
    void shouldMakeDefensiveCopyOfMap() {
        // given
        final Map<Object, Object> map = new HashMap<>();

        // when
        final ConcurrentMap<Object, Object> concurrentMap = ConcurrentMaps.asConcurrentMap(map);

        // then
        Assertions.assertNotNull(concurrentMap);
        Assertions.assertNotSame(map, concurrentMap);
    }

    @Test
    void shouldReturnConcurrentMap() {
        // given
        final Map<Object, Object> map = new ConcurrentHashMap<>();

        // when
        final ConcurrentMap<Object, Object> concurrentMap = ConcurrentMaps.asConcurrentMap(map);

        // then
        Assertions.assertNotNull(concurrentMap);
        Assertions.assertSame(map, concurrentMap);
    }

    @Test
    void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // given
        final Constructor<ConcurrentMaps> constructor = ConcurrentMaps.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assertions.assertTrue(isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
