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

final class GuavaCacheBasedConsumerMemoizer<KEY, VALUE>
        extends AbstractGuavaCacheBasedMemoizer<KEY, VALUE>
        implements MemoizingConsumer<KEY, VALUE> {

    private final Function<VALUE, KEY> keyFunction;
    private final Consumer<VALUE>      consumer;

    GuavaCacheBasedConsumerMemoizer(
            final Cache<KEY, VALUE> cache,
            final Function<VALUE, KEY> keyFunction,
            final Consumer<VALUE> consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
        return this::get;
    }

    @Override
    public final Function<VALUE, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public final Consumer<VALUE> getConsumer() {
        return consumer;
    }

}
