package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;
import de.xn__ho_hia.utils.memoization.shared.MemoizingBiFunction;

final class ConcurrentHashMapBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends ConcurrentHashMapBasedMemoizer<KEY, VALUE>
        implements MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY>   keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedBiFunctionMemoizer(
            final Map<KEY, VALUE> preComputedValues,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biFunction = requireNonNull(biFunction,
                "Cannot memoize a NULL BiFunction - provide an actual BiFunction to fix this.");
    }

    @Override
    public BiFunction<FIRST, SECOND, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public BiFunction<FIRST, SECOND, VALUE> getBiFunction() {
        return biFunction;
    }

    @Override
    public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
        return this::computeIfAbsent;
    }

}
