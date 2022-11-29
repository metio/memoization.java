/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The RxJava module contains the implementation covering RxJava interfaces.
 */
@org.jspecify.nullness.NullMarked
module wtf.metio.memoization.rxjava {

    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;
    requires org.jspecify;
    requires io.reactivex.rxjava3;

    exports wtf.metio.memoization.rxjava;

}
