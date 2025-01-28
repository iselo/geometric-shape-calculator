package org.test.enumfactory;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 *         new ShapeDeserializerFactory()
 *                 .newInstance("GNU_GPL")
 *                 .fromJson("{}");
 */
public final class ShapeDeserializerFactory implements EnumFactory<Shape> {

    @Override
    public Shape newInstance(String value) throws IllegalArgumentException {
        try {
            checkNotNull(value);
            return Shape.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
