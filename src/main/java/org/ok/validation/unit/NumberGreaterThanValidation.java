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

    public NumberGreaterThanValidation(Number input, String errCode, String errMsg, Number compareValue) {
        super(input, errCode, errMsg,null, compareValue);
        if (compareValue == null) {
            throw new NullPointerException("无效的数字 compareValue:null ");
        }
    }

    @Override
    public void validation() throws OKValidationException {
        if (super.isGreaterThan()) {
            validationFail();
        }
    }
}
