package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NumberRangeValidation extends NumberValidation {

    public NumberRangeValidation(Number input, String errCode, String errMsg, Number min, Number max) {
        super(input, errCode, errMsg, min, max);
    }

    @Override
    protected void doValidation(Number input) throws OKValidationException {
        if (super.isLessThan() || super.isGreaterThan()) {
            validationFail();
        }
    }
}
