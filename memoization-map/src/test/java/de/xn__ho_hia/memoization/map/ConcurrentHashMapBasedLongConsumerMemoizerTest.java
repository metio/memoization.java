package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongConsumer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.map.ConcurrentHashMapBasedLongConsumerMemoizer;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedLongConsumerMemoizerTest {

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
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedLongConsumerMemoizer memoizer = new ConcurrentHashMapBasedLongConsumerMemoizer(
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
        final Map<Long, Long> precomputedValues = null;
        final LongConsumer consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedLongConsumerMemoizer(precomputedValues, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongConsumer consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentHashMapBasedLongConsumerMemoizer(precomputedValues, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedLongConsumerMemoizer memoizer = new ConcurrentHashMapBasedLongConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123L);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongConsumer consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedLongConsumerMemoizer memoizer = new ConcurrentHashMapBasedLongConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123L);
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
    public void shouldUseCallWrappedConsumer() {
        // given
        final Map<Long, Long> precomputedValues = new HashMap<>();
        final LongConsumer consumer = Mockito.mock(LongConsumer.class);

        // when
        final ConcurrentHashMapBasedLongConsumerMemoizer memoizer = new ConcurrentHashMapBasedLongConsumerMemoizer(
                precomputedValues, consumer);

        // then
        memoizer.accept(123L);
        Mockito.verify(consumer).accept(123L);
    }

}
