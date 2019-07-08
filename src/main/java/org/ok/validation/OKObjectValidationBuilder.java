package org.ok.validation;

import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * 对象验证
 */
public class OKObjectValidationBuilder {

    // 通用错误码
    protected String errCode;
    // 输入值
    protected Object input;
    // 基本数据类型验证
    protected OKBasicTypeValidationBuilder basicTypeValidationBuilder = null;

    protected Object getValue(String fieldNme) throws OKValidationException {
        if (input == null) {
            throw new NullPointerException("无效的输入值[input] fieldName:" + fieldNme);
        }
        if (fieldNme == null || "".equals(fieldNme)) {
            throw OKValidationException.builder()
                    .errCode("ERR_INVALID_FIELD_NAME")
                    .errMsg("无效的FieldName");
        }
        try {
            Object value = OKObjectUtil.getValue(input, fieldNme);
            return value;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw OKValidationException.builder()
                    .errCode("ERR")
                    .errMsg("" + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw OKValidationException.builder()
                    .errCode("ERR")
                    .errMsg("" + e.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw OKValidationException.builder()
                    .errCode("ERR")
                    .errMsg("" + e.getMessage());
        }
    }

    protected Object getValueNotNull(String fieldNme, String errCode, String errMsg) throws OKValidationException {
        Object value = getValue(fieldNme);
        if (value == null) {
            throw OKValidationException.builder()
                    .errCode(errCode)
                    .errMsg(errMsg);
        }
        return value;
    }

    private OKObjectValidationBuilder() {
        basicTypeValidationBuilder = new OKBasicTypeValidationBuilder();
    }

    public static OKObjectValidationBuilder builder() {
        return new OKObjectValidationBuilder();
    }

    /**
     * 设置默认错误码
     * @param errCode 默认错误码
     * @return 当前对象
     */
    public OKObjectValidationBuilder errCode(String errCode) {
        basicTypeValidationBuilder.errCode(errCode);
        this.errCode = errCode;
        return this;
    }

    /**
     * 设置统一输入数据
     * @param input 输入对象
     * @return 当前对象
     * @throws OKValidationException 如果输入数据不支持会抛出异常
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
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 盐城失败时返回的错误信息 OKValidationException::msg 盐城失败时返回的错误信息 OKValidationException::msg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder notNull(String fieldName, String errMsg) throws OKValidationException {
        return notNull(fieldName, errCode, errMsg);
    }

    /**
     * 非null验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder notNull(String fieldName, String errCode, String errMsg) throws OKValidationException {
        Object value = getValue(fieldName);
        basicTypeValidationBuilder.notNull(value, errCode, errMsg);
        return this;
    }

    /**
     * 非空验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder notEmpty(String fieldName, String errMsg) throws OKValidationException {
        return notEmpty(fieldName, errCode, errMsg);
    }

    /**
     * 非空验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder notEmpty(String fieldName, String errCode, String errMsg) throws OKValidationException {
        Object value = getValue(fieldName);
        basicTypeValidationBuilder.notEmpty(value, errCode, errMsg);
        return this;
    }

    /**
     * 等值验证，如果是自定义类，要求类重写equals函数
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param equalValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder equal(String fieldName, String errMsg, Object[] equalValue) throws OKValidationException {
        return equal(fieldName, errCode, errMsg, equalValue);
    }

    /**
     * 等值验证，如果是自定义类，要求类重写equals函数
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param equalValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder equal(String fieldName, String errCode, String errMsg, Object[] equalValue) throws OKValidationException{
        Object value = getValue(fieldName);
        basicTypeValidationBuilder.equal(value, errCode, errMsg, equalValue);
        return this;
    }

    /**
     * 小于验证，fieldName &lt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder numberLessThan(String fieldName, String errMsg, Number compareValue) throws OKValidationException {
        return numberLessThan(fieldName, errCode, errMsg, compareValue);
    }

    /**
     * 小于验证，fieldName &lt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder numberLessThan(String fieldName, String errCode, String errMsg, Number compareValue) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof Number == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为Number类型的数据");
        }
        Number number = (Number) value;
        basicTypeValidationBuilder.numberLessThan(number, errCode, errMsg, compareValue);
        return this;
    }

    /**
     * 大于验证，fieldName &gt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder numberGreaterThan(String fieldName, String errMsg, Number compareValue) throws OKValidationException {
        return numberGreaterThan(fieldName, errCode, errMsg, compareValue);
    }

    /**
     * 大于验证，fieldName &gt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder numberGreaterThan(String fieldName, String errCode, String errMsg, Number compareValue) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof Number == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为Number类型的数据");
        }
        Number number = (Number) value;
        basicTypeValidationBuilder.numberGreaterThan(number, errCode, errMsg, compareValue);
        return this;
    }

    /**
     * 数字范围验证，min &lt; fieldName &lt; max，否则验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param min 最小值
     * @param max 最大值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder numberRange(String fieldName, String errMsg, Number min, Number max) throws OKValidationException {
        return numberRange(fieldName, errMsg, errMsg, min, max);
    }

    /**
     * 数字范围验证，min &lt; fieldName &lt; max，否则验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param min 最小值
     * @param max 最大值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder numberRange(String fieldName, String errCode, String errMsg, Number min, Number max) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof Number == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为Number类型的数据");
        }
        Number number = (Number) value;
        basicTypeValidationBuilder.numberRange(number, errCode, errMsg, min, max);
        return this;
    }

    /**
     * 正则表达式验证，要求指定数据符合输入的正则表达式，否则验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param regex 正则表达式
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringRegex(String fieldName, String errMsg, String regex) throws OKValidationException {
        return stringRegex(fieldName, errCode, errMsg, regex);
    }

    /**
     * 正则表达式验证，要求指定数据符合输入的正则表达式，否则验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param regex 正则表达式
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringRegex(String fieldName, String errCode, String errMsg, String regex) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.stringRegex(cs, errCode, errMsg, regex);
        return this;
    }

    /**
     * 数字验证，要求指定输入必须为纯数字，否则验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder requiredNumber(String fieldName, String errMsg) throws OKValidationException {
        return requiredNumber(fieldName, errCode, errMsg);
    }

    /**
     * 数字验证，要求指定输入必须为纯数字，否则验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder requiredNumber(String fieldName, String errCode, String errMsg) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence或Number类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.requireNumber(cs, errCode, errMsg);
        return this;
    }

    /**
     * 字符串长度验证，length(fieldName) &lt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringMinLen(String fieldName, String errMsg, int compareValue) throws OKValidationException {
        return stringMinLen(fieldName, errCode, errMsg, compareValue);
    }

    /**
     * 字符串长度验证，length(fieldName) &lt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringMinLen(String fieldName, String errCode, String errMsg, int compareValue) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence或Number类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.stringMinLen(cs, errCode, errMsg, compareValue);
        return this;
    }

    /**
     * 字符串长度验证，length(fieldName) &gt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringMaxLen(String fieldName, String errMsg, int compareValue) throws OKValidationException {
        return stringMaxLen(fieldName, errCode, errMsg, compareValue);
    }

    /**
     * 字符串长度验证，length(fieldName) &gt; compareValue 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param compareValue 比较值
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringMaxLen(String fieldName, String errCode, String errMsg, int compareValue) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence或Number类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.stringMaxLen(cs, errCode, errMsg, compareValue);
        return this;
    }

    /**
     * 字符串长度范围验证 length(fieldName) &lt; minLen 或 length(fieldName) &gt; maxLen  验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param minLen 字符串最小长度验证
     * @param maxLen 字符串最大长度验证
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringRangeLen(String fieldName, String errMsg, int minLen, int maxLen) throws OKValidationException {
        return stringRangeLen(fieldName, errCode, errMsg, minLen, maxLen);
    }

    /**
     * 字符串长度范围验证 length(fieldName) &lt; minLen 或 length(fieldName) &gt; maxLen 验证失败
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg 
     * @param minLen 字符串最小长度验证
     * @param maxLen 字符串最大长度验证
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder stringRangeLen(String fieldName, String errCode, String errMsg, int minLen, int maxLen) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence或Number类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.stringRangeLen(cs, errCode, errMsg, minLen, maxLen);
        return this;
    }

    /**
     * 邮箱验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder email(String fieldName, String errMsg) throws OKValidationException {
        return email(fieldName, errCode, errMsg);
    }

    /**
     * 邮箱验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder email(String fieldName, String errCode, String errMsg) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.email(cs, errCode, errMsg);
        return this;
    }

    /**
     * 手机号（中国）验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder mobile(String fieldName, String errMsg) throws OKValidationException {
        return this.mobile(fieldName, errCode, errMsg);
    }

    /**
     * 手机号（中国）验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder mobile(String fieldName, String errCode ,String errMsg) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.mobile(cs, errCode, errMsg);
        return this;
    }

    /**
     * ipv4地址验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder ipv4(String fieldName, String errMsg) throws OKValidationException {
        return this.ipv4(fieldName, errCode, errMsg);
    }

    /**
     * ipv4地址验证
     * @param fieldName 对戏实例变量名称，用于反射get函数获取输入值进行验证
     * @param errCode 验证失败时返回的错误信息 OKValidationException::errCode
     * @param errMsg 验证失败时返回的错误信息 OKValidationException::errMsg
     * @return 当前对象
     * @throws OKValidationException 输入不支持的数据类型时抛出异常
     */
    public OKObjectValidationBuilder ipv4(String fieldName, String errCode ,String errMsg) throws OKValidationException {
        Object value = getValueNotNull(fieldName, errCode, errMsg);
        if (value instanceof CharSequence == false) {
            throw OKValidationException.builder()
                    .errCode("ERR_UN_SUPPORT")
                    .errMsg("fieldName:" + fieldName + " 必须为CharSequence类型的数据");
        }
        CharSequence cs = (CharSequence) value;
        basicTypeValidationBuilder.ipv4(cs, errCode, errMsg);
        return this;
    }

    protected void builderFail(String errCode, String errMsg, Object errValue) throws OKValidationException {
        throw OKValidationException.builder()
                .errCode(errCode)
                .errMsg(errMsg)
                .errValue(errValue);
    }

    public void validation() throws OKValidationException {
        basicTypeValidationBuilder.validation();
    }

    public OKObjectValidationBuilder addValidation(OKValidation validation) {
        basicTypeValidationBuilder.addValidation(validation);
        return this;
    }
}
