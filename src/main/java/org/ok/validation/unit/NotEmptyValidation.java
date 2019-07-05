package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class NotEmptyValidation extends DefaultValidation {

    public NotEmptyValidation(String fieldName,String errCode, String errMsg,  Object input) {
        super(fieldName, errCode, errMsg, input);
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        super.notEmpty();
    }
}
