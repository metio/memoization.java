/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import wtf.metio.memoization.core.ObjDoubleFunction;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjDoubleConsumer;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedObjDoubleConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements ObjDoubleConsumer<INPUT> {

    private final ObjDoubleFunction<INPUT, KEY> keyFunction;
    private final ObjDoubleConsumer<INPUT> consumer;

    ConcurrentMapBasedObjDoubleConsumerMemoizer(
            final ConcurrentMap<KEY, INPUT> cache,
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
        final KEY key = keyFunction.apply(input, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(input, value);
            return input;
        });
    }

}
