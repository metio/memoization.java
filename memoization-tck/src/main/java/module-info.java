/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The tck module contains re-usable test cases for the various memoization.java modules.
 */
module wtf.metio.memoization.tck {

    requires org.junit.jupiter.api;
    requires org.mockito;
    requires concurrentunit;
    requires wtf.metio.memoization.core;

    exports wtf.metio.memoization.tck;

}
