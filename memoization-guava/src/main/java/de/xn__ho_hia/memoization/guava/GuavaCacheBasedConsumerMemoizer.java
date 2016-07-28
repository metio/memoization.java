/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.cache.Cache;

import de.xn__ho_hia.memoization.shared.MemoizingConsumer;

final class GuavaCacheBasedConsumerMemoizer<KEY, INPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, INPUT>
        implements MemoizingConsumer<KEY, INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Consumer<INPUT>      consumer;

    GuavaCacheBasedConsumerMemoizer(
            final Cache<KEY, INPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Consumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public BiFunction<KEY, Function<KEY, INPUT>, INPUT> getMemoizingFunction() {
        return this::get;
    }

    @Override
    public final Function<INPUT, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public final Consumer<INPUT> getConsumer() {
        return consumer;
    }

}
