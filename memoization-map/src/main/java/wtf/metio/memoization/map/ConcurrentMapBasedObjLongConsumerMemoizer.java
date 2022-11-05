/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import wtf.metio.memoization.core.ObjLongFunction;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjLongConsumer;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedObjLongConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements ObjLongConsumer<INPUT> {

    private final ObjLongFunction<INPUT, KEY> keyFunction;
    private final ObjLongConsumer<INPUT> consumer;

    ConcurrentMapBasedObjLongConsumerMemoizer(
            final ConcurrentMap<KEY, INPUT> cache,
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
        final KEY key = keyFunction.apply(input, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(input, value);
            return input;
        });
    }

}
