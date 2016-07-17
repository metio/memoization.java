package de.xn__ho_hia.memoization.guava;

import java.util.function.Supplier;

import com.google.common.cache.CacheLoader;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 *
 */
public class SupplierBasedCacheLoaderTest {

    /**
     *
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @SuppressWarnings("static-method")
    @Test
    public void shouldRequireSupplierToConstruct() {
        // given
        final Supplier<String> supplier = () -> "test"; //$NON-NLS-1$

        // when
        final CacheLoader<String, String> cacheLoader = new SupplierBasedCacheLoader<>(supplier);

        // then
        Assert.assertNotNull(cacheLoader);
    }

    /**
     *
     */
    @SuppressWarnings("unused")
    @Test
    public void shouldDeclineNullSupplier() {
        // given
        final Supplier<String> supplier = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a supplier to load values into the cache!"); //$NON-NLS-1$

        // then
        new SupplierBasedCacheLoader<>(supplier);
    }

    /**
     * @throws Exception
     *             If the cache is unable to load the result.
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    public void shouldCallProvidedSupplierDuringLoad() throws Exception {
        // given
        final CacheLoader<String, String> cacheLoader = new SupplierBasedCacheLoader<>(() -> "value");

        // when
        final String loadedValue = cacheLoader.load("key");

        // then
        Assert.assertEquals("value", loadedValue);
    }

}