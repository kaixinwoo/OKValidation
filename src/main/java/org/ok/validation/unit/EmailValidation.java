package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

/**
 * 邮箱验证
 */
public class EmailValidation extends RegexValidation {

    private static final String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5\\.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public EmailValidation(CharSequence input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    protected String getRegex() {
        return regex;
    }

}
