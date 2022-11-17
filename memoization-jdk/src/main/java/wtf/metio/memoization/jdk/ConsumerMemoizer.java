/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class ConsumerMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Consumer<INPUT> consumer;

    ConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function<INPUT, KEY> keyFunction,
            final Consumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function, might just be 'Function.identity()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final INPUT input) {
        computeIfAbsent(keyFunction.apply(input), key -> {
            consumer.accept(input);
            return key;
        });
    }

}
