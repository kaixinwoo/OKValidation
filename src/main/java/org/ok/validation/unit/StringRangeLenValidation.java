package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class StringRangeLenValidation extends DefaultValidation<CharSequence> {

    private int minLen;

    private int maxLen;

    public StringRangeLenValidation(CharSequence input, String errCode, String errMsg, int minLen, int maxLen) {
        super(input, errCode, errMsg);
        this.minLen = minLen;
        this.maxLen = maxLen;
    }

    @Override
    public void validation() throws OKValidationException {
        CharSequence cs = super.notEmpty();
        if (cs.length() > maxLen || cs.length() < minLen) {
            validationFail();
        }
    }
}
