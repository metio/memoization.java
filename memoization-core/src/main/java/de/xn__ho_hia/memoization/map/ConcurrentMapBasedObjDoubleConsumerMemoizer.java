/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ObjDoubleConsumer;

import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedObjDoubleConsumerMemoizer<VALUE, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, KEY>
        implements ObjDoubleConsumer<VALUE> {

    private final ObjDoubleFunction<VALUE, KEY> keyFunction;
    private final ObjDoubleConsumer<VALUE>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedObjDoubleConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final ObjDoubleFunction<VALUE, KEY> keyFunction,
            final ObjDoubleConsumer<VALUE> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final VALUE t, final double value) {
        final KEY key = keyFunction.apply(t, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(t, value);
            return givenKey;
        });
    }

}
