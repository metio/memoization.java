package de.xn__ho_hia.memoization.caffeine;

import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineBasedFunctionMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldAcceptCacheAndFunction() {
        // given
        final Function<String, String> function = Function.identity();
        final Cache<String, String> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedFunctionMemoizer<String, String> memoizer = new CaffeineBasedFunctionMemoizer<>(cache,
                function);

        // then
        Assert.assertNotNull("Memoizer is NULL", memoizer);
    }

    /**
    *
    */
    @Test
    public void shouldCallFunction() {
        // given
        final Function<String, String> function = Function.identity();
        final Cache<String, String> cache = Caffeine.newBuilder().build();

        // when
        final CaffeineBasedFunctionMemoizer<String, String> memoizer = new CaffeineBasedFunctionMemoizer<>(cache,
                function);

        // then
        Assert.assertEquals("Memoized value does not match expectation", "test", memoizer.apply("test"));
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldRejectNullValues() {
        // given
        final Function<String, String> function = Function.identity();
        final Cache<String, String> cache = Mockito.mock(Cache.class);
        BDDMockito.given(cache.get(Matchers.any(), Matchers.any())).willReturn(null);
        final CaffeineBasedFunctionMemoizer<String, String> memoizer = new CaffeineBasedFunctionMemoizer<>(cache,
                function);

        // when
        thrown.expect(MemoizationException.class);
        thrown.expectMessage("The calculated value is NULL");

        // then
        memoizer.apply("test");
    }

}
