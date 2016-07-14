package de.xn__ho_hia.utils.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoublePredicate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 */
@SuppressWarnings("nls")
public class ConcurrentHashMapBasedDoublePredicateMemoizerTest {

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
        final Map<Double, Boolean> precomputedValues = new HashMap<>();
        final DoublePredicate predicate = input -> true;

        // when
        final ConcurrentHashMapBasedDoublePredicateMemoizer memoizer = new ConcurrentHashMapBasedDoublePredicateMemoizer(
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
        final Map<Double, Boolean> precomputedValues = null;
        final DoublePredicate predicate = input -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedDoublePredicateMemoizer(precomputedValues, predicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("unused")
    public void shouldRequireNonNullPredicate() {
        // given
        final Map<Double, Boolean> precomputedValues = new HashMap<>();
        final DoublePredicate predicate = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");

        // then
        new ConcurrentHashMapBasedDoublePredicateMemoizer(precomputedValues, predicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings("static-method")
    public void shouldMemoizePredicateCall() {
        // given
        final Map<Double, Boolean> precomputedValues = new HashMap<>();
        final DoublePredicate predicate = input -> true;

        // when
        final ConcurrentHashMapBasedDoublePredicateMemoizer memoizer = new ConcurrentHashMapBasedDoublePredicateMemoizer(
                precomputedValues, predicate);

        // then
        Assert.assertTrue("Memoized value does not match expectations", memoizer.test(123.456D));
    }

}
