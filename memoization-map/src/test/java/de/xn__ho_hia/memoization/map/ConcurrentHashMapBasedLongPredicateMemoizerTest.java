package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongPredicate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.memoization.map.ConcurrentHashMapBasedLongPredicateMemoizer;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedLongPredicateMemoizerTest {

    /**
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptPreComputedValuesAndPredicate() {
        // given
        final Map<Long, Boolean> precomputedValues = new HashMap<>();
        final LongPredicate predicate = input -> true;

        // when
        final ConcurrentHashMapBasedLongPredicateMemoizer memoizer = new ConcurrentHashMapBasedLongPredicateMemoizer(
                precomputedValues, predicate);

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
        final Map<Long, Boolean> precomputedValues = null;
        final LongPredicate predicate = input -> true;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedLongPredicateMemoizer(precomputedValues, predicate);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullPredicate() {
        // given
        final Map<Long, Boolean> precomputedValues = new HashMap<>();
        final LongPredicate predicate = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");

        // then
        new ConcurrentHashMapBasedLongPredicateMemoizer(precomputedValues, predicate);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicateCall() {
        // given
        final Map<Long, Boolean> precomputedValues = new HashMap<>();
        final LongPredicate predicate = input -> true;

        // when
        final ConcurrentHashMapBasedLongPredicateMemoizer memoizer = new ConcurrentHashMapBasedLongPredicateMemoizer(
                precomputedValues, predicate);

        // then
        Assert.assertTrue("Memoized value does not match expectations", memoizer.test(123L));
    }

}
