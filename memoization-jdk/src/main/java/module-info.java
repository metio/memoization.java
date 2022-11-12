/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The JDK module contains the implementation covering JDK interfaces.
 */
module wtf.metio.memoization.jdk {

    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;

    exports wtf.metio.memoization.jdk;

}
