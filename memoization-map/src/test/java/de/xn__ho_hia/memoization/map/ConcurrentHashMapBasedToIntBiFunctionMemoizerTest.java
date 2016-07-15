package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

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
public class ConcurrentHashMapBasedToIntBiFunctionMemoizerTest {

    /** */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     *
     */
    @Test
    public void shouldAcceptPreComputedValuesKeyBiFunctionAndBiFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentHashMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

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
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyBiFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(
                "Cannot memoize a NULL ToIntBiFunction - provide an actual ToIntBiFunction to fix this.");

        // then
        new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(precomputedValues, keyFunction, biFunction);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentHashMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentHashMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
        Assert.assertFalse("Cache is still empty after memoization", memoizer.viewCacheForTest().isEmpty());
        Assert.assertEquals("Memoization key does not match expectations", "key",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertEquals("Memoization value does not match expectations", 123,
                memoizer.viewCacheForTest().values().iterator().next().intValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiFunction() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = Mockito.mock(ToIntBiFunction.class);

        // when
        final ConcurrentHashMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        memoizer.applyAsInt("123.456", "789.123");
        Mockito.verify(biFunction).applyAsInt("123.456", "789.123");
    }

    /**
    *
    */
    @Test
    public void shouldUseReturnBiFunctionResult() {
        // given
        final Map<String, Integer> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (a, b) -> "key";
        final ToIntBiFunction<String, String> biFunction = (a, b) -> 123;

        // when
        final ConcurrentHashMapBasedToIntBiFunctionMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedToIntBiFunctionMemoizer<>(
                precomputedValues, keyFunction, biFunction);

        // then
        Assert.assertEquals(123, memoizer.applyAsInt("123.456", "789.123"));
    }

}
