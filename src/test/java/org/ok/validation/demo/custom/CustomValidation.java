package org.ok.validation.demo.custom;

import org.ok.validation.OKValidation;
import org.ok.validation.demo.entity.Customer;
import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

/**
 * 自定义验证规则
 */
public class CustomValidation implements OKValidation {

    Customer customer;
    public CustomValidation(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void validation() throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (customer.getAge() > 60) {
            throw OKValidationException.builder()
                    .errCode("9999")
                    .errMsg("年龄不能超过60岁");
        }
    }
}
