/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The lambda module contains the implementation covering lambda interfaces.
 */
@org.jspecify.annotations.NullMarked
module wtf.metio.memoization.lambda {

    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;
    requires org.jspecify;
    requires lambda;

    exports wtf.metio.memoization.lambda;

}
