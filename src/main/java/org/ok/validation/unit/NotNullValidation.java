package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NotNullValidation extends DefaultValidation {

    public NotNullValidation(String fieldName, String errCode, String msg, Object input) {
        super(fieldName, errCode, msg, input);
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        super.notNull();
    }
}
