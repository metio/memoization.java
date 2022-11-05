/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.MemoizingConsumer;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

final class GuavaCacheBasedConsumerMemoizer<KEY, INPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, INPUT>
        implements MemoizingConsumer<KEY, INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Consumer<INPUT> consumer;

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
    public Function<INPUT, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public Consumer<INPUT> consumer() {
        return consumer;
    }

}
