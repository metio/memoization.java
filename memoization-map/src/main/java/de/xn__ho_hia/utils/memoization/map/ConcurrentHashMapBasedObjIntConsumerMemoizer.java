package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ObjIntConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;
import de.xn__ho_hia.utils.memoization.shared.ObjIntFunction;

final class ConcurrentHashMapBasedObjIntConsumerMemoizer<VALUE, KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, KEY>
        implements ObjIntConsumer<VALUE> {

    private final ObjIntFunction<VALUE, KEY> keyFunction;
    private final ObjIntConsumer<VALUE>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedObjIntConsumerMemoizer(
            final Map<KEY, KEY> preComputedValues,
            final ObjIntFunction<VALUE, KEY> keyFunction,
            final ObjIntConsumer<VALUE> consumer) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objIntConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final VALUE t, final int value) {
        final KEY key = keyFunction.apply(t, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(t, value);
            return givenKey;
        });
    }

}
