package org.ok.validation;

import org.ok.validation.exception.OKValidationException;
import org.ok.validation.unit.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 对象验证
 */
public class OKObjectValidationBuilder {

    protected List<OKValidation> validations = new LinkedList<>();
    // 默认错误码
    protected String errCode;
    // 输入值
    protected Object input;

    public static OKObjectValidationBuilder builder() {
        return new OKObjectValidationBuilder();
    }

    /**
     * 设置默认错误码
     * @param defaultErrCode
     * @return
     */
    public OKObjectValidationBuilder defaultErrCode(String defaultErrCode) {
        this.errCode = defaultErrCode;
        return this;
    }

    /**
     * 设置统一输入数据
     * @param input
     * @return
     * @throws OKValidationException
     */
    public OKObjectValidationBuilder input(Object input) throws OKValidationException {
        if (input == null) {
            throw new NullPointerException();
        }
        if (input instanceof Collection || input.getClass().isArray()) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持Collection或Array类型数据作为输入项");
        }
        this.input = input;
        return this;
    }

    /**
     * 非空null验证
     * @param fieldName
     * @param errMsg
     * @return
     */
    public OKObjectValidationBuilder notNull(String fieldName, String errMsg) {
        return notNull(fieldName, errCode, errMsg, input);
    }

    /**
     * 非null验证
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @return
     */
    public OKObjectValidationBuilder notNull(String fieldName, String errCode, String errMsg, Object input) {
        NotNullValidation notNullValidation = new NotNullValidation(fieldName, errCode, errMsg, input);
        addValidation(notNullValidation);
        return this;
    }

    /**
     * 非空验证
     * @param fieldName
     * @param errMsg
     * @return
     */
    public OKObjectValidationBuilder notEmpty(String fieldName, String errMsg) {
        return notEmpty(fieldName, errCode, errMsg, input);
    }

    /**
     * 非空验证
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @return
     */
    public OKObjectValidationBuilder notEmpty(String fieldName, String errCode, String errMsg, Object input) {
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation(fieldName, errCode, errMsg, input);
        addValidation(notEmptyValidation);
        return this;
    }

    /**
     * 等值验证，如果是自定义类，要求类重写equals函数
     * @param fieldName
     * @param errMsg
     * @param equalValue
     * @return
     */
    public OKObjectValidationBuilder equal(String fieldName, String errMsg, Object[] equalValue) {
        return equal(fieldName, errCode, errMsg, input, equalValue);
    }

    /**
     * 等值验证，如果是自定义类，要求类重写equals函数
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param equalValue
     * @return
     */
    public OKObjectValidationBuilder equal(String fieldName, String errCode, String errMsg, Object input, Object[] equalValue) {
        EqualValidation equalValidation = new EqualValidation(fieldName, errCode, errMsg, input, equalValue);
        addValidation(equalValidation);
        return this;
    }

    /**
     * 小于验证，fieldName < compareValue 验证失败
     * @param fieldName
     * @param errMsg
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder lessThan(String fieldName, String errMsg, Number compareValue) {
        return lessThan(fieldName, errCode, errMsg, input, compareValue);
    }

    /**
     * 小于验证，fieldName < compareValue 验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder lessThan(String fieldName, String errCode, String errMsg, Object input, Number compareValue) {
        NumberLessThanValidation lessThanValidation = new NumberLessThanValidation(fieldName, errCode, errMsg, input, compareValue);
        addValidation(lessThanValidation);
        return this;
    }

    /**
     * 大于验证，fieldName > compareValue 验证失败
     * @param fieldName
     * @param errMsg
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder greaterThan(String fieldName, String errMsg, Number compareValue) {
        return greaterThan(fieldName, errCode, errMsg, input, compareValue);
    }

    /**
     * 大于验证，fieldName > compareValue 验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder greaterThan(String fieldName, String errCode, String errMsg, Object input, Number compareValue) {
        NumberGreaterThanValidation greaterThanValidation = new NumberGreaterThanValidation(fieldName, errCode, errMsg, input, compareValue);
        addValidation(greaterThanValidation);
        return this;
    }

    /**
     * 数字范围验证，min < fieldName < max，否则验证失败
     * @param fieldName
     * @param errMsg
     * @param min
     * @param max
     * @return
     */
    public OKObjectValidationBuilder numberRange(String fieldName, String errMsg, Number min, Number max) {
        return numberRange(fieldName, errMsg, errMsg, input, min, max);
    }

    /**
     * 数字范围验证，min < fieldName < max，否则验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param min
     * @param max
     * @return
     */
    public OKObjectValidationBuilder numberRange(String fieldName, String errCode, String errMsg, Object input, Number min, Number max) {
        NumberRangeValidation rangeValidation = new NumberRangeValidation(fieldName, errCode, errMsg, input, min, max);
        addValidation(rangeValidation);
        return this;
    }

    /**
     * 正则表达式验证，要求指定数据符合输入的正则表达式，否则验证失败
     * @param fieldName
     * @param errMsg
     * @param regex
     * @return
     */
    public OKObjectValidationBuilder regularExpression(String fieldName, String errMsg, String regex) {
        return regularExpression(fieldName, errCode, errMsg, input, regex);
    }

    /**
     * 正则表达式验证，要求指定数据符合输入的正则表达式，否则验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param regex
     * @return
     */
    public OKObjectValidationBuilder regularExpression(String fieldName, String errCode, String errMsg, Object input, String regex) {
        RegularExpressionValidation regularExpressionValidation = new RegularExpressionValidation(fieldName, errCode, errMsg, input, regex);
        addValidation(regularExpressionValidation);
        return this;
    }

    /**
     * 数字验证，要求指定输入必须为纯数字，否则验证失败
     * @param fieldName
     * @param errMsg
     * @return
     */
    public OKObjectValidationBuilder requiredNumber(String fieldName, String errMsg) {
        return requiredNumber(fieldName, errCode, errMsg, input);
    }

    /**
     * 数字验证，要求指定输入必须为纯数字，否则验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @return
     */
    public OKObjectValidationBuilder requiredNumber(String fieldName, String errCode, String errMsg, Object input) {
        RequiredNumberValidation requiredNumberValidation = new RequiredNumberValidation(fieldName, errCode, errMsg, input);
        addValidation(requiredNumberValidation);
        return this;
    }

    /**
     * 字符串长度验证，length(fieldName) < compareValue 验证失败
     * @param fieldName
     * @param errMsg
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder stringMinLen(String fieldName, String errMsg, int compareValue) {
        return stringMinLen(fieldName, errCode, errMsg, input, compareValue);
    }

    /**
     * 字符串长度验证，length(fieldName) < compareValue 验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder stringMinLen(String fieldName, String errCode, String errMsg, Object input, int compareValue) {
        StringMinLenValidation stringMinLenValidation = new StringMinLenValidation(fieldName, errCode, errMsg, input, compareValue);
        addValidation(stringMinLenValidation);
        return this;
    }

    /**
     * 字符串长度验证，length(fieldName) > compareValue 验证失败
     * @param fieldName
     * @param errMsg
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder stringMaxLen(String fieldName, String errMsg, int compareValue) {
        return stringMaxLen(fieldName, errCode, errMsg, input, compareValue);
    }

    /**
     * 字符串长度验证，length(fieldName) > compareValue 验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param compareValue
     * @return
     */
    public OKObjectValidationBuilder stringMaxLen(String fieldName, String errCode, String errMsg, Object input, int compareValue) {
        StringMaxLenValidation stringMaxLenValidation = new StringMaxLenValidation(fieldName, errCode, errMsg, input, compareValue);
        addValidation(stringMaxLenValidation);
        return this;
    }

    /**
     * 字符串长度范围验证 length(fieldName) < minLen 或 length(fieldName) > maxLen  验证失败
     * @param fieldName
     * @param errMsg
     * @param minLen
     * @param maxLen
     * @return
     */
    public OKObjectValidationBuilder stringRangeLen(String fieldName, String errMsg, int minLen, int maxLen) {
        return stringRangeLen(fieldName, errCode, errMsg, input, minLen, maxLen);
    }

    /**
     * 字符串长度范围验证 length(fieldName) < minLen 或 length(fieldName) > maxLen 验证失败
     * @param fieldName
     * @param errCode
     * @param errMsg
     * @param input
     * @param minLen
     * @param maxLen
     * @return
     */
    public OKObjectValidationBuilder stringRangeLen(String fieldName, String errCode, String errMsg, Object input, int minLen, int maxLen) {
        StringRangeLenValidation stringRangeLenValidation = new StringRangeLenValidation(fieldName, errCode, errMsg, input, minLen, maxLen);
        addValidation(stringRangeLenValidation);
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

    public OKObjectValidationBuilder addValidation(OKValidation validation) {
        validations.add(validation);
        return this;
    }
}
