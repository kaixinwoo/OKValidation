package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class GreaterThanValidation extends NumberValidation {


    public GreaterThanValidation(String fieldName, String errCode, String errMsg, Object input, Number max) {
        super(fieldName, errCode, errMsg, input, null, max);
        if (max == null) {
            throw new NullPointerException("无效的数字 max:null fieldName:" + fieldName);
        }
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (super.isGreaterThan()) {
            validationFail();
        }
    }
}
