package de.xn__ho_hia.memoization.guava;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class GuavaCacheBasedConsumerMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndKeyFunctionAndConsumer() {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Consumer<String> consumer = System.out::println;
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedConsumerMemoizer<String, String> memoizer = new GuavaCacheBasedConsumerMemoizer<>(cache,
                keyFunction, consumer);

        // then
        Assert.assertNotNull(memoizer);
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldConsumeGivenValue() {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Consumer<String> consumer = Mockito.mock(Consumer.class);
        final Cache<String, String> cache = CacheBuilder.newBuilder().build();

        // when
        final GuavaCacheBasedConsumerMemoizer<String, String> memoizer = new GuavaCacheBasedConsumerMemoizer<>(cache,
                keyFunction, consumer);

        // then
        memoizer.accept("value");
        Mockito.verify(consumer).accept("value");
    }

    /**
     * @throws ExecutionException
     *             Added for the call to 'cache.get(..)'.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapExecutionExceptionInMemoizationException() throws ExecutionException {
        // given
        final Function<String, String> keyFunction = Function.identity();
        final Consumer<String> consumer = System.out::println;
        final Cache<String, String> cache = Mockito.mock(Cache.class);
        given(cache.get(any(), any())).willThrow(ExecutionException.class);
        final GuavaCacheBasedConsumerMemoizer<String, String> memoizer = new GuavaCacheBasedConsumerMemoizer<>(cache,
                keyFunction, consumer);

        // when
        thrown.expect(MemoizationException.class);

        // then
        memoizer.accept("test");
    }

}
