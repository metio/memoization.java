/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import wtf.metio.memoization.tck.ClassStructureTCK;

class MemoizeJoolClassStructureTest extends ClassStructureTCK {

    @Override
    protected Class<?> getMemoizerClass() {
        return MemoizeJool.class;
    }

}
