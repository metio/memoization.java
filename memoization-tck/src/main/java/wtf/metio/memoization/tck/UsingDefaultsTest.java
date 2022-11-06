package wtf.metio.memoization.tck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

public abstract class UsingDefaultsTest {

    @Test
    final void shouldMemoizeFunction() {
        // given
        final Function<String, String> function = a -> "test";

        // when
        final Function<String, String> memoize = function(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(Function<INPUT, OUTPUT> function);

    @Test
    final void shouldMemoizeIntFunction() {
        // given
        final IntFunction<String> function = a -> "test";

        // when
        final IntFunction<String> memoize = intFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <OUTPUT> IntFunction<OUTPUT> intFunction(IntFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeLongFunction() {
        // given
        final LongFunction<String> function = a -> "test";

        // when
        final LongFunction<String> memoize = longFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <OUTPUT> LongFunction<OUTPUT> longFunction(LongFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeDoubleFunction() {
        // given
        final DoubleFunction<String> function = a -> "test";

        // when
        final DoubleFunction<String> memoize = doubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(DoubleFunction<OUTPUT> function);

    @Test
    final void shouldMemoizeDoubleToIntFunction() {
        // given
        final DoubleToIntFunction function = a -> 123;

        // when
        final DoubleToIntFunction memoize = doubleToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoubleToIntFunction doubleToIntFunction(DoubleToIntFunction function);

    @Test
    final void shouldMemoizeDoubleToLongFunction() {
        // given
        final DoubleToLongFunction function = a -> 123L;

        // when
        final DoubleToLongFunction memoize = doubleToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoubleToLongFunction doubleToLongFunction(DoubleToLongFunction function);

    @Test
    final void shouldMemoizeDoubleUnaryOperator() {
        // given
        final DoubleUnaryOperator function = DoubleUnaryOperator.identity();

        // when
        final DoubleUnaryOperator memoize = doubleUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoubleUnaryOperator doubleUnaryOperator(DoubleUnaryOperator operator);

    @Test
    final void shouldMemoizeDoubleBinaryOperator() {
        // given
        final DoubleBinaryOperator function = Double::sum;

        // when
        final DoubleBinaryOperator memoize = doubleBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoubleBinaryOperator doubleBinaryOperator(DoubleBinaryOperator operator);

    @Test
    final void shouldMemoizeIntBinaryOperator() {
        // given
        final IntBinaryOperator function = Integer::sum;

        // when
        final IntBinaryOperator memoize = intBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntBinaryOperator intBinaryOperator(IntBinaryOperator operator);

    @Test
    final void shouldMemoizeIntToDoubleFunction() {
        // given
        final IntToDoubleFunction function = a -> 123D;

        // when
        final IntToDoubleFunction memoize = intToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntToDoubleFunction intToDoubleFunction(IntToDoubleFunction function);

    @Test
    final void shouldMemoizeIntToLongFunction() {
        // given
        final IntToLongFunction function = a -> 123L;

        // when
        final IntToLongFunction memoize = intToLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntToLongFunction intToLongFunction(IntToLongFunction function);

    @Test
    final void shouldMemoizeIntUnaryOperator() {
        // given
        final IntUnaryOperator function = a -> 123;

        // when
        final IntUnaryOperator memoize = intUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntUnaryOperator intUnaryOperator(IntUnaryOperator operator);

    @Test
    final void shouldMemoizeLongBinaryOperator() {
        // given
        final LongBinaryOperator function = Long::sum;

        // when
        final LongBinaryOperator memoize = longBinaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongBinaryOperator longBinaryOperator(LongBinaryOperator operator);

    @Test
    final void shouldMemoizeLongToDoubleFunction() {
        // given
        final LongToDoubleFunction function = a -> 123.456D;

        // when
        final LongToDoubleFunction memoize = longToDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongToDoubleFunction longToDoubleFunction(LongToDoubleFunction function);

    @Test
    final void shouldMemoizeLongToIntFunction() {
        // given
        final LongToIntFunction function = a -> 123;

        // when
        final LongToIntFunction memoize = longToIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongToIntFunction longToIntFunction(LongToIntFunction function);

    @Test
    final void shouldMemoizeLongUnaryOperator() {
        // given
        final LongUnaryOperator function = a -> 123L;

        // when
        final LongUnaryOperator memoize = longUnaryOperator(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongUnaryOperator longUnaryOperator(LongUnaryOperator operator);

    @Test
    final void shouldMemoizeSupplier() {
        // given
        final Supplier<String> supplier = () -> "test";

        // when
        final Supplier<String> memoize = supplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <OUTPUT> Supplier<OUTPUT> supplier(Supplier<OUTPUT> supplier);

    @Test
    final void shouldMemoizeBooleanSupplier() {
        // given
        final BooleanSupplier supplier = () -> true;

        // when
        final BooleanSupplier memoize = booleanSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract BooleanSupplier booleanSupplier(BooleanSupplier supplier);

    @Test
    final void shouldMemoizeDoubleSupplier() {
        // given
        final DoubleSupplier supplier = () -> 123.456D;

        // when
        final DoubleSupplier memoize = doubleSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoubleSupplier doubleSupplier(DoubleSupplier supplier);

    @Test
    final void shouldMemoizeIntSupplier() {
        // given
        final IntSupplier supplier = () -> 123;

        // when
        final IntSupplier memoize = intSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntSupplier intSupplier(IntSupplier supplier);

    @Test
    final void shouldMemoizeLongSupplier() {
        // given
        final LongSupplier supplier = () -> 123L;

        // when
        final LongSupplier memoize = longSupplier(supplier);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongSupplier longSupplier(LongSupplier supplier);

    @Test
    final void shouldMemoizeToDoubleBiFunction() {
        // given
        final ToDoubleBiFunction<Double, Double> function = Double::sum;

        // when
        final ToDoubleBiFunction<Double, Double> memoize = toDoubleBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(
            ToDoubleBiFunction<FIRST, SECOND> function);

    @Test
    final void shouldMemoizeToIntBiFunction() {
        // given
        final ToIntBiFunction<Integer, Integer> function = Integer::sum;

        // when
        final ToIntBiFunction<Integer, Integer> memoize = toIntBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(
            ToIntBiFunction<FIRST, SECOND> function);

    @Test
    final void shouldMemoizeToLongBiFunction() {
        // given
        final ToLongBiFunction<Long, Long> function = Long::sum;

        // when
        final ToLongBiFunction<Long, Long> memoize = toLongBiFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(
            ToLongBiFunction<FIRST, SECOND> function);

    @Test
    final void shouldMemoizeToDoubleFunction() {
        // given
        final ToDoubleFunction<String> function = Double::parseDouble;

        // when
        final ToDoubleFunction<String> memoize = toDoubleFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(ToDoubleFunction<INPUT> function);

    @Test
    final void shouldMemoizeToIntFunction() {
        // given
        final ToIntFunction<String> function = Integer::parseInt;

        // when
        final ToIntFunction<String> memoize = toIntFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> ToIntFunction<INPUT> toIntFunction(ToIntFunction<INPUT> function);

    @Test
    final void shouldMemoizeToLongFunction() {
        // given
        final ToLongFunction<String> function = Long::parseLong;

        // when
        final ToLongFunction<String> memoize = toLongFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> ToLongFunction<INPUT> toLongFunction(ToLongFunction<INPUT> function);

    @Test
    final void shouldMemoizePredicate() {
        // given
        final Predicate<String> predicate = a -> true;

        // when
        final Predicate<String> memoize = predicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> Predicate<INPUT> predicate(Predicate<INPUT> predicate);

    @Test
    final void shouldMemoizeLongPredicate() {
        // given
        final LongPredicate predicate = a -> true;

        // when
        final LongPredicate memoize = longPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongPredicate longPredicate(LongPredicate predicate);

    @Test
    final void shouldMemoizeIntPredicate() {
        // given
        final IntPredicate predicate = a -> true;

        // when
        final IntPredicate memoize = intPredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntPredicate intPredicate(IntPredicate predicate);

    @Test
    final void shouldMemoizeDoublePredicate() {
        // given
        final DoublePredicate predicate = a -> true;

        // when
        final DoublePredicate memoize = doublePredicate(predicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoublePredicate doublePredicate(DoublePredicate predicate);

    @Test
    final void shouldMemoizeConsumer() {
        // given
        final Consumer<String> consumer = System.out::println;

        // when
        final Consumer<String> memoize = consumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> Consumer<INPUT> consumer(Consumer<INPUT> consumer);

    @Test
    final void shouldMemoizeDoubleConsumer() {
        // given
        final DoubleConsumer consumer = System.out::println;

        // when
        final DoubleConsumer memoize = doubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract DoubleConsumer doubleConsumer(DoubleConsumer consumer);

    @Test
    final void shouldMemoizeIntConsumer() {
        // given
        final IntConsumer consumer = System.out::println;

        // when
        final IntConsumer memoize = intConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract IntConsumer intConsumer(IntConsumer consumer);

    @Test
    final void shouldMemoizeLongConsumer() {
        // given
        final LongConsumer consumer = System.out::println;

        // when
        final LongConsumer memoize = longConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract LongConsumer longConsumer(LongConsumer consumer);

    @Test
    final void shouldMemoizeBiPredicate() {
        // given
        final BiPredicate<Long, Long> biPredicate = (first, second) -> true;

        // when
        final BiPredicate<Long, Long> memoize = biPredicate(biPredicate);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(BiPredicate<FIRST, SECOND> predicate);

    @Test
    final void shouldMemoizeBiFunction() {
        // given
        final BiFunction<Long, Long, String> function = (first, second) -> "test";

        // when
        final BiFunction<Long, Long, String> memoize = biFunction(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(
            BiFunction<FIRST, SECOND, OUTPUT> biFunction);

    @Test
    final void shouldMemoizeBiConsumer() {
        // given
        final BiConsumer<Long, Long> function = (first, second) -> System.out.println(first + " " + second);

        // when
        final BiConsumer<Long, Long> memoize = biConsumer(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(
            BiConsumer<FIRST, SECOND> biConsumer);

    @Test
    final void shouldMemoizeObjDoubleConsumer() {
        // given
        final ObjDoubleConsumer<Long> consumer = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjDoubleConsumer<Long> memoize = objDoubleConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(ObjDoubleConsumer<INPUT> consumer);

    @Test
    final void shouldMemoizeObjIntConsumer() {
        // given
        final ObjIntConsumer<Long> consumer = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjIntConsumer<Long> memoize = objIntConsumer(consumer);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> ObjIntConsumer<INPUT> objIntConsumer(ObjIntConsumer<INPUT> consumer);

    @Test
    final void shouldMemoizeObjLongConsumer() {
        // given
        final ObjLongConsumer<Long> function = (first, second) -> System.out.println(first + " " + second);

        // when
        final ObjLongConsumer<Long> memoize = objLongConsumer(function);

        // then
        Assertions.assertNotNull(memoize);
    }

    protected abstract <INPUT> ObjLongConsumer<INPUT> objLongConsumer(ObjLongConsumer<INPUT> consumer);

}
