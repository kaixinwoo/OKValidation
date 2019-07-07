package org.ok.validation.unit;

/**
 * IPv4地址验证
 */
public class IPv4Validation extends RegexValidation {

    private static final String regex = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";

    public IPv4Validation(CharSequence input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    protected String getRegex() {
        return regex;
    }

}
