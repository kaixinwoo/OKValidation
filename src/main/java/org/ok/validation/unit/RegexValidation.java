package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.util.regex.Pattern;

/**
 * 正则表达式验证
 */
public abstract class RegexValidation extends DefaultValidation<CharSequence> {

    public RegexValidation(CharSequence input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    public void validation() throws OKValidationException {
        CharSequence cs = super.notEmpty();
        boolean bool = Pattern.matches(getRegex(), cs);
        if (bool == false) {
            validationFail();
        }
    }

    protected abstract String getRegex();
}
