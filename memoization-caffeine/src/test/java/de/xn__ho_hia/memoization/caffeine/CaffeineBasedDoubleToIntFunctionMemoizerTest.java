package de.xn__ho_hia.memoization.caffeine;

import java.util.function.DoubleToIntFunction;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineBasedDoubleToIntFunctionMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;
        final Cache<Double, Integer> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleToIntFunctionMemoizer memoizer = new CaffeineBasedDoubleToIntFunctionMemoizer(cache,
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
        final DoubleToIntFunction function = a -> 123;
        final Cache<Double, Integer> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleToIntFunctionMemoizer memoizer = new CaffeineBasedDoubleToIntFunctionMemoizer(cache,
                function);

        // then
        Assert.assertEquals("Memoized value does not match expectation", 123, memoizer.applyAsInt(123.456D));
    }

}
