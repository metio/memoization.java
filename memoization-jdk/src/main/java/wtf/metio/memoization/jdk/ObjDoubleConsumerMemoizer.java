/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjDoubleConsumer;

import static java.util.Objects.requireNonNull;

final class ObjDoubleConsumerMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements ObjDoubleConsumer<INPUT> {

    private final ObjDoubleFunction<INPUT, KEY> keyFunction;
    private final ObjDoubleConsumer<INPUT> consumer;

    ObjDoubleConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final ObjDoubleFunction<INPUT, KEY> keyFunction,
            final ObjDoubleConsumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final INPUT input, final double value) {
        computeIfAbsent(keyFunction.apply(input, value), key -> {
            consumer.accept(input, value);
            return key;
        });
    }

}
