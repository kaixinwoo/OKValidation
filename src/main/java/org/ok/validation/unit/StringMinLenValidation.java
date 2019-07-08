package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringMinLenValidation extends DefaultValidation<CharSequence> {

    protected int compareValue;

    public StringMinLenValidation(CharSequence input, String errCode, String errMsg, int compareValue) {
        super(input, errCode, errMsg);
        this.compareValue = compareValue;
    }

    @Override
    protected void doValidation(CharSequence input) throws OKValidationException {
        CharSequence cs = super.notEmpty();
        if (cs.length() < compareValue) {
            validationFail();
        }
    }
}
