package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class RegularExpressionValidation extends DefaultValidation {

    String regex;

    public RegularExpressionValidation(String fieldName, String errCode, String errMsg, Object input, String regex) {
        super(fieldName, errCode, errMsg, input);
        if (regex == null) {
            throw new NullPointerException("无效的正则表达式 fieldName:" + fieldName + " errCode:" + errCode + " errMsg:" + errMsg);
        }
        this.regex = regex;
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof CharSequence) {
            CharSequence cs = (CharSequence) value;
            boolean bool = Pattern.matches(this.regex, cs);
            if (bool == false) {
                validationFail();
            }
        }
    }
}
