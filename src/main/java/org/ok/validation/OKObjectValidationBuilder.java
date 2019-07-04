package org.ok.validation;

import org.ok.validation.exception.OKValidationException;
import org.ok.validation.unit.*;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class OKObjectValidationBuilder {

    private List<OKValidation> validations = new LinkedList<>();
    // 默认错误码
    private String errCode;
    // 输入值
    private Object input;

    public static OKObjectValidationBuilder builder() {
        return new OKObjectValidationBuilder();
    }

    public OKObjectValidationBuilder defaultErrCode(String defaultErrCode) {
        this.errCode = defaultErrCode;
        return this;
    }

    public OKObjectValidationBuilder input(Object input) {
        this.input = input;
        return this;
    }

    public OKObjectValidationBuilder notNull(String fieldName, String errMsg) {
        return notNull(fieldName, errCode, errMsg, input);
    }

    public OKObjectValidationBuilder notNull(String fieldName, String errCode, String errMsg, Object input) {
        NotNullValidation notNullValidation = new NotNullValidation(fieldName, errCode, errMsg, input);
        addValidation(notNullValidation);
        return this;
    }

    public OKObjectValidationBuilder notEmpty(String fieldName, String errMsg) {
        return notEmpty(fieldName, errCode, errMsg, input);
    }

    public OKObjectValidationBuilder notEmpty(String fieldName, String errCode, String errMsg, Object input) {
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation(fieldName, errCode, errMsg, input);
        addValidation(notEmptyValidation);
        return this;
    }

    public OKObjectValidationBuilder equal(String fieldName, String errMsg, Object[] equalValue) {
        return equal(fieldName, errCode, errMsg, input, equalValue);
    }

    public OKObjectValidationBuilder equal(String fieldName, String errCode, String errMsg, Object input, Object[] equalValue) {
        EqualValidation equalValidation = new EqualValidation(fieldName, errCode, errMsg, input, equalValue);
        addValidation(equalValidation);
        return this;
    }

    public OKObjectValidationBuilder lessThan(String fieldName, String errMsg, Number min) {
        return lessThan(fieldName, errCode, errMsg, input, min);
    }

    public OKObjectValidationBuilder lessThan(String fieldName, String errCode, String errMsg, Object input, Number min) {
        LessThanValidation lessThanValidation = new LessThanValidation(fieldName, errCode, errMsg, input, min);
        addValidation(lessThanValidation);
        return this;
    }

    public OKObjectValidationBuilder greaterThan(String fieldName, String errMsg, Number max) {
        return greaterThan(fieldName, errCode, errMsg, input, max);
    }

    public OKObjectValidationBuilder greaterThan(String fieldName, String errCode, String errMsg, Object input, Number max) {
        GreaterThanValidation greaterThanValidation = new GreaterThanValidation(fieldName, errCode, errMsg, input, max);
        addValidation(greaterThanValidation);
        return this;
    }

    public OKObjectValidationBuilder numberRange(String fieldName, String errMsg, Number min, Number max) {
        return numberRange(fieldName, errMsg, errMsg, input, min, max);
    }

    public OKObjectValidationBuilder numberRange(String fieldName, String errCode, String errMsg, Object input, Number min, Number max) {
        RangeValidation rangeValidation = new RangeValidation(fieldName, errCode, errMsg, input, min, max);
        addValidation(rangeValidation);
        return this;
    }

    public OKObjectValidationBuilder regularExpression(String fieldName, String errMsg, String regex) {
        return regularExpression(fieldName, errCode, errMsg, input, regex);
    }

    public OKObjectValidationBuilder regularExpression(String fieldName, String errCode, String errMsg, Object input, String regex) {
        RegularExpressionValidation regularExpressionValidation = new RegularExpressionValidation(fieldName, errCode, errMsg, input, regex);
        addValidation(regularExpressionValidation);
        return this;
    }

    public OKObjectValidationBuilder requiredNumber(String fieldName, String errMsg) {
        return requiredNumber(fieldName, errCode, errMsg, input);
    }

    public OKObjectValidationBuilder requiredNumber(String fieldName, String errCode, String errMsg, Object input) {
        RequiredNumberValidation requiredNumberValidation = new RequiredNumberValidation(fieldName, errCode, errMsg, input);
        addValidation(requiredNumberValidation);
        return this;
    }

    public OKObjectValidationBuilder stringMinLen(String fieldName, String errMsg, int minLen) {
        return stringMinLen(fieldName, errCode, errMsg, input, minLen);
    }

    public OKObjectValidationBuilder stringMinLen(String fieldName, String errCode, String errMsg, Object input, int minLen) {
        StringMinLenValidation stringMinLenValidation = new StringMinLenValidation(fieldName, errCode, errMsg, input, minLen);
        addValidation(stringMinLenValidation);
        return this;
    }

    public OKObjectValidationBuilder stringMaxLen(String fieldName, String errMsg, int maxLen) {
        return stringMaxLen(fieldName, errCode, errMsg, input, maxLen);
    }

    public OKObjectValidationBuilder stringMaxLen(String fieldName, String errCode, String errMsg, Object input, int maxLen) {
        StringMaxLenValidation stringMaxLenValidation = new StringMaxLenValidation(fieldName, errCode, errMsg, input, maxLen);
        addValidation(stringMaxLenValidation);
        return this;
    }

    public OKObjectValidationBuilder stringRangeLen(String fieldName, String errMsg, int minLen, int maxLen) {
        return stringRangeLen(fieldName, errCode, errMsg, input, minLen, maxLen);
    }

    public OKObjectValidationBuilder stringRangeLen(String fieldName, String errCode, String errMsg, Object input, int minLen, int maxLen) {
        StringRangeLenValidation stringRangeLenValidation = new StringRangeLenValidation(fieldName, errCode, errMsg, input, minLen, maxLen);
        addValidation(stringRangeLenValidation);
        return this;
    }

    public void validation() throws OKValidationException {
        if (validations != null && validations.size() > 0) {
            for (OKValidation validation : validations) {
                try {
                    validation.validation(input);
                } catch (OKValidationException e) {
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("unknown_error").errMsg("未知错误 fieldName:");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("unknown_error").errMsg("未知错误");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("unknown_error").errMsg("未知错误");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("unknown_error").errMsg("未知错误");
                }
            }
        }
    }

    public void addValidation(OKValidation validation) {
        validations.add(validation);
    }
}
