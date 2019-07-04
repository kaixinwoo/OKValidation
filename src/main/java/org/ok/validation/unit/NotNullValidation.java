package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NotNullValidation extends DefaultValidation {

    public NotNullValidation(String fieldName, String code, String msg, Object input) {
        super(fieldName, code, msg, input);
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        super.notNull();
    }
}
