package de.xn__ho_hia.memoization.jcache;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.cache.configuration.Factory;
import javax.cache.integration.CacheLoader;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.memoization.jcache.JCacheMemoization;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JCacheMemoizationTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = JCacheMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = JCacheMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldReturnNonNullSupplierFactory() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Factory<CacheLoader<Object, String>> factory = JCacheMemoization.supplierFactory(supplier);

        // then
        Assert.assertNotNull("Cache loader factory for Supplier is NULL", factory);
    }

    /**
    *
    */
    @Test
    public void shouldReturnNonNullFunctionFactory() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Factory<CacheLoader<String, String>> factory = JCacheMemoization.functionFactory(function);

        // then
        Assert.assertNotNull("Cache loader factory for Function is NULL", factory);
    }

    /**
     * @throws NoSuchMethodException
     *             Reflection problemt
     * @throws IllegalAccessException
     *             Reflection problemt
     * @throws InvocationTargetException
     *             Reflection problemt
     * @throws InstantiationException
     *             Reflection problemt
     */
    @Test
    public void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // given
        final Constructor<JCacheMemoization> constructor = JCacheMemoization.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assert.assertTrue("Constructor is not private", isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
