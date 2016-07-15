package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntUnaryOperator;

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
public class ConcurrentHashMapBasedIntUnaryOperatorMemoizerTest {

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
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedIntUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(
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
        final Map<Integer, Integer> precomputedValues = null;
        final IntUnaryOperator operator = input -> input;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(precomputedValues, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntUnaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL IntUnaryOperator - provide an actual IntUnaryOperator to fix this.");

        // then
        new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(precomputedValues, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedIntUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsInt(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedIntUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsInt(123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123,
                memoizer.viewCacheForTest().keySet().iterator().next().intValue());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntUnaryOperator operator = Mockito.mock(IntUnaryOperator.class);

        // when
        final ConcurrentHashMapBasedIntUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsInt(123);
        Mockito.verify(operator).applyAsInt(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedIntUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedIntUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt(123));
    }

}
