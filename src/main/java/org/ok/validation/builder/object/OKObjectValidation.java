package org.ok.validation.builder.object;


import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * 对象类型验证
 */
public class OKObjectValidation {

    /**
     * 输入对象，要验证的对象
     */
    protected Object input;
    /**
     * 全局错误码，如果单个字段未指定错误码的情况下使用全局错误码返回
     */
    protected String errCode;
    /**
     * 错误信息，如果单个字段未指定错误信息的情况下使用全局错误码返回
     */
    protected String errMsg;
    /**
     * 字段的包装信息
     */
    protected List<OKObjectFieldWrapper> wrappers;

    private OKObjectValidation() {
        wrappers = new LinkedList<>();
    }

    public static OKObjectValidation builder() {
        return new OKObjectValidation();
    }

    public OKObjectValidation input(Object input) {
        this.input = input;
        return this;
    }

    public OKObjectValidation errCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public OKObjectValidation errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public OKObjectValidation wrapper(String fieldName, String errMsg) throws OKValidationException {
        if (this.errCode == null) {
            throw new NullPointerException("无效的输入 先设置全局错误码(errCode)");
        }
        return this.wrapper(fieldName, errCode, errMsg);
    }

    public OKObjectValidation wrapper(String fieldName, String errCode, String errMsg) throws OKValidationException {
        OKObjectFieldWrapper wrapper = OKObjectFieldWrapper.builder(input, fieldName, errCode, errMsg);
        return this.wrapper(wrapper);
    }

    public OKObjectValidation wrapper(OKObjectFieldWrapper wrapper) throws OKValidationException {
        if (this.input == null) {
            throw new NullPointerException("无效的输入 请先设置全局输入(input)");
        }
        if (wrapper == null) {
            throw new NullPointerException();
        }
        if (wrapper.getErrCode() == null && this.errCode == null) {
            throw new NullPointerException("无效的输入 先设置全局错误码(errCode)");
        }
        if (wrapper.getFieldName() == null || "".equals(wrapper.getFieldName())) {
            throw new NullPointerException("无效的输入 先设置全局错误码(fieldName)");
        }
        if (wrapper.getErrCode() == null) {
            wrapper.setErrCode(this.errCode);
        }
        if (wrapper.getErrMsg() == null) {
            wrapper.setErrMsg(this.errMsg);
        }
        try {
            Object value = OKObjectUtil.getValue(input, wrapper.getFieldName());
            wrapper.setInput(value);
            wrappers.add(wrapper);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw OKValidationException.builder()
                    .errCode(errCode)
                    .errMsg(errMsg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw OKValidationException.builder()
                    .errCode(errCode)
                    .errMsg(errMsg);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw OKValidationException.builder()
                    .errCode(errCode)
                    .errMsg(errMsg);
        }
        return this;
    }

    public void validation() throws OKValidationException {
        if (wrappers != null && wrappers.size() > 0) {
            for (OKObjectFieldWrapper wrapper : wrappers) {
                wrapper.validation();
            }
        }
    }

}
