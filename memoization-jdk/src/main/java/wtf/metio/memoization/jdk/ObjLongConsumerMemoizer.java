/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjLongConsumer;

import static java.util.Objects.requireNonNull;

final class ObjLongConsumerMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements ObjLongConsumer<INPUT> {

    private final ObjLongFunction<INPUT, KEY> keyFunction;
    private final ObjLongConsumer<INPUT> consumer;

    ObjLongConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final ObjLongFunction<INPUT, KEY> keyFunction,
            final ObjLongConsumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objLongConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final INPUT input, final long value) {
        computeIfAbsent(keyFunction.apply(input, value), key -> {
            consumer.accept(input, value);
            return key;
        });
    }

}
