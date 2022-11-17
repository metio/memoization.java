/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class ConsumerMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, KEY>
        implements Consumer<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Consumer<INPUT> consumer;

    ConsumerMemoizer(
            final ConcurrentMap<KEY, KEY> cache,
            final Function<INPUT, KEY> keyFunction,
            final Consumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function, might just be 'Function.identity()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final INPUT input) throws Throwable {
        try {
            computeIfAbsent(keyFunction.apply(input), key -> {
                try {
                    consumer.accept(input);
                    return key;
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
