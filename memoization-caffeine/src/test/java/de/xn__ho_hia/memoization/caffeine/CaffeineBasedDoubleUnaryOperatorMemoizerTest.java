package de.xn__ho_hia.memoization.caffeine;

import java.util.function.DoubleUnaryOperator;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineBasedDoubleUnaryOperatorMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator operator = DoubleUnaryOperator.identity();
        final Cache<Double, Double> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleUnaryOperatorMemoizer memoizer = new CaffeineBasedDoubleUnaryOperatorMemoizer(cache,
                operator);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldCallFunction() {
        // given
        final DoubleUnaryOperator operator = DoubleUnaryOperator.identity();
        final Cache<Double, Double> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleUnaryOperatorMemoizer memoizer = new CaffeineBasedDoubleUnaryOperatorMemoizer(cache,
                operator);

        // then
        Assert.assertEquals("Memoized value does not match expectation", 123.456D, memoizer.applyAsDouble(123.456D),
                0.0D);
    }

}
