/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import java.util.Collections;
import java.util.Map;

class MemoizeJoolUsingMapCacheTest extends MemoizeJoolUsingCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        return Collections.emptyMap();
    }

}
