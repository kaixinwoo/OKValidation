package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringRangeLenValidation extends DefaultValidation {

    int minLen;

    int maxLen;

    public StringRangeLenValidation(String fieldName, String errCode, String errMsg, Object input, int minLen, int maxLen) {
        super(fieldName, errCode, errMsg, input);
        this.minLen = minLen;
        this.maxLen = maxLen;
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof CharSequence) {
            CharSequence cs = (CharSequence) value;
            if (cs.length() > maxLen || cs.length() < minLen) {
                validationFail();
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
    }
}
