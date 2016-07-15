package de.xn__ho_hia.memoization.map;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.junit.Assert;
import org.junit.Test;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

/**
 *
 *
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class MapMemoizationTest {

    /**
    *
    */
    @Test
    public void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = MapMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized Supplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final BooleanSupplier memoize = MapMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized BooleanSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final DoubleSupplier memoize = MapMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized DoubleSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final IntSupplier memoize = MapMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized IntSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123L;

        // when
        final LongSupplier memoize = MapMemoization.memoize(supplier);

        // then
        Assert.assertNotNull("Memoized LongSupplier is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = MapMemoization.memoize(function);

        // then
        Assert.assertNotNull("Memoized Function is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiFunction() {
        // given
        final BiFunction<String, String, String> bifunction = (a, b) -> "test";

        // when
        final BiFunction<String, String, String> memoize = MapMemoization.memoize(bifunction);

        // then
        Assert.assertNotNull("Memoized BiFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized Consumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<String, String> biConsumer = (first, second) -> System.out.println(first + second);

        // when
        final BiConsumer<String, String> memoize = MapMemoization.memoize(biConsumer);

        // then
        Assert.assertNotNull("Memoized BiConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ObjDoubleConsumer<String> memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized ObjDoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final DoubleConsumer memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized DoubleConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldIntMemoizeConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final IntConsumer memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized IntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final LongConsumer memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized LongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjIntConsumer() {
        // given
        final ObjIntConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ObjIntConsumer<String> memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized ObjIntConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeObjLongConsumer() {
        // given
        final ObjLongConsumer<String> consumer = (first, second) -> System.out.println(first + second);

        // when
        final ObjLongConsumer<String> memoize = MapMemoization.memoize(consumer);

        // then
        Assert.assertNotNull("Memoized ObjLongConsumer is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = input -> true;

        // when
        final Predicate<String> memoize = MapMemoization.memoize(predicate);

        // then
        Assert.assertNotNull("Memoized Predicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<String, String> predicate = (first, second) -> true;

        // when
        final BiPredicate<String, String> memoize = MapMemoization.memoize(predicate);

        // then
        Assert.assertNotNull("Memoized BiPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = input -> true;

        // when
        final DoublePredicate memoize = MapMemoization.memoize(predicate);

        // then
        Assert.assertNotNull("Memoized DoublePredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = input -> true;

        // when
        final IntPredicate memoize = MapMemoization.memoize(predicate);

        // then
        Assert.assertNotNull("Memoized IntPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = input -> true;

        // when
        final LongPredicate memoize = MapMemoization.memoize(predicate);

        // then
        Assert.assertNotNull("Memoized LongPredicate is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator operator = (first, second) -> first + second;

        // when
        final DoubleBinaryOperator memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized DoubleBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator operator = (first, second) -> first + second;

        // when
        final IntBinaryOperator memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized IntBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator operator = (first, second) -> first + second;

        // when
        final LongBinaryOperator memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized LongBinaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator operator = input -> 123.456D;

        // when
        final DoubleUnaryOperator memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized DoubleUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator operator = input -> 123;

        // when
        final IntUnaryOperator memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized IntUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator operator = input -> 123L;

        // when
        final LongUnaryOperator memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized LongUnaryOperator is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction operator = input -> 123;

        // when
        final DoubleToIntFunction memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized DoubleToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction operator = input -> 123L;

        // when
        final DoubleToLongFunction memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized DoubleToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction operator = input -> 123.456D;

        // when
        final IntToDoubleFunction memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized IntToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction operator = input -> 123L;

        // when
        final IntToLongFunction memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized IntToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction operator = input -> 123D;

        // when
        final LongToDoubleFunction memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized LongToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction operator = input -> 123;

        // when
        final LongToIntFunction memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized LongToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> operator = Double::parseDouble;

        // when
        final ToDoubleFunction<String> memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized ToDoubleFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> operator = Integer::parseInt;

        // when
        final ToIntFunction<String> memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized ToIntFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> operator = Long::parseLong;

        // when
        final ToLongFunction<String> memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized ToLongFunction is NULL", memoize);
    }

    /**
    *
    */
    @Test
    public void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<String, String> operator = (first, second) -> 123.456D;

        // when
        final ToDoubleBiFunction<String, String> memoize = MapMemoization.memoize(operator);

        // then
        Assert.assertNotNull("Memoized ToDoubleBiFunction is NULL", memoize);
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
        final Constructor<MapMemoization> constructor = MapMemoization.class.getDeclaredConstructor();

        // when
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // then
        Assert.assertTrue("Constructor is not private", isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
