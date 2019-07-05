package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 比较验证
 * 要求指定数据要
 */
public class NumberGreaterThanValidation extends NumberValidation {

    public NumberGreaterThanValidation(String fieldName, String errCode, String errMsg, Object input, Number compareValue) {
        super(fieldName, errCode, errMsg, input, null, compareValue);
        if (compareValue == null) {
            throw new NullPointerException("无效的数字 compareValue:null fieldName:" + fieldName);
        }
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (super.isGreaterThan()) {
            validationFail();
        }
    }
}
