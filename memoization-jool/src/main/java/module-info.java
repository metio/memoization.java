/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The jOOL module contains the implementation covering jOOL interfaces.
 */
@org.jspecify.nullness.NullMarked
module wtf.metio.memoization.jool {

    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;
    requires org.jspecify;
    requires org.jooq.jool;

    exports wtf.metio.memoization.jool;

}
