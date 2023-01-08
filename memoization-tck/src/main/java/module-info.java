/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The tck module contains re-usable test cases for the various memoization.java modules.
 */
@org.jspecify.annotations.NullMarked
module wtf.metio.memoization.tck {

    requires wtf.metio.memoization.core;
    requires org.junit.jupiter.api;
    requires org.jspecify;

    exports wtf.metio.memoization.tck;

}
