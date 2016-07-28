/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjIntConsumer;

import de.xn__ho_hia.memoization.shared.ObjIntFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedObjIntConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements ObjIntConsumer<INPUT> {

    private final ObjIntFunction<INPUT, KEY> keyFunction;
    private final ObjIntConsumer<INPUT>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
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
