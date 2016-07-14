package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.IntConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedIntConsumerMemoizer
        extends ConcurrentHashMapBasedMemoizer<Integer, Integer>
        implements IntConsumer {

    private final IntConsumer consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedIntConsumerMemoizer(
            final Map<Integer, Integer> preComputedValues,
            final IntConsumer consumer) {
        super(preComputedValues);
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final int value) {
        final Integer key = Integer.valueOf(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return givenKey;
        });
    }

}
