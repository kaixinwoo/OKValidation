package org.ok.validation.unit;

import org.ok.validation.OKValidation;
import org.ok.validation.exception.OKValidationException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public abstract class DefaultValidation<T> implements OKValidation, Serializable {

    /**
     * 错误码
     */
    protected String errCode;
    /**
     * 错误信息
     */
    protected String errMsg;
    /**
     * 要验证的对象 目前支持 byte、short、int、long、flat、double、string、boolean、map、collection
     */
    protected T input;
    /**
     * 验证字段可能为null
     * mayBeNull = false 要求验证的数据不能为null且必须符合验证规则
     * mayBeNull = true 要求验证验证数据可以为null，为null时验证通过，如果不为null必须符合验证规则
     */
    protected boolean mayBeNull = false;

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

    @Override
    public void validation() throws OKValidationException {
        if (mayBeNull) {
            if (this.input == null) {
                // 通过，输入数据允许有null值
            } else {
                // 验证，输入数据允许有null值，但数据不为null，需要符合验证规则
                doValidation(input);
            }
        } else {
            if (input == null) {
                // 失败，输入数据不允许有null值，输入数据为null
                validationFail();
            } else {
                doValidation(input);
            }
        }
    }

    /**
     * 规则验证
     */
    protected abstract void doValidation(T input) throws OKValidationException;

    /**
     * 获取输入值
     * @return 输入值
     */
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

    public void errCode(String errCode) {
        this.errCode = errCode;
    }

    public void errMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void mayBeNull(boolean bool) {
        this.mayBeNull = bool;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

}
