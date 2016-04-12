package com.github.sebhoss.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConcurrentHashMapBasedSupplierMemoizerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldAcceptPreComputedValuesKeySupplierAndValueSupplier() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";
        ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer;

        // when
        memoizer = new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assert.assertNotNull(memoizer);
    }

    @Test
    public void shouldRequireNonNullPreComputedValues() {
        // given
        final Map<String, String> precomputedValues = null;
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    @Test
    public void shouldRequireNonNullKeySupplier() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = null;
        final Supplier<String> supplier = () -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a constant key supplier like this '() -> \"myValue\"'.");

        // then
        new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    @Test
    public void shouldRequireNonNullValueSupplier() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL supplier - provide an actual supplier to fix this.");

        // then
        new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    @Test
    public void shouldMemoizeSuppliedValue() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer = new
                ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assert.assertEquals("value", memoizer.get());
    }

    @Test
    public void shouldUseSuppliedKey() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer = new
                ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);

        // then
        Assert.assertTrue(memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("value", memoizer.get()); // trigger once
        Assert.assertFalse(memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("key", memoizer.viewCacheForTest().keySet().iterator().next());
    }

}
