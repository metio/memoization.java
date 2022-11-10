/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;

class MemoizeJoolUsingCustomKeyFunctionAndGuavaCacheTest extends MemoizeJoolUsingCustomKeyFunctionAndCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        final Cache<K, V> cache = CacheBuilder.newBuilder().build();
        return cache.asMap();
    }

}
