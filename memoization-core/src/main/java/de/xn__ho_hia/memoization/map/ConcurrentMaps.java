/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMaps {

    private ConcurrentMaps() {
        // utility class
    }

    static final <KEY, VALUE> ConcurrentMap<KEY, VALUE> asConcurrentMap(final Map<KEY, VALUE> map) {
        if (ConcurrentMap.class.isAssignableFrom(map.getClass())) {
            return (ConcurrentMap<KEY, VALUE>) nullsafe(map);
        }
        return new ConcurrentHashMap<>(nullsafe(map));
    }

    @SuppressWarnings(CompilerWarnings.NLS)
    static final <MAP extends Map<?, ?>> MAP nullsafe(final MAP map) {
        return requireNonNull(map, "Provide an empty map instead of NULL.");
    }

}
