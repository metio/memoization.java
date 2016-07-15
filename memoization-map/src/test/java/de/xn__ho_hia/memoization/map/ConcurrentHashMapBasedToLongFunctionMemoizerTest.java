package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToLongFunction;

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
public class ConcurrentHashMapBasedToLongFunctionMemoizerTest {

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
        final Map<String, Long> precomputedValues = new HashMap<>();
        final ToLongFunction<String> function = input -> 123L;

        // when
        final ConcurrentHashMapBasedToLongFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToLongFunctionMemoizer<>(
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
        final Map<String, Long> precomputedValues = null;
        final ToLongFunction<String> function = input -> 123L;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedToLongFunctionMemoizer<>(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final ToLongFunction<String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToLongFunction - provide an actual ToLongFunction to fix this.");

        // then
        new ConcurrentHashMapBasedToLongFunctionMemoizer<>(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final ToLongFunction<String> function = input -> 123L;

        // when
        final ConcurrentHashMapBasedToLongFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToLongFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsLong("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final ToLongFunction<String> function = input -> 123L;

        // when
        final ConcurrentHashMapBasedToLongFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToLongFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsLong("123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123L,
                memoizer.viewCacheForTest().values().iterator().next().longValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final ToLongFunction<String> function = Mockito.mock(ToLongFunction.class);

        // when
        final ConcurrentHashMapBasedToLongFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToLongFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsLong("123");
        Mockito.verify(function).applyAsLong("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<String, Long> precomputedValues = new HashMap<>();
        final ToLongFunction<String> function = input -> 123L;

        // when
        final ConcurrentHashMapBasedToLongFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToLongFunctionMemoizer<>(
                precomputedValues, function);

        // then
        Assert.assertEquals(123L, memoizer.applyAsLong("123"));
    }

}
