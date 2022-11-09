/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import wtf.metio.memoization.tck.ClassStructureTCK;

class GuavaMemoizeClassStructureTest extends ClassStructureTCK {

    @Override
    protected Class<?> getMemoizerClass() {
        return GuavaMemoize.class;
    }

}
