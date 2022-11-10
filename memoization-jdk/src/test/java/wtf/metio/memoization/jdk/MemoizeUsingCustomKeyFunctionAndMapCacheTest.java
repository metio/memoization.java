/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import java.util.Collections;
import java.util.Map;

class MemoizeUsingCustomKeyFunctionAndMapCacheTest extends MemoizeUsingCustomKeyFunctionAndCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        return Collections.emptyMap();
    }

}
