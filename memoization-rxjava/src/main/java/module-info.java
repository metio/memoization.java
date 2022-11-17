/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
/**
 * The RxJava module contains the implementation covering RxJava interfaces.
 */
module wtf.metio.memoization.rxjava {

    requires io.reactivex.rxjava3;
    requires wtf.metio.memoization.core;
    requires com.github.spotbugs.annotations;

    exports wtf.metio.memoization.rxjava;

}