/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The jOOL module contains the implementation covering jOOL interfaces.
 */
module wtf.metio.memoization.jool {

    requires org.jooq.jool;
    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;

    exports wtf.metio.memoization.jool;

}
