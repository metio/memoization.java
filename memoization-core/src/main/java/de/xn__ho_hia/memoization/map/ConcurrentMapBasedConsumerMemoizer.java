/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import de.xn__ho_hia.memoization.shared.MemoizingConsumer;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedConsumerMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, INPUT>
        implements MemoizingConsumer<KEY, INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Consumer<INPUT>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedConsumerMemoizer(
            final ConcurrentMap<KEY, INPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Consumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function, might just be 'Function.identity()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public Function<INPUT, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public Consumer<INPUT> getConsumer() {
        return consumer;
    }

    @Override
    public BiFunction<KEY, Function<KEY, INPUT>, INPUT> getMemoizingFunction() {
        return this::computeIfAbsent;
    }

}
