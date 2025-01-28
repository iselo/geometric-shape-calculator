package org.test.enumfactory;

public interface EnumFactory<T extends EnumEntity> {

    /**
     * Obtains a new instance of enum entity or the given value.
     */
    T newInstance(String value);
}
