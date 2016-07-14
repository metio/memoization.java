package de.xn__ho_hia.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

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
public class ConcurrentHashMapBasedBiPredicateMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptPreComputedValuesKeyFunctionAndBiPredicate() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentHashMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiPredicateMemoizer<>(
                precomputedValues, keyFunction, biPredicate);

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
        final Map<String, Boolean> precomputedValues = null;
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedBiPredicateMemoizer<>(precomputedValues, keyFunction, biPredicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = null;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");

        // then
        new ConcurrentHashMapBasedBiPredicateMemoizer<>(precomputedValues, keyFunction, biPredicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullBiPredicate() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL BiPredicate - provide an actual BiPredicate to fix this.");

        // then
        new ConcurrentHashMapBasedBiPredicateMemoizer<>(precomputedValues, keyFunction, biPredicate);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicate() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentHashMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiPredicateMemoizer<>(
                precomputedValues, keyFunction, biPredicate);

        // then
        Assert.assertTrue(memoizer.test("first", "second"));
    }

    /**
    *
    */
    @Test
    public void shouldUseSetCacheKeyAndValue() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = (first, second) -> true;

        // when
        final ConcurrentHashMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiPredicateMemoizer<>(
                precomputedValues, keyFunction, biPredicate);

        // then
        memoizer.test("first", "second");
        Assert.assertEquals("Memoization key does not match expectations", "firstsecond",
                memoizer.viewCacheForTest().keySet().iterator().next());
        Assert.assertTrue("Memoization value does not match expectations",
                memoizer.viewCacheForTest().values().iterator().next().booleanValue());
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldUseCallWrappedBiPredicate() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final BiFunction<String, String, String> keyFunction = (first, second) -> first + second;
        final BiPredicate<String, String> biPredicate = Mockito.mock(BiPredicate.class);

        // when
        final ConcurrentHashMapBasedBiPredicateMemoizer<String, String, String> memoizer = new ConcurrentHashMapBasedBiPredicateMemoizer<>(
                precomputedValues, keyFunction, biPredicate);

        // then
        memoizer.test("first", "second");
        Mockito.verify(biPredicate).test("first", "second");
    }

}
