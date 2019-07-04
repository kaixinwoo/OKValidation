package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class LessThanValidation extends NumberValidation {

    public LessThanValidation(String fieldName, String errCode, String errMsg, Object input, Number min) {
        super(fieldName, errCode, errMsg, input, min, null);
        if (min == null) {
            throw new NullPointerException("无效的数字 min:null fieldName:" + fieldName);
        }
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (super.isLessThan()) {
            validationFail();
        }
    }
}
