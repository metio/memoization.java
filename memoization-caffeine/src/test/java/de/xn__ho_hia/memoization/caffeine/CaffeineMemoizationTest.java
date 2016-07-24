package de.xn__ho_hia.memoization.caffeine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class CaffeineMemoizationTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;

        // when
        final DoubleToIntFunction memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;

        // when
        final DoubleToLongFunction memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();

        // when
        final DoubleUnaryOperator memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator function = (first, second) -> first + second;

        // when
        final DoubleBinaryOperator memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;

        // when
        final ToDoubleFunction<String> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;

        // when
        final ToIntFunction<String> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;

        // when
        final ToLongFunction<String> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
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
        final Constructor<CaffeineMemoization> constructor = CaffeineMemoization.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assert.assertTrue("Constructor is not private", isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
