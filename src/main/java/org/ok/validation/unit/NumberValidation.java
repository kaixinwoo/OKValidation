package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class NumberValidation extends DefaultValidation {

    Number min;

    Number max;

    public NumberValidation(String fieldName, String errCode, String errMsg, Object input, Number min, Number max) {
        super(fieldName, errCode, errMsg, input);
        this.min = min;
        this.max = max;
    }

    protected boolean isLessThan() throws OKValidationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof Number == false) {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
        boolean bool = false;
        if (value instanceof Byte) {
            if (((Number) value).byteValue() < min.byteValue()) {
                bool = true;
            }
        } else if (value instanceof Short) {
            if (((Number) value).shortValue() < min.shortValue()) {
                bool = true;
            }
        } else if (value instanceof Integer) {
            if (((Number) value).intValue() < min.intValue()) {
                bool = true;
            }
        } else if (value instanceof Long) {
            if (((Number) value).longValue() < min.longValue()) {
                bool = true;
            }
        } else if (value instanceof Float) {
            if (((Number) value).floatValue() < min.floatValue()) {
                bool = true;
            }
        } else if (value instanceof Double) {
            if (((Number) value).doubleValue() < min.doubleValue()) {
                bool = true;
            }
        } else if (value instanceof BigDecimal) {
            BigDecimal minDecimal = (BigDecimal) min;
            BigDecimal inDecimal = (BigDecimal) value;
            if (inDecimal.compareTo(minDecimal) < 0) {
                bool = true;
            }
        } else if (value instanceof AtomicInteger) {
            if (((AtomicInteger) value).intValue() < min.intValue()) {
                bool = true;
            }
        } else if (value instanceof AtomicLong) {
            if (((AtomicLong) value).longValue() < min.longValue()) {
                bool = true;
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
        return bool;
    }

    protected boolean isGreaterThan() throws NoSuchMethodException, IllegalAccessException, OKValidationException, InvocationTargetException {
        Object value = super.notEmpty();
        if (value instanceof Number == false) {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
        boolean bool = false;
        if (value instanceof Byte) {
            if (((Number) value).byteValue() > max.byteValue()) {
                bool = true;
            }
        } else if (value instanceof Short) {
            if (((Number) value).shortValue() > max.shortValue()) {
                bool = true;
            }
        } else if (value instanceof Integer) {
            if (((Number) value).intValue() > max.intValue()) {
                bool = true;
            }
        } else if (value instanceof Long) {
            if (((Number) value).longValue() > max.longValue()) {
                bool = true;
            }
        } else if (value instanceof Float) {
            if (((Number) value).floatValue() > max.floatValue()) {
                bool = true;
            }
        } else if (value instanceof Double) {
            if (((Number) value).doubleValue() > max.doubleValue()) {
                bool = true;
            }
        } else if (value instanceof BigDecimal) {
            BigDecimal minDecimal = (BigDecimal) max;
            BigDecimal inDecimal = (BigDecimal) value;
            if (inDecimal.compareTo(minDecimal) < 0) {
                bool = true;
            }
        } else if (value instanceof AtomicInteger) {
            if (((AtomicInteger) value).intValue() > max.intValue()) {
                bool = true;
            }
        } else if (value instanceof AtomicLong) {
            if (((AtomicLong) value).longValue() > max.longValue()) {
                bool = true;
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
        return bool;
    }
}
