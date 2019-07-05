package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class RequiredNumberValidation extends DefaultValidation<Object> {

    private static final String regex = "^[0-9]+$";

    public RequiredNumberValidation(Object input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    public void validation() throws OKValidationException {
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
