/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public abstract class AbstractMemoizer<KEY, VALUE> {

    private final ConcurrentMap<KEY, VALUE> cache;

    protected AbstractMemoizer(final ConcurrentMap<KEY, VALUE> cache) {
        this.cache = cache;
    }

    @CheckReturnValue
    protected final VALUE computeIfAbsent(final KEY key, final Function<KEY, VALUE> mappingFunction) {
        return cache.computeIfAbsent(key, mappingFunction);
    }

}
