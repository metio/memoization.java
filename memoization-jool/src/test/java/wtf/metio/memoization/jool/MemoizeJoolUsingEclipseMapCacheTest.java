/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;

import java.util.Map;

class MemoizeJoolUsingEclipseMapCacheTest extends MemoizeJoolUsingCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        final UnifiedMap<K, V> map = UnifiedMap.newMap();
        return map.asSynchronized();
    }

}
