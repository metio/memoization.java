/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The guava module contains the Guava based implementation of memoization.java
 */
module wtf.metio.memoization.guava {

    requires com.google.common;
    requires wtf.metio.memoization.core;

    exports wtf.metio.memoization.guava;

}
