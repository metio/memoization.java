package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

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
public class ConcurrentHashMapBasedToIntFunctionMemoizerTest {

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
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final ToIntFunction<String> function = input -> 123;

        // when
        final ConcurrentHashMapBasedToIntFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToIntFunctionMemoizer<>(
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
        final Map<String, Integer> precomputedValues = null;
        final ToIntFunction<String> function = input -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedToIntFunctionMemoizer<>(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final ToIntFunction<String> function = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToIntFunction - provide an actual ToIntFunction to fix this.");

        // then
        new ConcurrentHashMapBasedToIntFunctionMemoizer<>(precomputedValues, function);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final ToIntFunction<String> function = input -> 123;

        // when
        final ConcurrentHashMapBasedToIntFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToIntFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsInt("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final ToIntFunction<String> function = input -> 123;

        // when
        final ConcurrentHashMapBasedToIntFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToIntFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsInt("123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "123",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final ToIntFunction<String> function = Mockito.mock(ToIntFunction.class);

        // when
        final ConcurrentHashMapBasedToIntFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToIntFunctionMemoizer<>(
                precomputedValues, function);

        // then
        memoizer.applyAsInt("123");
        Mockito.verify(function).applyAsInt("123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnFunctionResult() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final ToIntFunction<String> function = input -> 123;

        // when
        final ConcurrentHashMapBasedToIntFunctionMemoizer<String> memoizer = new ConcurrentHashMapBasedToIntFunctionMemoizer<>(
                precomputedValues, function);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt("123"));
    }

}
