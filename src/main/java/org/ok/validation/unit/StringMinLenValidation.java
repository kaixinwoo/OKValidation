package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringMinLenValidation extends DefaultValidation {

    int minLen;

    public StringMinLenValidation(String fieldName, String errCode, String errMsg, Object input, int minLen) {
        super(fieldName, errCode, errMsg, input);
        this.minLen = minLen;
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof CharSequence) {
            CharSequence cs = (CharSequence) value;
            if (cs.length() < minLen) {
                validationFail();
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
    }
}
