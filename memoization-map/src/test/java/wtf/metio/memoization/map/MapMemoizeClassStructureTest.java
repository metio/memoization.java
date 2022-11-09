/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import wtf.metio.memoization.tck.ClassStructureTCK;

class MapMemoizeClassStructureTest extends ClassStructureTCK {

    @Override
    protected Class<?> getMemoizerClass() {
        return MapMemoize.class;
    }

}
