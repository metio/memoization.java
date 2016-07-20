package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.cache.Cache;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.memoization.jcache.JCacheBasedSupplierMemoizer;
import de.xn__ho_hia.memoization.jcache.JCacheMemoization;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheBasedSupplierMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";
        final Supplier<String> keySupplier = () -> "key";
        try (final Cache<String, String> cache = JCacheMemoization.createCache(Function.class.getSimpleName(),
                JCacheMemoization.supplierFactory(supplier))) {
            // when
            final JCacheBasedSupplierMemoizer<String, String> loader = new JCacheBasedSupplierMemoizer<>(cache,
                    keySupplier);

            // then
            Assert.assertEquals("Memoized value does not match expectation", "test", loader.get());
        }
    }

}
