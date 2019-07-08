package org.ok.validation.builder.object;

import org.ok.validation.OKValidation;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.unit.*;
import org.ok.validation.util.OKObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class OKObjectFieldWrapper {


    // 输入的对象
    protected Object input;
    // 字段名称，实例变量名称，要求实例变量必须有get函数
    protected String fieldName;
    // 错误码
    protected String errCode;
    // 错误信息
    protected String errMsg;
    // 验证规则列表
    protected List<OKValidation> validations = null;

    private OKObjectFieldWrapper(Object input, String fieldName, String errCode, String errMsg) {
        this.validations = new LinkedList<>();
        this.input = input;
        this.fieldName = fieldName;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static OKObjectFieldWrapper builder(String fieldName) {
        return new OKObjectFieldWrapper(null, fieldName, null, null);
    }

    public static OKObjectFieldWrapper builder(String fieldName, String errCode) {
        return new OKObjectFieldWrapper(null, fieldName, errCode, null);
    }

    public static OKObjectFieldWrapper builder(String fieldName, String errCode, String errMsg) {
        return new OKObjectFieldWrapper(null, fieldName, errCode, errMsg);
    }

    public static OKObjectFieldWrapper builder(Object input, String fieldName, String errCode, String errMsg) {
        return new OKObjectFieldWrapper(input, fieldName, errCode, errMsg);
    }

    protected Object getValue() throws OKValidationException {
        try {
            Object value = OKObjectUtil.getValue(input, fieldName);
            return value;
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
    }

    public OKObjectFieldWrapper notNull() {
        NotNullValidation notNullValidation = new NotNullValidation(null, errCode, errMsg);
        return addValidation(notNullValidation);
    }

    public OKObjectFieldWrapper notEmpty() {
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation(null, errCode, errMsg);
        return addValidation(notEmptyValidation);
    }

    public OKObjectFieldWrapper equal(Object[] compareValue) {
        EqualValidation equalValidation = new EqualValidation(null, errCode, errMsg, compareValue);
        return addValidation(equalValidation);
    }

    public OKObjectFieldWrapper email() {
        EmailValidation emailValidation = new EmailValidation(null, errCode, errMsg);
        return addValidation(emailValidation);
    }

    public OKObjectFieldWrapper ipv4() {
        IPv4Validation iPv4Validation = new IPv4Validation(null, errCode, errMsg);
        return addValidation(iPv4Validation);
    }

    public OKObjectFieldWrapper mobile() {
        MobileValidation mobileValidation = new MobileValidation(null, errCode, errMsg);
        return addValidation(mobileValidation);
    }

    public OKObjectFieldWrapper numberGreaterThan(Number compareValue) {
        NumberGreaterThanValidation numberGreaterThanValidation = new NumberGreaterThanValidation(null, errCode, errMsg, compareValue);
        return addValidation(numberGreaterThanValidation);
    }

    public OKObjectFieldWrapper numberLessThan(Number compareValue) {
        NumberLessThanValidation numberLessThanValidation = new NumberLessThanValidation(null, errCode, errMsg, compareValue);
        return addValidation(numberLessThanValidation);
    }

    public OKObjectFieldWrapper numberRange(Number min, Number max) {
        NumberRangeValidation numberRangeValidation = new NumberRangeValidation(null, errCode, errMsg, min, max);
        return addValidation(numberRangeValidation);
    }

    public OKObjectFieldWrapper requiredNumber() {
        RequiredNumberValidation requiredNumberValidation = new RequiredNumberValidation(null, errCode, errMsg);
        return addValidation(requiredNumberValidation);
    }

    public OKObjectFieldWrapper stringMaxLen(int compareValue) {
        StringMaxLenValidation stringMaxLenValidation = new StringMaxLenValidation(null, errCode, errMsg, compareValue);
        return addValidation(stringMaxLenValidation);
    }

    public OKObjectFieldWrapper stringMinLen(int compareValue) {
        StringMinLenValidation stringMinLenValidation = new StringMinLenValidation(null, errCode, errMsg, compareValue);
        return addValidation(stringMinLenValidation);
    }

    public OKObjectFieldWrapper stringRangeLen(int min, int max) {
        StringRangeLenValidation stringRangeLenValidation = new StringRangeLenValidation(null, errCode, errMsg, min, max);
        return addValidation(stringRangeLenValidation);
    }

    public OKObjectFieldWrapper stringRegex(String regex) {
        StringRegexValidation stringRegexValidation = new StringRegexValidation(null, errCode, errMsg, regex);
        return addValidation(stringRegexValidation);
    }

    public OKObjectFieldWrapper addValidation(OKValidation okValidation) {
         validations.add(okValidation);
         return this;
    }

    public List<OKValidation> getValidations() {
        return this.validations;
    }

    void validation() throws OKValidationException {
        if (validations != null && validations.size() > 0) {
            for (OKValidation okvalidation : validations) {
                okvalidation.validation();
            }
        }
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public Object getInput() {
        return input;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
