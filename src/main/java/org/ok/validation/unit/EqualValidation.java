package org.ok.validation.unit;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public class EqualValidation extends DefaultValidation  {

    Object[] equalValue;

    public EqualValidation(String fieldName, String errCode, String errMsg, Object input, Object[] equalValue) {
        super(fieldName, errCode, errMsg, input);
        if (equalValue == null) {
            throw new NullPointerException("无效的比较数据 equalValue:null fieldName:" + fieldName);
        }
        this.equalValue = equalValue;
    }

    @Override
    public void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object value = super.notEmpty();
        boolean isEqual = false;
        for (Object item : equalValue) {
            if (item.getClass().equals(value.getClass()) == false) {
                throw OKValidationException.builder()
                        .errCode("ERR_DATA_TYPE")
                        .errMsg("数据类型不匹配 fieldName:" + super.getFieldName() + " 需要:" + value.getClass() + " 比较数据:" + item.getClass());
            }
            if (value.equals(item)) {
                isEqual = true;
                break;
            }
        }
        if (isEqual == false) {
            validationFail();
        }
    }
}
