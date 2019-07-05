package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringMaxLenValidation extends DefaultValidation<CharSequence> {

    protected int compareValue;

    public StringMaxLenValidation(CharSequence input, String errCode, String errMsg, int compareValue) {
        super(input, errCode, errMsg);
        this.compareValue = compareValue;
    }

    @Override
    public void validation() throws OKValidationException {
        CharSequence input = super.notEmpty();
        if (input.length() > compareValue) {
            validationFail();
        }
    }
}
