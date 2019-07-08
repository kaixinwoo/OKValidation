package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.util.regex.Pattern;

/**
 * 等值验证，
 * 要求输入项的值和equalValue进行比较，如果有值相等验证通过，否则验证失败
 */
public class EqualValidation extends DefaultValidation<Object>  {

    private Object[] equalValue;

    public EqualValidation(Object input, String errCode, String errMsg, Object[] equalValue) {
        super(input, errCode, errMsg);
        if (equalValue == null) {
            throw new NullPointerException("无效的比较数据 equalValue:null");
        }
        this.equalValue = equalValue;
    }

    @Override
    protected void doValidation(Object input) throws OKValidationException {
        boolean isEqual = false;
        for (Object item : equalValue) {
            if (item instanceof CharSequence) {
                String itemString = input.toString();
                String equalString = item.toString();
                if (itemString.equals(equalString)) {
                    isEqual = true;
                    break;
                }
            } else {
                if (input.equals(equalValue)) {
                    isEqual = true;
                    break;
                }
            }
            if (input.equals(item)) {
                isEqual = true;
                break;
            }
        }
        if (isEqual == false) {
            validationFail();
        }
    }
}
