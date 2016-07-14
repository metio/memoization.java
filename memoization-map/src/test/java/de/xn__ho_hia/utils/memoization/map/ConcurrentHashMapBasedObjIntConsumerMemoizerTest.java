package de.xn__ho_hia.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ObjIntConsumer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;
import de.xn__ho_hia.utils.memoization.shared.ObjIntFunction;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedObjIntConsumerMemoizerTest {

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
        final Map<String, String> precomputedValues = new HashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentHashMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

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
        final Map<String, String> precomputedValues = null;
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final ObjIntFunction<String, String> keyFunction = null;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Provide a key function, might just be 'MemoizationDefaults.objIntConsumerHashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentHashMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ConcurrentHashMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "test123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", "test123",
                memoizer.viewCacheForTest().values().iterator().next());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final ObjIntFunction<String, String> keyFunction = (first, second) -> first + second;
        final ObjIntConsumer<String> consumer = Mockito.mock(ObjIntConsumer.class);

        // when
        final ConcurrentHashMapBasedObjIntConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedObjIntConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

        // then
        memoizer.accept("test", 123);
        Mockito.verify(consumer).accept("test", 123);
    }

}
