package org.ok.validation;

import org.ok.validation.exception.OKValidationException;
import org.ok.validation.unit.*;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * 基本数据类型验证
 */
public class OKBasicTypeValidationBuilder {

    protected List<OKValidation> validations = new LinkedList<>();
    // 默认错误码
    protected String errCode;

    public static OKBasicTypeValidationBuilder builder() {
        return new OKBasicTypeValidationBuilder();
    }

    public OKBasicTypeValidationBuilder errCode(String errCode) {
        this.errCode = errCode;
        return this;
    }


    public OKBasicTypeValidationBuilder notNull(String errMsg, Object input) throws OKValidationException {
        return notNull(errCode, errMsg, input);
    }

    public OKBasicTypeValidationBuilder notNull(String errCode, String errMsg, Object input) throws OKValidationException {
        checkSupport(input);
        NotNullValidation notEmptyValidation = new NotNullValidation(null, errCode, errMsg, input);
        return addValidation(notEmptyValidation);
    }

    public OKBasicTypeValidationBuilder notEmpty(String errMsg, Object input) throws OKValidationException {
        return notEmpty(errCode, errMsg, input);
    }

    public OKBasicTypeValidationBuilder notEmpty(String errCode, String errMsg, Object input) throws OKValidationException {
        checkSupport(input);
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation(null, errCode, errMsg, input);
        return addValidation(notEmptyValidation);
    }

    public OKBasicTypeValidationBuilder equal(String errMsg, Object input, Object[] equalValue) throws OKValidationException {
        return equal(errCode, errMsg, input, equalValue);
    }

    public OKBasicTypeValidationBuilder equal(String errCode, String errMsg, Object input, Object[] equalValue) throws OKValidationException {
        checkSupport(input);
        EqualValidation equalValidation = new EqualValidation(null, errCode, errMsg, input, equalValue);
        return addValidation(equalValidation);
    }

    public OKBasicTypeValidationBuilder lessThan(String errMsg, Object input, Number compareValue) throws OKValidationException {
        return lessThan(errCode, errMsg, input, compareValue);
    }

    public OKBasicTypeValidationBuilder lessThan(String errCode, String errMsg, Object input, Number compareValue) throws OKValidationException {
        checkSupport(input);
        NumberLessThanValidation numberLessThanValidation = new NumberLessThanValidation(null, errCode, errMsg, input, compareValue);
        return addValidation(numberLessThanValidation);
    }

    public OKBasicTypeValidationBuilder greaterThan(String errMsg, Object input, Number compareValue) throws OKValidationException {
        return greaterThan(errCode, errMsg, input, compareValue);
    }

    public OKBasicTypeValidationBuilder greaterThan(String errCode, String errMsg, Object input, Number compareValue) throws OKValidationException {
        checkSupport(input);
        NumberGreaterThanValidation numberGreaterThanValidation = new NumberGreaterThanValidation(null, errCode, errMsg, input, compareValue);
        return addValidation(numberGreaterThanValidation);
    }

    public OKBasicTypeValidationBuilder range(String errMsg, Object input, Number min, Number max) throws OKValidationException {
        return range(errCode, errMsg, input, min, max);
    }

    public OKBasicTypeValidationBuilder range(String errCode, String errMsg, Object input, Number min, Number max) throws OKValidationException {
        checkSupport(input);
        NumberRangeValidation numberRangeValidation = new NumberRangeValidation(null, errCode, errMsg, input, min, max);
        return this.addValidation(numberRangeValidation);
    }

    public OKBasicTypeValidationBuilder regularExpression(String errMsg, Object input, String regex) throws OKValidationException {
        return this.regularExpression(errCode, errMsg, input, regex);
    }

    public OKBasicTypeValidationBuilder regularExpression(String errCode, String errMsg, Object input, String regex) throws OKValidationException {
        if (input instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
        RegularExpressionValidation regularExpressionValidation = new RegularExpressionValidation(null, errCode, errMsg, input, regex);
        return this.addValidation(regularExpressionValidation);
    }

    public OKBasicTypeValidationBuilder requireNumber(String errMsg, Object input) throws OKValidationException {
        return this.requireNumber(errCode, errMsg, input);
    }

    public OKBasicTypeValidationBuilder requireNumber(String errCode, String errMsg, Object input) throws OKValidationException {
        if (input instanceof Number || input instanceof CharSequence) {
            RequiredNumberValidation requiredNumberValidation = new RequiredNumberValidation(null, errCode, errMsg, input);
            return this.addValidation(requiredNumberValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder stringMinLen(String errMsg, Object input, int compareValue) throws OKValidationException {
        return this.stringMinLen(errCode, errMsg, input, compareValue);
    }

    public OKBasicTypeValidationBuilder stringMinLen(String errCode, String errMsg, Object input, int compareValue) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringMinLenValidation stringMinLenValidation = new StringMinLenValidation(null, errCode, errMsg, input, compareValue);
            return this.addValidation(stringMinLenValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder stringMaxLen(String errMsg, Object input, int compareValue) throws OKValidationException {
        return this.stringMaxLen(errCode, errMsg, input, compareValue);
    }

    public OKBasicTypeValidationBuilder stringMaxLen(String errCode, String errMsg, Object input, int compareValue) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringMaxLenValidation stringMinLenValidation = new StringMaxLenValidation(null, errCode, errMsg, input, compareValue);
            return this.addValidation(stringMinLenValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder stringRangeLen(String errMsg, Object input, int minLen, int maxLen) throws OKValidationException {
        return this.stringRangeLen(errCode, errMsg, input, minLen, maxLen);
    }

    public OKBasicTypeValidationBuilder stringRangeLen(String errCode, String errMsg, Object input, int minLen, int maxLen) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringRangeLenValidation stringRangeLenValidation = new StringRangeLenValidation(null, errCode, errMsg, input, minLen, maxLen);
            return this.addValidation(stringRangeLenValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    protected void checkSupport(Object input) throws OKValidationException {
        boolean isSupport = false;
        if (input instanceof Number) {
            isSupport = true;
        }
        if (input instanceof CharSequence) {
            isSupport = true;
        }
        if (input instanceof Boolean) {
            isSupport = true;
        }
        if (isSupport == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder addValidation(OKValidation okValidation) {
        validations.add(okValidation);
        return this;
    }

    public void validation() throws OKValidationException {
        if (validations != null && validations.size() > 0) {
            for (OKValidation validation : validations) {
                try {
                    validation.validation();
                } catch (OKValidationException e) {
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("UNKNOWN_ERROR").errMsg("未知错误 fieldName:");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("UNKNOWN_ERROR").errMsg("未知错误");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("UNKNOWN_ERROR").errMsg("未知错误");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("UNKNOWN_ERROR").errMsg("未知错误");
                }
            }
        }
    }
}
