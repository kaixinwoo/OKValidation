package org.ok.validation.unit;

import org.ok.validation.OKValidation;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

public abstract class DefaultValidation<T> implements OKValidation {

    // 错误码
    protected String errCode;
    // 错误信息
    protected String errMsg;
    // 要验证的对象 目前支持 byte、short、int、long、flat、double、string、boolean、map、collection
    protected T input;

    public static final OKValidationException DATA_TYPE_ERR_EXCEPTION;
    static {
        DATA_TYPE_ERR_EXCEPTION = OKValidationException.builder()
                .errCode("ERR_DATA_TYPE")
                .errMsg("不支持的数据类型");
    }

    public DefaultValidation(T input, String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.input = input;
    }

    protected T getInput() {
        return this.input;
    }

    /**
     * 非null验证
     * @return 返回验证的输入
     * @throws OKValidationException 验证失败返回
     */
    protected T notNull() throws OKValidationException {
        T input = getInput();
        if (input == null) {
            validationFail();
        }
        return input;
    }

    /**
     * 非空验证
     * @return 返回验证值
     * @throws OKValidationException 验证失败返回
     */
    protected T notEmpty() throws OKValidationException {
        T input = notNull();
        if (input instanceof CharSequence) {
            CharSequence s = (CharSequence) input;
            if (s.length() == 0) {
                validationFail();
            }
        } else if (input instanceof Collection) {
            Collection collection = (Collection) input;
            if (collection.isEmpty()) {
                validationFail();
            }
        } else if (input instanceof Map) {
            Map map = (Map) input;
            if (map.isEmpty()) {
                validationFail();
            }
        } else if (input.getClass().isArray()) {
            Object[] array = (Object[]) input;
            if (array.length == 0) {
                validationFail();
            }
        }
        return input;
    }

    /**
     * 验证失败 throw OKValidationException
     * @throws OKValidationException 验证失败返回
     */
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

}
