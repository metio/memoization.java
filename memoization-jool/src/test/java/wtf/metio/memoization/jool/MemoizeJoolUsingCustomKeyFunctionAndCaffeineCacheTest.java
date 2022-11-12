/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Map;

class MemoizeJoolUsingCustomKeyFunctionAndCaffeineCacheTest extends MemoizeJoolUsingCustomKeyFunctionAndCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        final Cache<K, V> cache = Caffeine.newBuilder().build();
        return cache.asMap();
    }

}
