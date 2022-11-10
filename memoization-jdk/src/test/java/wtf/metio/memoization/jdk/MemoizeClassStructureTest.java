/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import wtf.metio.memoization.tck.ClassStructureTCK;

class MemoizeClassStructureTest extends ClassStructureTCK {

    @Override
    protected Class<?> getMemoizerClass() {
        return Memoize.class;
    }

}
