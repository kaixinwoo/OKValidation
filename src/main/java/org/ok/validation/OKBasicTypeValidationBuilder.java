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

    protected static final String NULL_FIELD_NAME = null;

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
        NotNullValidation notEmptyValidation = new NotNullValidation(NULL_FIELD_NAME, errCode, errMsg, input);
        return addValidation(notEmptyValidation);
    }

    public OKBasicTypeValidationBuilder notEmpty(String errMsg, Object input) throws OKValidationException {
        return notEmpty(errCode, errMsg, input);
    }

    public OKBasicTypeValidationBuilder notEmpty(String errCode, String errMsg, Object input) throws OKValidationException {
        checkSupport(input);
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation(NULL_FIELD_NAME, errCode, errMsg, input);
        return addValidation(notEmptyValidation);
    }

    public OKBasicTypeValidationBuilder equal(String errMsg, Object input, Object[] equalValue) throws OKValidationException {
        return equal(errCode, errMsg, input, equalValue);
    }

    public OKBasicTypeValidationBuilder equal(String errCode, String errMsg, Object input, Object[] equalValue) throws OKValidationException {
        checkSupport(input);
        EqualValidation equalValidation = new EqualValidation(NULL_FIELD_NAME, errCode, errMsg, input, equalValue);
        return addValidation(equalValidation);
    }

    public OKBasicTypeValidationBuilder numberLessThan(String errMsg, Object input, Number compareValue) throws OKValidationException {
        return numberLessThan(errCode, errMsg, input, compareValue);
    }

    public OKBasicTypeValidationBuilder numberLessThan(String errCode, String errMsg, Object input, Number compareValue) throws OKValidationException {
        if (input instanceof Number) {
            NumberLessThanValidation numberLessThanValidation = new NumberLessThanValidation(NULL_FIELD_NAME, errCode, errMsg, input, compareValue);
            return addValidation(numberLessThanValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder numberGreaterThan(String errMsg, Object input, Number compareValue) throws OKValidationException {
        return numberGreaterThan(errCode, errMsg, input, compareValue);
    }

    public OKBasicTypeValidationBuilder numberGreaterThan(String errCode, String errMsg, Object input, Number compareValue) throws OKValidationException {
        if (input instanceof Number) {
            NumberGreaterThanValidation numberGreaterThanValidation = new NumberGreaterThanValidation(NULL_FIELD_NAME, errCode, errMsg, input, compareValue);
            return addValidation(numberGreaterThanValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder numberRange(String errMsg, Object input, Number min, Number max) throws OKValidationException {
        return numberRange(errCode, errMsg, input, min, max);
    }

    public OKBasicTypeValidationBuilder numberRange(String errCode, String errMsg, Object input, Number min, Number max) throws OKValidationException {
        if (input instanceof Number) {
            NumberRangeValidation numberRangeValidation = new NumberRangeValidation(NULL_FIELD_NAME, errCode, errMsg, input, min, max);
            return this.addValidation(numberRangeValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder stringRegularExpression(String errMsg, Object input, String regex) throws OKValidationException {
        return this.stringRegularExpression(errCode, errMsg, input, regex);
    }

    public OKBasicTypeValidationBuilder stringRegularExpression(String errCode, String errMsg, Object input, String regex) throws OKValidationException {
        if (input instanceof CharSequence) {
            RegularExpressionValidation regularExpressionValidation = new RegularExpressionValidation(NULL_FIELD_NAME, errCode, errMsg, input, regex);
            return this.addValidation(regularExpressionValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    public OKBasicTypeValidationBuilder requireNumber(String errMsg, Object input) throws OKValidationException {
        return this.requireNumber(errCode, errMsg, input);
    }

    public OKBasicTypeValidationBuilder requireNumber(String errCode, String errMsg, Object input) throws OKValidationException {
        if (input instanceof Number || input instanceof CharSequence) {
            RequiredNumberValidation requiredNumberValidation = new RequiredNumberValidation(NULL_FIELD_NAME, errCode, errMsg, input);
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
            StringMinLenValidation stringMinLenValidation = new StringMinLenValidation(NULL_FIELD_NAME, errCode, errMsg, input, compareValue);
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
            StringMaxLenValidation stringMinLenValidation = new StringMaxLenValidation(NULL_FIELD_NAME, errCode, errMsg, input, compareValue);
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
            StringRangeLenValidation stringRangeLenValidation = new StringRangeLenValidation(NULL_FIELD_NAME, errCode, errMsg, input, minLen, maxLen);
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
        } else if (input instanceof CharSequence) {
            isSupport = true;
        } else if (input instanceof Boolean) {
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
