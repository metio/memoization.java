package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedToDoubleBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Double>
        implements ToDoubleBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY>    keyFunction;
    private final ToDoubleBiFunction<FIRST, SECOND> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedToDoubleBiFunctionMemoizer(
            final Map<KEY, Double> preComputedValues,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        super(preComputedValues);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleBiFunction - provide an actual ToDoubleBiFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> Double.valueOf(function.applyAsDouble(t, u))).doubleValue();
    }

}
