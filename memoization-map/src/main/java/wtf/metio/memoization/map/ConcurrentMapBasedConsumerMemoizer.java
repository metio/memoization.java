/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.map;

import wtf.metio.memoization.core.MemoizingConsumer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements MemoizingConsumer<KEY, INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Consumer<INPUT> consumer;

    ConcurrentMapBasedConsumerMemoizer(
            final ConcurrentMap<KEY, INPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Consumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function, might just be 'Function.identity()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public Function<INPUT, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public Consumer<INPUT> consumer() {
        return consumer;
    }

    @Override
    public BiFunction<KEY, Function<KEY, INPUT>, INPUT> getMemoizingFunction() {
        return this::computeIfAbsent;
    }

}
