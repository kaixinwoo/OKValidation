package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringMaxLenValidation extends DefaultValidation {

    protected int compareValue;

    public StringMaxLenValidation(String fieldName, String errCode, String errMsg, Object input, int compareValue) {
        super(fieldName, errCode, errMsg, input);
        this.compareValue = compareValue;
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof CharSequence) {
            CharSequence cs = (CharSequence) value;
            if (cs.length() > compareValue) {
                validationFail();
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
    }
}
