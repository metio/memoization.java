/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;

class MemoizeUsingGuavaCacheTest extends MemoizeUsingCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        final Cache<K, V> cache = CacheBuilder.newBuilder().build();
        return cache.asMap();
    }

}
