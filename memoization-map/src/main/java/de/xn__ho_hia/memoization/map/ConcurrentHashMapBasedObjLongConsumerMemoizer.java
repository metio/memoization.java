package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ObjLongConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;
import de.xn__ho_hia.utils.memoization.shared.ObjLongFunction;

final class ConcurrentHashMapBasedObjLongConsumerMemoizer<VALUE, KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, KEY>
        implements ObjLongConsumer<VALUE> {

    private final ObjLongFunction<VALUE, KEY> keyFunction;
    private final ObjLongConsumer<VALUE>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedObjLongConsumerMemoizer(
            final Map<KEY, KEY> preComputedValues,
            final ObjLongFunction<VALUE, KEY> keyFunction,
            final ObjLongConsumer<VALUE> consumer) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objLongConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final VALUE t, final long value) {
        final KEY key = keyFunction.apply(t, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(t, value);
            return givenKey;
        });
    }

}
