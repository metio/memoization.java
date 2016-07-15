package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleToIntFunction;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedDoubleToIntFunctionMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptPreComputedValuesKeyFunctionAndOperator() {
        // given
        final Map<Double, Integer> precomputedValues = new HashMap<>();
        final DoubleToIntFunction operator = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(
                precomputedValues, operator);

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
        final Map<Double, Integer> precomputedValues = null;
        final DoubleToIntFunction operator = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(precomputedValues, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final Map<Double, Integer> precomputedValues = new HashMap<>();
        final DoubleToIntFunction operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL DoubleToIntFunction - provide an actual DoubleToIntFunction to fix this.");

        // then
        new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(precomputedValues, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final Map<Double, Integer> precomputedValues = new HashMap<>();
        final DoubleToIntFunction operator = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsInt(123.456D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Double, Integer> precomputedValues = new HashMap<>();
        final DoubleToIntFunction operator = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsInt(123D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123D,
                memoizer.viewCacheForTest().keySet().iterator().next().doubleValue(), 0.0D);
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final Map<Double, Integer> precomputedValues = new HashMap<>();
        final DoubleToIntFunction operator = Mockito.mock(DoubleToIntFunction.class);

        // when
        final ConcurrentHashMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsInt(123D);
        Mockito.verify(operator).applyAsInt(123D);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final Map<Double, Integer> precomputedValues = new HashMap<>();
        final DoubleToIntFunction operator = input -> 123;

        // when
        final ConcurrentHashMapBasedDoubleToIntFunctionMemoizer memoizer = new ConcurrentHashMapBasedDoubleToIntFunctionMemoizer(
                precomputedValues, operator);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt(123D));
    }

}
