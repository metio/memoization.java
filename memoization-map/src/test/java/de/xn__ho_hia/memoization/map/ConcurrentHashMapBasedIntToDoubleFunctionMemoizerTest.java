package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntToDoubleFunction;

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
public class ConcurrentHashMapBasedIntToDoubleFunctionMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptPreComputedValuesAndFunction() {
        // given
        final Map<Integer, Double> precomputedValues = new HashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(
                precomputedValues, function);

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
        final Map<Integer, Double> precomputedValues = null;
        final IntToDoubleFunction function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<Integer, Double> precomputedValues = new HashMap<>();
        final IntToDoubleFunction function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL IntToDoubleFunction - provide an actual IntToDoubleFunction to fix this.");

        // then
        new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<Integer, Double> precomputedValues = new HashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Integer, Double> precomputedValues = new HashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble(123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123,
                memoizer.viewCacheForTest().keySet().iterator().next().intValue());
        Assert.assertEquals("Memoization value does not match expectations", 123D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<Integer, Double> precomputedValues = new HashMap<>();
        final IntToDoubleFunction function = Mockito.mock(IntToDoubleFunction.class);

        // when
        final ConcurrentHashMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble(123);
        Mockito.verify(function).applyAsDouble(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<Integer, Double> precomputedValues = new HashMap<>();
        final IntToDoubleFunction function = input -> 123;

        // when
        final ConcurrentHashMapBasedIntToDoubleFunctionMemoizer memoizer = new ConcurrentHashMapBasedIntToDoubleFunctionMemoizer(
                precomputedValues, function);

        // then
        Assert.assertEquals(123D, memoizer.applyAsDouble(123), 0.0D);
    }

}
