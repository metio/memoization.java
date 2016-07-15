package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleFunction;

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
public class ConcurrentHashMapBasedToDoubleFunctionMemoizerTest {

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
        final Map<String, Double> precomputedValues = new HashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentHashMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(
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
        final Map<String, Double> precomputedValues = null;
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final ToDoubleFunction<String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");

        // then
        new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentHashMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentHashMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble("123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123.456D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final ToDoubleFunction<String> function = Mockito.mock(ToDoubleFunction.class);

        // when
        final ConcurrentHashMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsDouble("123");
        Mockito.verify(function).applyAsDouble("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final ToDoubleFunction<String> function = input -> 123.456D;

        // when
        final ConcurrentHashMapBasedToDoubleFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToDoubleFunctionMemoizer<>(
                precomputedValues, function);

        // then
        Assert.assertEquals(123.456D, memoizer.applyAsDouble("123"), 0.0D);
    }

}
