package org.test.shapecalculator.calculator;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
class CalculatorService {

    private Validator validator;

    @Autowired
    public CalculatorService(Validator validator) {
        this.validator = validator;
    }

    /**
     * Obtains measurement results from specific geometry shape calculator for
     * the given geometric shape.
     *
     * @param calculator concrete geometric sha[pe calculator that implements
     *                   {@code GeometricShapeCalculator}
     * @param shape      the geometric shape to be measured
     * @return measurement results
     * @throws ConstraintViolationException if given geometric shape details
     *                                      violates it's constrains.
     */
    public CalculatorResult getMeasurement(GeometricShapeVisitor calculator,
                                           GeometricShape shape) throws ConstraintViolationException {
        checkNotNull(calculator);
        checkNotNull(shape);
        validateGeometricShape(shape);
        var value = shape.accept(calculator);
        return new CalculatorResult(value);
    }

    private void validateGeometricShape(GeometricShape shape) throws ConstraintViolationException {
        var violations = validator.validate(shape);

        if (!violations.isEmpty()) {
            StringBuilder violationMessages = new StringBuilder();
            for (var constraintViolation : violations) {
                violationMessages.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Geometric shape violates: " + violationMessages, violations);
        }
    }
}
