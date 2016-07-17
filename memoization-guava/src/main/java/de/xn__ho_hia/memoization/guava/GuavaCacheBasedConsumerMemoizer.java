package de.xn__ho_hia.memoization.guava;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.cache.Cache;

import de.xn__ho_hia.memoization.shared.MemoizingConsumer;

final class GuavaCacheBasedConsumerMemoizer<KEY, VALUE>
        extends AbstractGuavaCacheBasedMemoizer<KEY, VALUE>
        implements MemoizingConsumer<KEY, VALUE> {

    private final Function<VALUE, KEY> keyFunction;
    private final Consumer<VALUE>      consumer;

    GuavaCacheBasedConsumerMemoizer(
            final Cache<KEY, VALUE> cache,
            final Function<VALUE, KEY> keyFunction,
            final Consumer<VALUE> consumer) {
        super(cache);
        this.keyFunction = keyFunction;
        this.consumer = consumer;
    }

    @Override
    public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
        return this::get;
    }

    @Override
    public final Function<VALUE, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public final Consumer<VALUE> getConsumer() {
        return consumer;
    }

}
