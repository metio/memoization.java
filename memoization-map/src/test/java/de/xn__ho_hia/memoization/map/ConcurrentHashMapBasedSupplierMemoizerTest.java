package de.xn__ho_hia.memoization.map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 * Unit tests for ConcurrentHashMapBasedSupplierMemoizer.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedSupplierMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that a new instance can be created, given that pre-computed values, a key-supplier and the actual value
     * supplier to memoize is present. If any of those values is <code>null</code>, an exception should be thrown.
     */
    @Test
    public void shouldAcceptPreComputedValuesKeySupplierAndValueSupplier() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer = new ConcurrentHashMapBasedSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

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
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeySupplier() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = null;
        final Supplier<String> supplier = () -> "value";

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");

        // then
        new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullValueSupplier() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");

        // then
        new ConcurrentHashMapBasedSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    public void shouldMemoizeSuppliedValue() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer = new ConcurrentHashMapBasedSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertEquals("Memoized value does not match expectations", "value", memoizer.get());
    }

    /**
     *
     */
    @Test
    public void shouldUseSuppliedKey() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = () -> "value";

        // when
        final ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer = new ConcurrentHashMapBasedSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertTrue("Cache is not empty before memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoized value does not match expectations", "value", memoizer.get()); // trigger once
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldTriggerOnce() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final Supplier<String> supplier = mock(Supplier.class);
        given(supplier.get()).willReturn("mocked");

        // when
        final ConcurrentHashMapBasedSupplierMemoizer<String, String> memoizer = new ConcurrentHashMapBasedSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertEquals("Memoized value does not match expectations", "mocked", memoizer.get()); // triggers
        Assert.assertEquals("Memoized value does not match expectations", "mocked", memoizer.get()); // memoized
        Assert.assertEquals("Memoized value does not match expectations", "mocked", memoizer.get()); // memoized
        Assert.assertEquals("Memoized value does not match expectations", "mocked", memoizer.get()); // memoized
        verify(supplier, times(1)).get(); // real supplier triggered once, all other calls were memoized
    }

}
