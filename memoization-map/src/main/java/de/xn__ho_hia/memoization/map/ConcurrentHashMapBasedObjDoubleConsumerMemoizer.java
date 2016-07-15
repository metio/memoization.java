package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ObjDoubleConsumer;

import de.xn__ho_hia.memoization.shared.ObjDoubleFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedObjDoubleConsumerMemoizer<VALUE, KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, KEY>
        implements ObjDoubleConsumer<VALUE> {

    private final ObjDoubleFunction<VALUE, KEY> keyFunction;
    private final ObjDoubleConsumer<VALUE>      consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedObjDoubleConsumerMemoizer(
            final Map<KEY, KEY> preComputedValues,
            final ObjDoubleFunction<VALUE, KEY> keyFunction,
            final ObjDoubleConsumer<VALUE> consumer) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction()'.");
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final VALUE t, final double value) {
        final KEY key = keyFunction.apply(t, value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(t, value);
            return givenKey;
        });
    }

}
