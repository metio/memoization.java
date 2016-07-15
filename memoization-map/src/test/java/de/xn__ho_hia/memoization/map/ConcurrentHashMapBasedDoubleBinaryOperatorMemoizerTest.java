package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedDoubleBinaryOperatorMemoizerTest {

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
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(
                precomputedValues, keyFunction, operator);

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
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(precomputedValues, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = null;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Provide a key function, might just be 'MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(precomputedValues, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL DoubleBinaryOperator - provide an actual DoubleBinaryOperator to fix this.");

        // then
        new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(precomputedValues, keyFunction, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(
                precomputedValues, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123.456D, 789.123D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(
                precomputedValues, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D, 789D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123.0 789.0",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 912D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = Mockito.mock(DoubleBinaryOperator.class);

        // when
        final ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(
                precomputedValues, keyFunction, operator);

        // then
        memoizer.applyAsDouble(123D, 789D);
        Mockito.verify(operator).applyAsDouble(123D, 789D);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final Map<String, Double> precomputedValues = new HashMap<>();
        final DoubleBinaryFunction<String> keyFunction = (first, second) -> first + " " + second;
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<String> memoizer = new ConcurrentHashMapBasedDoubleBinaryOperatorMemoizer<>(
                precomputedValues, keyFunction, operator);

        // then
        Assert.assertEquals(912D, memoizer.applyAsDouble(123D, 789D), 0.0D);
    }

}
