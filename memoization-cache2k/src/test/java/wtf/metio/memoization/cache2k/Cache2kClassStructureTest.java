/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.cache2k;

import wtf.metio.memoization.tck.ClassStructureTest;

class Cache2kClassStructureTest extends ClassStructureTest {

    @Override
    protected Class<?> getMemoizerClass() {
        return Cache2kMemoize.class;
    }

}
