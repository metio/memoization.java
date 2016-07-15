package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongUnaryOperator;

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
public class ConcurrentHashMapBasedLongUnaryOperatorMemoizerTest {

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
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedLongUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(
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
        final Map<Long, Long> precomputedValues = null;
        final LongUnaryOperator operator = input -> input;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(precomputedValues, operator);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullOperator() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongUnaryOperator operator = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL LongUnaryOperator - provide an actual LongUnaryOperator to fix this.");

        // then
        new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(precomputedValues, operator);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeOperator() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedLongUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsLong(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedLongUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsLong(123L);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123L,
                memoizer.viewCacheForTest().keySet().iterator().next().longValue());
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedOperator() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongUnaryOperator operator = Mockito.mock(LongUnaryOperator.class);

        // when
        final ConcurrentHashMapBasedLongUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        memoizer.applyAsLong(123L);
        Mockito.verify(operator).applyAsLong(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnOperatorResult() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongUnaryOperator operator = input -> input;

        // when
        final ConcurrentHashMapBasedLongUnaryOperatorMemoizer memoizer = new ConcurrentHashMapBasedLongUnaryOperatorMemoizer(
                precomputedValues, operator);

        // then
        Assert.assertEquals(123L, memoizer.applyAsLong(123L));
    }

}
