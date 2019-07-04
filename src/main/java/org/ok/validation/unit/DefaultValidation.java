package org.ok.validation.unit;

import org.ok.validation.OKValidation;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import javax.xml.bind.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

public abstract class DefaultValidation implements OKValidation {

    // 错误码
    protected String errCode;
    // 错误信息
    protected String errMsg;
    // 字段名称，对象、map等类型有意义，基本数据类型此值无意义
    protected String fieldName;
    // 要验证的对象 目前支持 byte、short、int、long、flat、double、string、boolean、map、collection
    protected Object input;

    protected static final String EMPTY;
    public static final OKValidationException DATA_TYPE_ERR_EXCEPTION;
    static {
        EMPTY = "";
        DATA_TYPE_ERR_EXCEPTION = OKValidationException.builder()
                .errCode("ERR_DATA_TYPE")
                .errMsg("不支持的数据类型");
    }

    public DefaultValidation(String fieldName, String errCode, String errMsg, Object input) {
        if (input == null) {
            throw new NullPointerException("fieldName:" + fieldName + " input is null");
        }
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.fieldName = fieldName;
        this.input = input;
    }

    /**
     * 获取值
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    protected Object getValue() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return OKObjectUtil.getValue(input, fieldName);
    }

    /**
     * 非null验证
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws OKValidationException
     */
    protected Object notNull() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, OKValidationException {
        Object value = getValue();
        if (value == null) {
            validationFail();
        }
        return value;
    }

    /**
     * 非空验证
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws OKValidationException
     * @throws IllegalAccessException
     */
    protected Object notEmpty() throws InvocationTargetException, NoSuchMethodException, OKValidationException, IllegalAccessException {
        Object value = notNull();
        if (value instanceof CharSequence) {
            String s = (String) value;
            if (EMPTY.equals(s)) {
                validationFail();
            }
        } else if (value instanceof Collection) {
            Collection collection = (Collection) value;
            if (collection.isEmpty()) {
                validationFail();
            }
        } else if (value instanceof Map) {
            Map map = (Map) value;
            if (map.isEmpty()) {
                validationFail();
            }
        } else if (value.getClass().isArray()) {
            Object[] array = (Object[]) value;
            if (array.length == 0) {
                validationFail();
            }
        }
        return value;
    }

    protected void validationFail() throws OKValidationException {
        throw OKValidationException.builder()
                .errCode(errCode)
                .errMsg(errMsg);
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getInput() {
        return input;
    }
}
