/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;

import javax.cache.Cache;

import de.xn__ho_hia.memoization.shared.MemoizationException;

abstract class AbstractJCacheBasedMemoizer<KEY, VALUE> {

    private final Cache<KEY, VALUE> cache;

    protected AbstractJCacheBasedMemoizer(final Cache<KEY, VALUE> cache) {
        this.cache = cache;
    }

    protected final VALUE invoke(final KEY key, final Function<KEY, VALUE> function) {
        try {
            return cache.invoke(key, (entry, args) -> {
                if (!entry.exists()) {
                    final VALUE value = function.apply(key);
                    entry.setValue(value);
                }
                return entry.getValue();
            });
        } catch (final RuntimeException exception) {
            throw new MemoizationException(exception);
        }
    }

}
