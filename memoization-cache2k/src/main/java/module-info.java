/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The cache2k module contains the cache2k based implementation of memoization.java
 */
module wtf.metio.memoization.cache2k {

    requires org.cache2k.api;
    requires wtf.metio.memoization.map;
    requires wtf.metio.memoization.core;

    exports wtf.metio.memoization.cache2k;

}
