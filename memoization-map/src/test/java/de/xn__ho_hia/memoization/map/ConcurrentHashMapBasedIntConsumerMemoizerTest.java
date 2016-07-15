package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

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
public class ConcurrentHashMapBasedIntConsumerMemoizerTest {

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
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedIntConsumerMemoizer memoizer = new ConcurrentHashMapBasedIntConsumerMemoizer(
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
        final Map<Integer, Integer> precomputedValues = null;
        final IntConsumer consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedIntConsumerMemoizer(precomputedValues, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntConsumer consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentHashMapBasedIntConsumerMemoizer(precomputedValues, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedIntConsumerMemoizer memoizer = new ConcurrentHashMapBasedIntConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedIntConsumerMemoizer memoizer = new ConcurrentHashMapBasedIntConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123);
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
    public void shouldUseCallWrappedConsumer() {
        // given
        final Map<Integer, Integer> precomputedValues = new HashMap<>();
        final IntConsumer consumer = Mockito.mock(IntConsumer.class);

        // when
        final ConcurrentHashMapBasedIntConsumerMemoizer memoizer = new ConcurrentHashMapBasedIntConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123);
        Mockito.verify(consumer).accept(123);
    }

}
