package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.util.regex.Pattern;

/**
 * 邮箱验证
 */
public class EmailValidation extends DefaultValidation<CharSequence> {

    private static final String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5\\.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public EmailValidation(CharSequence input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    public void validation() throws OKValidationException {
        CharSequence cs = super.notEmpty();
        boolean bool = Pattern.matches(this.regex, cs);
        if (bool == false) {
            validationFail();
        }
    }

}
