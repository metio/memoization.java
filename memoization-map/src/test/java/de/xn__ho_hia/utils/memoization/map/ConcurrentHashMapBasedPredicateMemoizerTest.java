package de.xn__ho_hia.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 */
@SuppressWarnings("nls")
public class ConcurrentHashMapBasedPredicateMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    @SuppressWarnings("static-method")
    public void shouldAcceptPreComputedValuesAndPredicate() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final Predicate<String> predicate = input -> true;

        // when
        final ConcurrentHashMapBasedPredicateMemoizer<String> memoizer = new ConcurrentHashMapBasedPredicateMemoizer<>(
                precomputedValues, predicate);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullPreComputedValues() {
        // given
        final Map<String, Boolean> precomputedValues = null;
        final Predicate<String> predicate = input -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedPredicateMemoizer<>(precomputedValues, predicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullPredicate() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final Predicate<String> predicate = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");

        // then
        new ConcurrentHashMapBasedPredicateMemoizer<>(precomputedValues, predicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("static-method")
    public void shouldMemoizePredicateCall() {
        // given
        final Map<String, Boolean> precomputedValues = new HashMap<>();
        final Predicate<String> predicate = input -> true;

        // when
        final ConcurrentHashMapBasedPredicateMemoizer<String> memoizer = new ConcurrentHashMapBasedPredicateMemoizer<>(
                precomputedValues, predicate);

        // then
        Assert.assertTrue("Memoized value does not match expectations", memoizer.test("test"));
    }

}
