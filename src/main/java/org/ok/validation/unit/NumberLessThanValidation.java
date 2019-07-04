package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NumberLessThanValidation extends NumberValidation {

    public NumberLessThanValidation(String fieldName, String errCode, String errMsg, Object input, Number compareValue) {
        super(fieldName, errCode, errMsg, input, compareValue, null);
        if (compareValue == null) {
            throw new NullPointerException("无效的数字 compareValue:null fieldName:" + fieldName);
        }
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (super.isLessThan()) {
            validationFail();
        }
    }
}
