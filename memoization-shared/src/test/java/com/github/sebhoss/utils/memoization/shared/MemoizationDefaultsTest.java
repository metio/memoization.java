package com.github.sebhoss.utils.memoization.shared;

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
@SuppressWarnings("static-method")
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
        Assert.assertNotNull(keySupplier);
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
        Assert.assertNotNull(defaultSuppliedKey);
        Assert.assertEquals("SUPPLIED", defaultSuppliedKey); //$NON-NLS-1$
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
        Assert.assertNotNull(keyFunction);
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
        Assert.assertEquals("1 2", calculatedKey); //$NON-NLS-1$
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
