package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongConsumer;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongConsumerMemoizer
        extends ConcurrentHashMapBasedMemoizer<Long, Long>
        implements LongConsumer {

    private final LongConsumer consumer;

    @SuppressWarnings(CompilerWarnings.NLS)
    public ConcurrentHashMapBasedLongConsumerMemoizer(
            final Map<Long, Long> preComputedValues,
            final LongConsumer consumer) {
        super(preComputedValues);
        this.consumer = requireNonNull(consumer,
                "Cannot memoize a NULL Consumer - provide an actual Consumer to fix this.");
    }

    @Override
    public void accept(final long value) {
        final Long key = Long.valueOf(value);
        computeIfAbsent(key, givenKey -> {
            consumer.accept(value);
            return givenKey;
        });
    }

}
