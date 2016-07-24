package de.xn__ho_hia.memoization.caffeine;

import static de.xn__ho_hia.memoization.shared.MemoizationDefaults.hashCodeKeyFunction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
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
    public void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator function = (first, second) -> first + second;

        // when
        final IntBinaryOperator memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;

        // when
        final IntToDoubleFunction memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction function = a -> 123L;

        // when
        final IntToLongFunction memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator function = a -> 123;

        // when
        final IntUnaryOperator memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator function = (first, second) -> first + second;

        // when
        final LongBinaryOperator memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;

        // when
        final LongToDoubleFunction memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction function = a -> 123;

        // when
        final LongToIntFunction memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = (first, second) -> first.doubleValue()
                + second.doubleValue();

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunctionWithKeyFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = (first, second) -> first.doubleValue()
                + second.doubleValue();
        final BiFunction<Double, Double, String> keyFunction = hashCodeKeyFunction();

        // when
        final ToDoubleBiFunction<Double, Double> memoize = CaffeineMemoization.memoize(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = (first, second) -> first.intValue()
                + second.intValue();

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntBiFunctionWithKeyFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = (first, second) -> first.intValue()
                + second.intValue();
        final BiFunction<Integer, Integer, String> keyFunction = hashCodeKeyFunction();

        // when
        final ToIntBiFunction<Integer, Integer> memoize = CaffeineMemoization.memoize(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToIntBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = (first, second) -> first.longValue()
                + second.longValue();

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongBiFunctionWithKeyFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = (first, second) -> first.longValue()
                + second.longValue();
        final BiFunction<Long, Long, String> keyFunction = hashCodeKeyFunction();

        // when
        final ToLongBiFunction<Long, Long> memoize = CaffeineMemoization.memoize(function, keyFunction);

        // then
        Assert.assertNotNull("Memoized ToLongBiFunction is NULL", memoize);
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
