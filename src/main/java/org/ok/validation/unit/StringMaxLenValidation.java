package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringMaxLenValidation extends DefaultValidation {

    int maxLen;

    public StringMaxLenValidation(String fieldName, String errCode, String errMsg, Object input, int maxLen) {
        super(fieldName, errCode, errMsg, input);
        this.maxLen = maxLen;
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof CharSequence) {
            CharSequence cs = (CharSequence) value;
            if (cs.length() > maxLen) {
                validationFail();
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
    }
}
