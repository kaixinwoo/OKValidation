package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;
import java.lang.Number;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class NumberValidation extends DefaultValidation<java.lang.Number> {

    protected Number min;

    protected Number max;

    public NumberValidation(Number input, String errCode, String errMsg, Number min, Number max) {
        super(input, errCode, errMsg);
        this.min = min;
        this.max = max;
    }

    protected boolean isLessThan() throws OKValidationException {
        Number input = super.notEmpty();
        boolean bool = false;
        if (input instanceof Byte) {
            if (input.byteValue() < min.byteValue()) {
                bool = true;
            }
        } else if (input instanceof Short) {
            if (input.shortValue() < min.shortValue()) {
                bool = true;
            }
        } else if (input instanceof Integer) {
            if (input.intValue() < min.intValue()) {
                bool = true;
            }
        } else if (input instanceof Long) {
            if (input.longValue() < min.longValue()) {
                bool = true;
            }
        } else if (input instanceof Float) {
            if (input.floatValue() < min.floatValue()) {
                bool = true;
            }
        } else if (input instanceof Double) {
            if (input.doubleValue() < min.doubleValue()) {
                bool = true;
            }
        } else if (input instanceof BigDecimal) {
            BigDecimal minDecimal = (BigDecimal) min;
            BigDecimal inDecimal = (BigDecimal) input;
            if (inDecimal.compareTo(minDecimal) < 0) {
                bool = true;
            }
        } else if (input instanceof AtomicInteger) {
            if (input.intValue() < min.intValue()) {
                bool = true;
            }
        } else if (input instanceof AtomicLong) {
            if (input.longValue() < min.longValue()) {
                bool = true;
            }
        } else {
            throw DATA_TYPE_ERR_EXCEPTION;
        }
        return bool;
    }

    protected boolean isGreaterThan() throws OKValidationException {
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
