/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjIntConsumer;

import static java.util.Objects.requireNonNull;

final class ObjIntConsumerMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements ObjIntConsumer<INPUT> {

    private final ObjIntFunction<INPUT, KEY> keyFunction;
    private final ObjIntConsumer<INPUT> consumer;

    ObjIntConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final ObjIntFunction<INPUT, KEY> keyFunction,
            final ObjIntConsumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objIntConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final INPUT input, final int value) {
        computeIfAbsent(keyFunction.apply(input, value), key -> {
            consumer.accept(input, value);
            return key;
        });
    }

}
