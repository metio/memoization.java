package de.xn__ho_hia.memoization.shared;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MemoizationDefaults}.
 */
@SuppressWarnings({ "static-method", "nls" })
public class MemoizationDefaultsTest {

    /**
     * Ensures that a default {@link Supplier} for keys is defined.
     */
    @Test
    public void shouldDefineDefaultKeySupplier() {
        // given
        Supplier<String> keySupplier;

        // when
        keySupplier = MemoizationDefaults.defaultKeySupplier();

        // then
        Assert.assertNotNull("Default key supplier is NULL", keySupplier);
    }

    /**
     * Ensures that a default key provided by the default {@link Supplier} is defined.
     */
    @Test
    public void shouldDefineDefaultKey() {
        // given
        final Supplier<String> keySupplier = MemoizationDefaults.defaultKeySupplier();

        // when
        final String defaultSuppliedKey = keySupplier.get();

        // then
        Assert.assertNotNull("Default supplied key is NULL", defaultSuppliedKey);
        Assert.assertEquals("Default supplied key does not match expectations", "SUPPLIED", defaultSuppliedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that a hash-code based key function is defined.
     */
    @Test
    public void shouldDefineHashCodeKeyFunction() {
        // given
        BiFunction<Object, Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        Assert.assertNotNull("Default key function is NULL", keyFunction);
    }

    /**
     * Ensures that the default key function concats the hash-codes of its two inputs by placing a whitespace inbetween.
     */
    @Test
    public void shouldConcatHashCodesWithWhitespaceInbetween() {
        // given
        final BiFunction<Object, Object, String> keyFunction = MemoizationDefaults.hashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(Integer.valueOf(1), Integer.valueOf(2));

        // when
        Assert.assertEquals("Calculated key does not match expectations", "1 2", calculatedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that a hash-code based key function is defined for ObjDoubleConsumer.
     */
    @Test
    public void shouldDefineObjDoubleConsumerHashCodeKeyFunction() {
        // given
        ObjDoubleFunction<Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.objDoubleConsumerHashCodeKeyFunction();

        // when
        Assert.assertNotNull("Default key function is NULL", keyFunction);
    }

    /**
     * Ensures that the default key function for ObjDoubleConsumer concats the hash-codes of its two inputs by placing a
     * whitespace inbetween.
     */
    @Test
    public void shouldConcatObjDoubleConsumerHashCodesWithWhitespaceInbetween() {
        // given
        final ObjDoubleFunction<Object, String> keyFunction = MemoizationDefaults
                .objDoubleConsumerHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(Integer.valueOf(1), 123.456D);

        // when
        Assert.assertEquals("Calculated key does not match expectations", "1 1522623320", calculatedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that a hash-code based key function is defined for ObjIntConsumer.
     */
    @Test
    public void shouldDefineObjIntConsumerHashCodeKeyFunction() {
        // given
        ObjIntFunction<Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.objIntConsumerHashCodeKeyFunction();

        // when
        Assert.assertNotNull("Default key function is NULL", keyFunction);
    }

    /**
     * Ensures that the default key function for ObjIntConsumer concats the hash-codes of its two inputs by placing a
     * whitespace inbetween.
     */
    @Test
    public void shouldConcatObjIntConsumerHashCodesWithWhitespaceInbetween() {
        // given
        final ObjIntFunction<Object, String> keyFunction = MemoizationDefaults
                .objIntConsumerHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(Integer.valueOf(1), 123);

        // when
        Assert.assertEquals("Calculated key does not match expectations", "1 123", calculatedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that a hash-code based key function is defined for ObjLongConsumer.
     */
    @Test
    public void shouldDefineObjLongConsumerHashCodeKeyFunction() {
        // given
        ObjLongFunction<Object, String> keyFunction;

        // when
        keyFunction = MemoizationDefaults.objLongConsumerHashCodeKeyFunction();

        // when
        Assert.assertNotNull("Default key function is NULL", keyFunction);
    }

    /**
     * Ensures that the default key function for ObjLongConsumer concats the hash-codes of its two inputs by placing a
     * whitespace inbetween.
     */
    @Test
    public void shouldConcatObjLongConsumerHashCodesWithWhitespaceInbetween() {
        // given
        final ObjLongFunction<Object, String> keyFunction = MemoizationDefaults
                .objLongConsumerHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(Integer.valueOf(1), 123L);

        // when
        Assert.assertEquals("Calculated key does not match expectations", "1 123", calculatedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that the default key function for DoubleBinaryOperator concats the hash-codes of its two inputs by
     * placing a whitespace inbetween.
     */
    @Test
    public void shouldConcatDoubleBinaryOperatorHashCodesWithWhitespaceInbetween() {
        // given
        final DoubleBinaryFunction<String> keyFunction = MemoizationDefaults
                .doubleBinaryOperatorHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(123.456D, 789.123D);

        // when
        Assert.assertEquals("Calculated key does not match expectations", "1522623320 -1478220865", calculatedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that the default key function for IntBinaryOperator concats the hash-codes of its two inputs by placing a
     * whitespace inbetween.
     */
    @Test
    public void shouldConcatIntBinaryOperatorHashCodesWithWhitespaceInbetween() {
        // given
        final IntBinaryFunction<String> keyFunction = MemoizationDefaults
                .intBinaryOperatorHashCodeKeyFunction();

        // when
        final String calculatedKey = keyFunction.apply(123, 456);

        // when
        Assert.assertEquals("Calculated key does not match expectations", "123 456", calculatedKey); //$NON-NLS-1$
    }

    /**
     * Ensures that the constructor of the {@link MemoizationDefaults} class is private.
     * <p>
     * The class should never be instantiated.
     *
     * @throws NoSuchMethodException
     *             Should not fail in case the StorageUnits class has a constructor..
     * @throws IllegalAccessException
     *             Should not fail in case the StorageUnits class has a constructor..
     * @throws InvocationTargetException
     *             Should not fail in case the StorageUnits class has a constructor..
     * @throws InstantiationException
     *             Should not fail in case the StorageUnits class has a constructor..
     */
    @Test
    public void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Given
        final Constructor<MemoizationDefaults> constructor = MemoizationDefaults.class.getDeclaredConstructor();

        // When
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // Then
        Assert.assertTrue("Constructor is not private", isPrivate); //$NON-NLS-1$
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
