/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

import java.util.Map;

class MemoizeUsingCustomKeyFunctionAndCache2kCacheTest extends MemoizeUsingCustomKeyFunctionAndCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        final Cache<K, V> cache = (Cache<K, V>) Cache2kBuilder.of(Object.class, Object.class).build();
        return cache.asMap();
    }

}
