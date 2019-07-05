package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NotNullValidation extends DefaultValidation<Object> {

    public NotNullValidation(Object input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    public void validation() throws OKValidationException {
        super.notNull();
    }
}
