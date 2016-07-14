package de.xn__ho_hia.utils.memoization.map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for ConcurrentHashMapBasedDoubleSupplierMemoizer.
 */
@SuppressWarnings("nls")
public class ConcurrentHashMapBasedDoubleSupplierMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that a new instance can be created, given that pre-computed values, a key-supplier and the actual value
     * supplier to memoize is present. If any of those values is <code>null</code>, an exception should be thrown.
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldAcceptPreComputedValuesKeySupplierAndValueSupplier() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final ConcurrentHashMapBasedDoubleSupplierMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullPreComputedValues() {
        // given
        final Map<String, Double> precomputedValues = null;
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullKeySupplier() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = null;
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");

        // then
        new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullValueSupplier() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");

        // then
        new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(precomputedValues, keySupplier, supplier);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldMemoizeSuppliedValue() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final ConcurrentHashMapBasedDoubleSupplierMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertEquals("Memoized value does not match expectations", 123.456D, memoizer.getAsDouble(), 0.0D);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldUseSuppliedKey() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final ConcurrentHashMapBasedDoubleSupplierMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertTrue("Cache is not empty before memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoized value does not match expectations", 123.456D, memoizer.getAsDouble(), 0.0D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
    }

    /**
     *
     */
    @Test
    @SuppressWarnings({ "static-method", "boxing" })
    public void shouldTriggerOnce() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final Supplier<String> keySupplier = () -> "key";
        final DoubleSupplier supplier = mock(DoubleSupplier.class);
        given(supplier.getAsDouble()).willReturn(123.456D);

        // when
        final ConcurrentHashMapBasedDoubleSupplierMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleSupplierMemoizer<>(
                precomputedValues, keySupplier, supplier);

        // then
        Assert.assertEquals("Memoized value does not match expectations", 123.456D, memoizer.getAsDouble(), 0.0D); // triggers
        Assert.assertEquals("Memoized value does not match expectations", 123.456D, memoizer.getAsDouble(), 0.0D); // memoized
        Assert.assertEquals("Memoized value does not match expectations", 123.456D, memoizer.getAsDouble(), 0.0D); // memoized
        Assert.assertEquals("Memoized value does not match expectations", 123.456D, memoizer.getAsDouble(), 0.0D); // memoized
        verify(supplier, times(1)).getAsDouble(); // real supplier triggered once, all other calls were memoized
    }

}
