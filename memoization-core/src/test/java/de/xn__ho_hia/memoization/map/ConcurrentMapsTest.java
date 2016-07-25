/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentMapsTest {

    /**
    *
    */
    @Test
    public void shouldMakeDefensiveCopyOfMap() {
        // given
        final Map<Object, Object> map = new HashMap<>();

        // when
        final ConcurrentMap<Object, Object> concurrentMap = ConcurrentMaps.asConcurrentMap(map);

        // then
        Assert.assertNotNull("Returned map is NULL", concurrentMap);
        Assert.assertNotSame("Instsances are the same", map, concurrentMap);
    }

    /**
    *
    */
    @Test
    public void shouldReturnConcurrentMap() {
        // given
        final Map<Object, Object> map = new ConcurrentHashMap<>();

        // when
        final ConcurrentMap<Object, Object> concurrentMap = ConcurrentMaps.asConcurrentMap(map);

        // then
        Assert.assertNotNull("Returned map is NULL", concurrentMap);
        Assert.assertSame("Instances are not the same", map, concurrentMap);
    }

    /**
     * @throws NoSuchMethodException
     *             Reflection problemt
     * @throws IllegalAccessException
     *             Reflection problemt
     * @throws InvocationTargetException
     *             Reflection problemt
     * @throws InstantiationException
     *             Reflection problemt
     */
    @Test
    public void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // given
        final Constructor<ConcurrentMaps> constructor = ConcurrentMaps.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assert.assertTrue("Constructor is not private", isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
