package org.ok.validation.unit;

public class RequiredNumberValidation extends RegexValidation {

    private static final String regex = "^[0-9]+$";

    public RequiredNumberValidation(CharSequence input, String errCode, String errMsg) {
        super(input, errCode, errMsg);
    }

    @Override
    protected String getRegex() {
        return regex;
    }
}
