/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import wtf.metio.memoization.core.ObjIntFunction;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjIntConsumer;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedObjIntConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements ObjIntConsumer<INPUT> {

    private final ObjIntFunction<INPUT, KEY> keyFunction;
    private final ObjIntConsumer<INPUT> consumer;

    ConcurrentMapBasedObjIntConsumerMemoizer(
            final ConcurrentMap<KEY, INPUT> cache,
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
        final KEY key = keyFunction.apply(input, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(input, value);
            return input;
        });
    }

}
