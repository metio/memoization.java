package de.xn__ho_hia.memoization.jsr107;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheFunctionBasedCacheLoaderTest {

    /**
    *
    */
    @Test
    public void shouldLoadKey() {
        // given
        final Function<String, String> function = Function.identity();

        // when
        final JCacheFunctionBasedCacheLoader<String, String> loader = new JCacheFunctionBasedCacheLoader<>(function);

        // then
        Assert.assertEquals("Loaded value does not match expectation", "test", loader.load("test"));
    }

    /**
    *
    */
    @Test
    public void shouldLoadAllKeys() {
        // given
        final Function<String, String> function = Function.identity();
        final List<String> keys = new ArrayList<>();
        keys.add("first");

        // when
        final JCacheFunctionBasedCacheLoader<String, String> loader = new JCacheFunctionBasedCacheLoader<>(function);
        final Map<String, String> loadedValues = loader.loadAll(keys);

        // then
        Assert.assertNotNull("Loaded values is NULL", loadedValues);
        Assert.assertEquals("Loaded key does not match expectation", "first", loadedValues.keySet().iterator().next());
        Assert.assertEquals("Loaded value does not match expectation", "first",
                loadedValues.values().iterator().next());
    }

}
