package de.xn__ho_hia.memoization.jsr107;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.function.Function;

import javax.cache.Cache;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import de.xn__ho_hia.memoization.shared.MemoizationException;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheBasedFunctionMemoizerTest {

    /** Captures expected exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = Function.identity();
        try (final Cache<String, String> cache = JCacheMemoization.createCache(Function.class.getSimpleName(),
                JCacheMemoization.functionFactory(function))) {
            // when
            final JCacheBasedFunctionMemoizer<String, String> loader = new JCacheBasedFunctionMemoizer<>(cache);

            // then
            Assert.assertEquals("Memoized value does not match expectation", "test", loader.apply("test"));
        }
    }

    /**
    *
    */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldWrapRuntimeExceptionInMemoizationException() {
        // given
        try (final Cache<String, String> cache = Mockito.mock(Cache.class)) {
            final JCacheBasedFunctionMemoizer<String, String> loader = new JCacheBasedFunctionMemoizer<>(cache);
            given(cache.get(any())).willThrow(RuntimeException.class);

            // when
            thrown.expect(MemoizationException.class);

            // then
            loader.apply("test");
        }
    }

}
