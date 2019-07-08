package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NumberLessThanValidation extends NumberValidation {

    public NumberLessThanValidation(Number input, String errCode, String errMsg, Number compareValue) {
        super(input, errCode, errMsg, compareValue, null);
        if (compareValue == null) {
            throw new NullPointerException("无效的数字 compareValue:null ");
        }
    }

    @Override
    protected void doValidation(Number input) throws OKValidationException {
        if (super.isLessThan()) {
            validationFail();
        }
    }
}
