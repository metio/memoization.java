package de.xn__ho_hia.memoization.caffeine;

import java.util.function.DoubleToLongFunction;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineBasedDoubleToLongFunctionMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final Cache<Double, Long> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleToLongFunctionMemoizer memoizer = new CaffeineBasedDoubleToLongFunctionMemoizer(cache,
                function);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldCallFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;
        final Cache<Double, Long> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleToLongFunctionMemoizer memoizer = new CaffeineBasedDoubleToLongFunctionMemoizer(cache,
                function);

        // then
        Assert.assertEquals("Memoized value does not match expectation", 123L, memoizer.applyAsLong(123.456D));
    }

}
