package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NotEmptyValidation extends DefaultValidation<Object> {

    public NotEmptyValidation(Object input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    protected void doValidation(Object input) throws OKValidationException {
        if (input == null) {
            validationFail();
        }
        if (input instanceof CharSequence) {
            CharSequence cs = (CharSequence) input;
            if (cs.length() == 0) {
                validationFail();
            }
        }
    }
}
