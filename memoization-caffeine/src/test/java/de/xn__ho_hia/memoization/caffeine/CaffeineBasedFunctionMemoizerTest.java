package de.xn__ho_hia.memoization.caffeine;

import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineBasedFunctionMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndFunction() {
        // given
        final Function<String, String> function = Function.identity();
        final Cache<String, String> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedFunctionMemoizer<String, String> memoizer = new CaffeineBasedFunctionMemoizer<>(cache,
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
        final Function<String, String> function = Function.identity();
        final Cache<String, String> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedFunctionMemoizer<String, String> memoizer = new CaffeineBasedFunctionMemoizer<>(cache,
                function);

        // then
        Assert.assertEquals("Memoized value does not match expectation", "test", memoizer.apply("test"));
    }

}
