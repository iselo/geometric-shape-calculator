package org.test.shapecalculator.calculator;

/**
 * The JSON deserializer type of given boundaries.
 *
 * @param <T> the type of the object to which JSON is to  be deserialized.
 */
interface Deserializer<T> {

    /**
     * Deserializes a given JSON into an object of the specified class.
     *
     * @param json the string from which the object is to be deserialized
     * @return an object of type T from the string
     */
    T fromJson(String json);
}
