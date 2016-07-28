/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.cache.Cache;

final class JCacheBasedConsumerMemoizer<INPUT, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, INPUT>
        implements Consumer<INPUT> {

    private final Consumer<INPUT>      consumer;
    private final Function<INPUT, KEY> keyFunction;

    JCacheBasedConsumerMemoizer(
            final Cache<KEY, INPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Consumer<INPUT> consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public void accept(final INPUT input) {
        final KEY key = keyFunction.apply(input);
        invoke(key, givenKey -> {
            consumer.accept(input);
            return input;
        });
    }

}
