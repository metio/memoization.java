/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The JDK module contains the implementation covering JDK interfaces.
 */
@org.jspecify.annotations.NullMarked
module wtf.metio.memoization.jdk {

    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;
    requires org.jspecify;

    exports wtf.metio.memoization.jdk;

}
