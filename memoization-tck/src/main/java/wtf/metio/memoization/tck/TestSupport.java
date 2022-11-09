/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.tck;

final class TestSupport {

    static final int DEFAULT_RUNS = 5;
    static final int DEFAULT_WAIT = 1000;

    private TestSupport() {
        // utility class
    }

    static void threadedRun(final int threadCount, final Runnable runnable) {
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
    }

}
