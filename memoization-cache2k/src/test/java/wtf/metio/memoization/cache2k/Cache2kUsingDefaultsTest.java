package wtf.metio.memoization.cache2k;

import wtf.metio.memoization.tck.UsingDefaultsTest;

import java.util.function.*;

class Cache2kUsingDefaultsTest extends UsingDefaultsTest {

    @Override
    protected <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(final Function<INPUT, OUTPUT> function) {
        return Cache2kMemoize.function(function);
    }

    @Override
    protected <OUTPUT> IntFunction<OUTPUT> intFunction(final IntFunction<OUTPUT> function) {
        return Cache2kMemoize.intFunction(function);
    }

    @Override
    protected <OUTPUT> LongFunction<OUTPUT> longFunction(final LongFunction<OUTPUT> function) {
        return Cache2kMemoize.longFunction(function);
    }

    @Override
    protected <OUTPUT> DoubleFunction<OUTPUT> doubleFunction(final DoubleFunction<OUTPUT> function) {
        return Cache2kMemoize.doubleFunction(function);
    }

    @Override
    protected DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction function) {
        return Cache2kMemoize.doubleToIntFunction(function);
    }

    @Override
    protected DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction function) {
        return Cache2kMemoize.doubleToLongFunction(function);
    }

    @Override
    protected DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator operator) {
        return Cache2kMemoize.doubleUnaryOperator(operator);
    }

    @Override
    protected DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator operator) {
        return Cache2kMemoize.doubleBinaryOperator(operator);
    }

    @Override
    protected IntBinaryOperator intBinaryOperator(final IntBinaryOperator operator) {
        return Cache2kMemoize.intBinaryOperator(operator);
    }

    @Override
    protected IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction function) {
        return Cache2kMemoize.intToDoubleFunction(function);
    }

    @Override
    protected IntToLongFunction intToLongFunction(final IntToLongFunction function) {
        return Cache2kMemoize.intToLongFunction(function);
    }

    @Override
    protected IntUnaryOperator intUnaryOperator(final IntUnaryOperator operator) {
        return Cache2kMemoize.intUnaryOperator(operator);
    }

    @Override
    protected LongBinaryOperator longBinaryOperator(final LongBinaryOperator operator) {
        return Cache2kMemoize.longBinaryOperator(operator);
    }

    @Override
    protected LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction function) {
        return Cache2kMemoize.longToDoubleFunction(function);
    }

    @Override
    protected LongToIntFunction longToIntFunction(final LongToIntFunction function) {
        return Cache2kMemoize.longToIntFunction(function);
    }

    @Override
    protected LongUnaryOperator longUnaryOperator(final LongUnaryOperator operator) {
        return Cache2kMemoize.longUnaryOperator(operator);
    }

    @Override
    protected <OUTPUT> Supplier<OUTPUT> supplier(final Supplier<OUTPUT> supplier) {
        return Cache2kMemoize.supplier(supplier);
    }

    @Override
    protected BooleanSupplier booleanSupplier(final BooleanSupplier supplier) {
        return Cache2kMemoize.booleanSupplier(supplier);
    }

    @Override
    protected DoubleSupplier doubleSupplier(final DoubleSupplier supplier) {
        return Cache2kMemoize.doubleSupplier(supplier);
    }

    @Override
    protected IntSupplier intSupplier(final IntSupplier supplier) {
        return Cache2kMemoize.intSupplier(supplier);
    }

    @Override
    protected LongSupplier longSupplier(final LongSupplier supplier) {
        return Cache2kMemoize.longSupplier(supplier);
    }

    @Override
    protected <FIRST, SECOND> ToDoubleBiFunction<FIRST, SECOND> toDoubleBiFunction(final ToDoubleBiFunction<FIRST, SECOND> function) {
        return Cache2kMemoize.toDoubleBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToIntBiFunction<FIRST, SECOND> toIntBiFunction(final ToIntBiFunction<FIRST, SECOND> function) {
        return Cache2kMemoize.toIntBiFunction(function);
    }

    @Override
    protected <FIRST, SECOND> ToLongBiFunction<FIRST, SECOND> toLongBiFunction(final ToLongBiFunction<FIRST, SECOND> function) {
        return Cache2kMemoize.toLongBiFunction(function);
    }

    @Override
    protected <INPUT> ToDoubleFunction<INPUT> toDoubleFunction(final ToDoubleFunction<INPUT> function) {
        return Cache2kMemoize.toDoubleFunction(function);
    }

    @Override
    protected <INPUT> ToIntFunction<INPUT> toIntFunction(final ToIntFunction<INPUT> function) {
        return Cache2kMemoize.toIntFunction(function);
    }

    @Override
    protected <INPUT> ToLongFunction<INPUT> toLongFunction(final ToLongFunction<INPUT> function) {
        return Cache2kMemoize.toLongFunction(function);
    }

    @Override
    protected <INPUT> Predicate<INPUT> predicate(final Predicate<INPUT> predicate) {
        return Cache2kMemoize.predicate(predicate);
    }

    @Override
    protected LongPredicate longPredicate(final LongPredicate predicate) {
        return Cache2kMemoize.longPredicate(predicate);
    }

    @Override
    protected IntPredicate intPredicate(final IntPredicate predicate) {
        return Cache2kMemoize.intPredicate(predicate);
    }

    @Override
    protected DoublePredicate doublePredicate(final DoublePredicate predicate) {
        return Cache2kMemoize.doublePredicate(predicate);
    }

    @Override
    protected <INPUT> Consumer<INPUT> consumer(final Consumer<INPUT> consumer) {
        return Cache2kMemoize.consumer(consumer);
    }

    @Override
    protected DoubleConsumer doubleConsumer(final DoubleConsumer consumer) {
        return Cache2kMemoize.doubleConsumer(consumer);
    }

    @Override
    protected IntConsumer intConsumer(final IntConsumer consumer) {
        return Cache2kMemoize.intConsumer(consumer);
    }

    @Override
    protected LongConsumer longConsumer(final LongConsumer consumer) {
        return Cache2kMemoize.longConsumer(consumer);
    }

    @Override
    protected <FIRST, SECOND> BiPredicate<FIRST, SECOND> biPredicate(final BiPredicate<FIRST, SECOND> predicate) {
        return Cache2kMemoize.biPredicate(predicate);
    }

    @Override
    protected <FIRST, SECOND, OUTPUT> BiFunction<FIRST, SECOND, OUTPUT> biFunction(final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        return Cache2kMemoize.biFunction(biFunction);
    }

    @Override
    protected <FIRST, SECOND> BiConsumer<FIRST, SECOND> biConsumer(final BiConsumer<FIRST, SECOND> biConsumer) {
        return Cache2kMemoize.biConsumer(biConsumer);
    }

    @Override
    protected <INPUT> ObjDoubleConsumer<INPUT> objDoubleConsumer(final ObjDoubleConsumer<INPUT> consumer) {
        return Cache2kMemoize.objDoubleConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjIntConsumer<INPUT> objIntConsumer(final ObjIntConsumer<INPUT> consumer) {
        return Cache2kMemoize.objIntConsumer(consumer);
    }

    @Override
    protected <INPUT> ObjLongConsumer<INPUT> objLongConsumer(final ObjLongConsumer<INPUT> consumer) {
        return Cache2kMemoize.objLongConsumer(consumer);
    }

}
