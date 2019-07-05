package org.ok.validation;

import org.ok.validation.exception.OKValidationException;
import org.ok.validation.unit.*;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * 基本数据类型验证（数字：Number 字符串：CharSequence  Boolean）
 */
public class OKBasicTypeValidationBuilder {

    protected List<OKValidation> validations = new LinkedList<>();
    /**
     * 默认错误码
     */
    protected String errCode;

    public static OKBasicTypeValidationBuilder builder() {
        return new OKBasicTypeValidationBuilder();
    }

    /**
     * 设置通用错误码
     * @param errCode
     * @return
     */
    public OKBasicTypeValidationBuilder errCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    /**
     * 非null验证
     * @param input
     * @param errMsg
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder notNull(Object input, String errMsg) throws OKValidationException {
        return notNull(input, errCode, errMsg);
    }

    /**
     * 非null验证
     * @param input
     * @param errCode
     * @param errMsg
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder notNull( Object input, String errCode, String errMsg) throws OKValidationException {
        checkSupport(input);
        NotNullValidation notEmptyValidation = new NotNullValidation(input, errCode, errMsg);
        return addValidation(notEmptyValidation);
    }

    /**
     * 非空验证
     * @param input
     * @param errMsg
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder notEmpty(Object input, String errMsg) throws OKValidationException {
        return notEmpty(input, errCode, errMsg);
    }

    /**
     * 非空验证
     * @param input
     * @param errCode
     * @param errMsg
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder notEmpty(Object input, String errCode, String errMsg) throws OKValidationException {
        checkSupport(input);
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation(input, errCode, errMsg);
        return addValidation(notEmptyValidation);
    }

    /**
     * 等值验证
     * @param input
     * @param errMsg
     * @param equalValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder equal(Object input, String errMsg, Object[] equalValue) throws OKValidationException {
        return equal(input, errCode, errMsg, equalValue);
    }

    /**
     * 等值验证
     * @param input
     * @param errCode
     * @param errMsg
     * @param equalValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder equal(Object input, String errCode, String errMsg, Object[] equalValue) throws OKValidationException {
        checkSupport(input);
        EqualValidation equalValidation = new EqualValidation(input, errCode, errMsg, equalValue);
        return addValidation(equalValidation);
    }

    /**
     * Number类型 小于验证 input < compareValue 验证失败
     * @param input
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder numberLessThan(Number input, String errMsg, Number compareValue) throws OKValidationException {
        return numberLessThan(input, errCode, errMsg, compareValue);
    }

    /**
     * Number类型 小于验证 input < compareValue 验证失败
     * @param input
     * @param errCode
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder numberLessThan(Number input, String errCode, String errMsg, Number compareValue) throws OKValidationException {
        if (input instanceof Number) {
            NumberLessThanValidation numberLessThanValidation = new NumberLessThanValidation(input, errCode, errMsg, compareValue);
            return addValidation(numberLessThanValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * Number类型 大于验证 input > compareValue 验证失败
     * @param input
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder numberGreaterThan(Number input, String errMsg, Number compareValue) throws OKValidationException {
        return numberGreaterThan(input, errCode, errMsg, compareValue);
    }

    /**
     * Number类型 大于验证 input > compareValue 验证失败
     * @param input
     * @param errCode
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder numberGreaterThan(Number input, String errCode, String errMsg, Number compareValue) throws OKValidationException {
        if (input instanceof Number) {
            NumberGreaterThanValidation numberGreaterThanValidation = new NumberGreaterThanValidation(input, errCode, errMsg, compareValue);
            return addValidation(numberGreaterThanValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * Number类型 范围验证 input < min or input > max 验证失败
     * @param input
     * @param errMsg
     * @param min
     * @param max
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder numberRange(Number input, String errMsg, Number min, Number max) throws OKValidationException {
        return numberRange(input, errCode, errMsg, min, max);
    }

    /**
     * Number类型 范围验证 input < min or input > max 验证失败
     * @param input
     * @param errCode
     * @param errMsg
     * @param min
     * @param max
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder numberRange(Number input, String errCode, String errMsg, Number min, Number max) throws OKValidationException {
        if (input instanceof Number) {
            NumberRangeValidation numberRangeValidation = new NumberRangeValidation(input, errCode, errMsg, min, max);
            return this.addValidation(numberRangeValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * 正则表达式验证
     * @param input
     * @param errMsg
     * @param regex
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringRegex(CharSequence input, String errMsg, String regex) throws OKValidationException {
        return this.stringRegex(input, errCode, errMsg, regex);
    }

    /**
     * 正则表达式验证
     * @param input
     * @param errCode
     * @param errMsg
     * @param regex
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringRegex(CharSequence input, String errCode, String errMsg, String regex) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringRegexValidation stringRegexValidation = new StringRegexValidation(input, errCode, errMsg, regex);
            return this.addValidation(stringRegexValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * 必须为数字  input 匹配正则表达式 '^[0-9]+$'
     * @param input
     * @param errMsg
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder requireNumber(Object input, String errMsg) throws OKValidationException {
        return this.requireNumber(input, errCode, errMsg);
    }

    /**
     * 必须为数字  input 匹配正则表达式 '^[0-9]+$'
     * @param input
     * @param errCode
     * @param errMsg
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder requireNumber(Object input, String errCode, String errMsg) throws OKValidationException {
        if (input instanceof Number || input instanceof CharSequence) {
            RequiredNumberValidation requiredNumberValidation = new RequiredNumberValidation(input, errCode, errMsg);
            return this.addValidation(requiredNumberValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * 字符串长度验证 length(input) < compareValue 验证失败
     * @param input
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringMinLen(CharSequence input, String errMsg, int compareValue) throws OKValidationException {
        return this.stringMinLen(input, errCode, errMsg, compareValue);
    }

    /**
     * 字符串长度验证 length(input) < compareValue 验证失败
     * @param input
     * @param errCode
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringMinLen(CharSequence input, String errCode, String errMsg, int compareValue) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringMinLenValidation stringMinLenValidation = new StringMinLenValidation(input, errCode, errMsg, compareValue);
            return this.addValidation(stringMinLenValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * 字符串长度验证 length(input) > compareValue 验证失败
     * @param input
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringMaxLen(CharSequence input, String errMsg, int compareValue) throws OKValidationException {
        return this.stringMaxLen(input, errCode, errMsg, compareValue);
    }

    /**
     * 字符串长度验证 length(input) > compareValue 验证失败
     * @param input
     * @param errCode
     * @param errMsg
     * @param compareValue
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringMaxLen(CharSequence input, String errCode, String errMsg, int compareValue) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringMaxLenValidation stringMinLenValidation = new StringMaxLenValidation(input, errCode, errMsg, compareValue);
            return this.addValidation(stringMinLenValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    /**
     * 字符串长度范围验证 length(input) < min or length(input) > max 验证失败
     * @param input
     * @param errMsg
     * @param minLen
     * @param maxLen
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringRangeLen(CharSequence input, String errMsg, int minLen, int maxLen) throws OKValidationException {
        return this.stringRangeLen(input, errCode, errMsg, minLen, maxLen);
    }

    /**
     * 字符串长度范围验证 length(input) < min or length(input) > max 验证失败
     * @param input
     * @param errCode
     * @param errMsg
     * @param minLen
     * @param maxLen
     * @return
     * @throws OKValidationException
     */
    public OKBasicTypeValidationBuilder stringRangeLen(CharSequence input, String errCode, String errMsg, int minLen, int maxLen) throws OKValidationException {
        if (input instanceof CharSequence) {
            StringRangeLenValidation stringRangeLenValidation = new StringRangeLenValidation(input, errCode, errMsg, minLen, maxLen);
            return this.addValidation(stringRangeLenValidation);
        } else {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("不支持的数据类型 " + input.getClass());
        }
    }

    protected void checkSupport(Object input) throws OKValidationException {
        boolean isSupport = false;
        if (input == null) {
            isSupport = true;
        } else if (input instanceof Number) {
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

    /**
     * 验证函数
     * @throws OKValidationException 验证失败抛出异常
     */
    public void validation() throws OKValidationException {
        if (validations != null && validations.size() > 0) {
            for (OKValidation validation : validations) {
                try {
                    validation.validation();
                } catch (OKValidationException e) {
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw OKValidationException.builder().errCode("UNKNOWN_ERROR").errMsg("未知错误");
                }
            }
        }
    }
}
