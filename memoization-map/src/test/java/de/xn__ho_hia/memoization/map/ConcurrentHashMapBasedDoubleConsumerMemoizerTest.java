package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleConsumer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.map.ConcurrentHashMapBasedDoubleConsumerMemoizer;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedDoubleConsumerMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptPreComputedValuesKeyFunctionAndConsumer() {
        // given
        final Map<Double, Double> precomputedValues = new HashMap<>();
        final DoubleConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedDoubleConsumerMemoizer memoizer = new ConcurrentHashMapBasedDoubleConsumerMemoizer(
                precomputedValues, consumer);

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
        final Map<Double, Double> precomputedValues = null;
        final DoubleConsumer consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedDoubleConsumerMemoizer(precomputedValues, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final Map<Double, Double> precomputedValues = new HashMap<>();
        final DoubleConsumer consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentHashMapBasedDoubleConsumerMemoizer(precomputedValues, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Map<Double, Double> precomputedValues = new HashMap<>();
        final DoubleConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedDoubleConsumerMemoizer memoizer = new ConcurrentHashMapBasedDoubleConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123.456D);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Double, Double> precomputedValues = new HashMap<>();
        final DoubleConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedDoubleConsumerMemoizer memoizer = new ConcurrentHashMapBasedDoubleConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123.456D);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", 123.456D,
                memoizer.viewCacheForTest().keySet().iterator().next().doubleValue(), 0.0D);
        Assert.assertEquals("Memoization value does not match expectations", 123.456D,
                memoizer.viewCacheForTest().values().iterator().next().doubleValue(), 0.0D);
    }

    /**
    *
    */
    @Test
    public void shouldUseCallWrappedConsumer() {
        // given
        final Map<Double, Double> precomputedValues = new HashMap<>();
        final DoubleConsumer consumer = Mockito.mock(DoubleConsumer.class);

        // when
        final ConcurrentHashMapBasedDoubleConsumerMemoizer memoizer = new ConcurrentHashMapBasedDoubleConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123.456D);
        Mockito.verify(consumer).accept(123.456D);
    }

}
