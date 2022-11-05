/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.Function;
import java.util.function.Predicate;

final class GuavaCacheBasedPredicateMemoizer<INPUT, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements Predicate<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Predicate<INPUT> predicate;

    GuavaCacheBasedPredicateMemoizer(
            final Cache<KEY, Boolean> cache,
            final Function<INPUT, KEY> keyFunction,
            final Predicate<INPUT> predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = predicate;
    }

    @Override
    public boolean test(final INPUT input) {
        final KEY key = keyFunction.apply(input);
        return get(key, givenKey -> predicate.test(input));
    }

}
