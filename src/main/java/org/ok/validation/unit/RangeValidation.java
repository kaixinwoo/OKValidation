package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class RangeValidation extends NumberValidation {

    public RangeValidation(String fieldName, String errCode, String errMsg, Object input, Number min, Number max) {
        super(fieldName, errCode, errMsg, input, min, max);
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (super.isLessThan() || super.isGreaterThan()) {
            validationFail();
        }
    }
}
