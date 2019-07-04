package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class RequiredNumberValidation extends DefaultValidation {

    private static final String regex = "^[0-9]+$";

    public RequiredNumberValidation(String fieldName, String errCode, String errMsg, Object input) {
        super(fieldName, errCode, errMsg, input);
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof Number) {

        } else if (value instanceof CharSequence) {
            CharSequence cs = (CharSequence) value;
            boolean bool = Pattern.matches(RequiredNumberValidation.regex, cs);
            if (bool == false) {
                validationFail();
            }
        } else {
            validationFail();
        }
    }
}
