/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.caffeine;

import wtf.metio.memoization.tck.ClassStructureTest;

class CaffeineClassStructureTest extends ClassStructureTest {

    @Override
    protected Class<?> getMemoizerClass() {
        return CaffeineMemoize.class;
    }

}
