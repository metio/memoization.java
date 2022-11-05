/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.MemoizationException;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

abstract class AbstractGuavaCacheBasedMemoizer<KEY, VALUE> {

    private final Cache<KEY, VALUE> cache;

    protected AbstractGuavaCacheBasedMemoizer(final Cache<KEY, VALUE> cache) {
        this.cache = cache;
    }

    protected final VALUE get(final KEY key, final Function<KEY, VALUE> function) {
        try {
            return cache.get(key, () -> function.apply(key));
        } catch (final ExecutionException exception) {
            throw new MemoizationException(exception);
        }
    }

}
