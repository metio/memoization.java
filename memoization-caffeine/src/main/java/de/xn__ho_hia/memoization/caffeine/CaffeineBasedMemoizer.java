/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.caffeine;

import java.util.Objects;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

class CaffeineBasedMemoizer<KEY, VALUE> {

    private final Cache<KEY, VALUE> cache;

    @SuppressWarnings(CompilerWarnings.NLS)
    CaffeineBasedMemoizer(final Cache<KEY, VALUE> cache) {
        this.cache = Objects.requireNonNull(cache, "Cannot use a NULL cache - provide an actual cache to fix this.");
    }

    protected final VALUE get(final KEY key, final Function<KEY, VALUE> mappingFunction) {
        return cache.get(key, mappingFunction);
    }

}
