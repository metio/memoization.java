/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import java.util.Collections;
import java.util.Map;

class MemoizeRxUsingCustomKeyFunctionAndMapCacheTest extends MemoizeRxUsingCustomKeyFunctionAndCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        return Collections.emptyMap();
    }

}
