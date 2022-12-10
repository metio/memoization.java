/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.jool;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import org.junit.jupiter.api.Disabled;

import java.util.Map;

@Disabled("Chronicle does run with jigsaw modules yet")
class MemoizeJoolUsingCustomKeyFunctionAndChronicleMapCacheTest extends MemoizeJoolUsingCustomKeyFunctionAndCustomCacheTCK {

    @Override
    protected <K, V> Map<K, V> cache() {
        return (ChronicleMap<K, V>) ChronicleMapBuilder.of(Object.class, Object.class).create();
    }

}
