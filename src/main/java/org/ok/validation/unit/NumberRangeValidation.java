package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NumberRangeValidation extends NumberValidation {

    public NumberRangeValidation(Number input, String errCode, String errMsg, Number min, Number max) {
        super(input, errCode, errMsg, min, max);
    }

    @Override
    public void validation() throws OKValidationException {
        if (super.isLessThan() || super.isGreaterThan()) {
            validationFail();
        }
    }
}
