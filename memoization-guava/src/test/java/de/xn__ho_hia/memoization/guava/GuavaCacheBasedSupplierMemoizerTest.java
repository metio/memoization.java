package de.xn__ho_hia.memoization.guava;

import java.util.function.Supplier;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 *
 */
public class GuavaCacheBasedSupplierMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    @SuppressWarnings("static-method")
    public void shouldAcceptLoadingCacheAndKeySupplier() {
        // given
        final Supplier<String> keySupplier = () -> "key"; //$NON-NLS-1$
        final Supplier<String> supplier = () -> "value"; //$NON-NLS-1$
        final LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .build(new SupplierBasedCacheLoader<>(supplier));

        // when
        final GuavaCacheBasedSupplierMemoizer<String, String> memoizer = new GuavaCacheBasedSupplierMemoizer<>(cache,
                keySupplier);

        // then
        Assert.assertNotNull(memoizer);
    }

}
