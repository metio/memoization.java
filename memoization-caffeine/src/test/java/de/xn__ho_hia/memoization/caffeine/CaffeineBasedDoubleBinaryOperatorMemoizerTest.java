package de.xn__ho_hia.memoization.caffeine;

import java.util.function.DoubleBinaryOperator;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineBasedDoubleBinaryOperatorMemoizerTest {

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator operator = (first, second) -> first + second;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final Cache<String, Double> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleBinaryOperatorMemoizer<String> memoizer = new CaffeineBasedDoubleBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldCallOperator() {
        // given
        final DoubleBinaryOperator operator = (first, second) -> first + second;
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final Cache<String, Double> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedDoubleBinaryOperatorMemoizer<String> memoizer = new CaffeineBasedDoubleBinaryOperatorMemoizer<>(
                cache, keyFunction, operator);

        // then
        Assert.assertEquals("Memoized value does not match expectation", 580.245D,
                memoizer.applyAsDouble(123.456D, 456.789D), 0.0D);
    }

}
