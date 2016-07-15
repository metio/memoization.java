package de.xn__ho_hia.memoization.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ConcurrentHashMapBasedConsumerMemoizerTest {

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
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedConsumerMemoizer<>(
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
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide an empty map instead of NULL in case you don't have any precomputed values.");

        // then
        new ConcurrentHashMapBasedConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullKeyFunction() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = null;
        final Consumer<String> consumer = System.out::println;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Provide a key function, might just be 'Function.identity()'.");

        // then
        new ConcurrentHashMapBasedConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
     *
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldRequireNonNullConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = null;

        // when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");

        // then
        new ConcurrentHashMapBasedConsumerMemoizer<>(precomputedValues, keyFunction, consumer);
    }

    /**
     *
     */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Map<String, String> precomputedValues = new HashMap<>();
        final Function<String, String> keyFunction = input -> "key";
        final Consumer<String> consumer = System.out::println;

        // when
        final ConcurrentHashMapBasedConsumerMemoizer<String, String> memoizer = new ConcurrentHashMapBasedConsumerMemoizer<>(
                precomputedValues, keyFunction, consumer);

        // then
        memoizer.accept("test");
    }

}
