/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedBiFunctionMemoizerTest {

    /** */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    public void shouldAcceptPreComputedValuesKeyBiFunctionAndBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullPreComputedValues() {
        // given
        final Map<String, String> precomputedValues = null;
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL BiFunction - provide an actual BiFunction to fix this.");

        // then
        new ConcurrentHashMapBasedBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
    *
    */
    @Test
    public void shouldReturnGivenBiFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoizer.getBiFunction());
        Assert.assertSame("Memoized BiFunction is not the same", biFunction, memoizer.getBiFunction());
    }

    /**
    *
    */
    @Test
    public void shouldReturnGivenKeyFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Key function is NULL", memoizer.getKeyFunction());
        Assert.assertSame("Key function is not the same", keyFunction, memoizer.getKeyFunction());
    }

    /**
    *
    */
    @Test
    public void shouldReturnNonNullMemoizationFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final BiFunction<String, String, String> biFunction = (a, b) -> "value";

        // when
        final ConcurrentHashMapBasedBiFunctionMemoizer<String, String, String, String> memoizer = new ConcurrentHashMapBasedBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertNotNull("Memoizing function is NULL", memoizer.getMemoizingFunction());
    }

}
