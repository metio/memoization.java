/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjLongConsumer;

import de.xn__ho_hia.memoization.shared.ObjLongFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedObjLongConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements ObjLongConsumer<INPUT> {

    private final ObjLongFunction<INPUT, KEY> keyFunction;
    private final ObjLongConsumer<INPUT>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
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
