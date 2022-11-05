/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The caffeine module contains the Caffeine based implementation of memoization.java
 */
module wtf.metio.memoization.caffeine {

    requires com.github.benmanes.caffeine;
    requires wtf.metio.memoization.core;
    requires wtf.metio.memoization.map;

    exports wtf.metio.memoization.caffeine;

}
