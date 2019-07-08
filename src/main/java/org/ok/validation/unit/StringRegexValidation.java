package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class StringRegexValidation extends DefaultValidation<CharSequence> {

    private String regex;

    public StringRegexValidation(CharSequence input, String errCode, String errMsg, String regex) {
        super(input, errCode, errMsg);
        if (regex == null) {
            throw new NullPointerException("无效的正则表达式 errCode:" + errCode + " errMsg:" + errMsg);
        }
        this.regex = regex;
    }

    @Override
    protected void doValidation(CharSequence input) throws OKValidationException {
        CharSequence cs = super.getInput();
        boolean bool = Pattern.matches(this.regex, cs);
        if (bool == false) {
            validationFail();
        }
    }
}
