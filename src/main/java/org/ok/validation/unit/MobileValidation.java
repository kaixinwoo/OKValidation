package org.ok.validation.unit;


/**
 * 手机号验证（中国）
 */
public class MobileValidation extends RegexValidation {

    private static final String regex = "^1[0-9]{10}$";

    public MobileValidation(CharSequence input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }


    @Override
    protected String getRegex() {
        return regex;
    }

}
