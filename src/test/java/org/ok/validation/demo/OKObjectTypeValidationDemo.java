package org.ok.validation.demo;

import org.ok.validation.OKObjectValidationBuilder;
import org.ok.validation.demo.custom.CustomValidation;
import org.ok.validation.demo.entity.Customer;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象字段验证
 */
public class OKObjectTypeValidationDemo {

    public static final Customer customer = Customer.builder()
            .userId("1234567890")
            .username("王二狗")
            .password("123456")
            .gender((byte)1)
            .age((short)30)
            .height(175)
            .weight(160)
            .deposit(new BigDecimal("1875"))
            .computers(OKObjectUtil.toList("thinkpad", "macbook"))
            .build();

    public static void main(String[] args) {
//        new ObjectDemo1().test1();
//        new ObjectDemo1().testCustomer();
//        new ObjectDemo1().testBasicDataType();
//        new OKObjectTypeValidationDemo().testCollection();
    }

    /**
     * 对象类型验证
     */
    public void test1() {
        try {
            OKObjectValidationBuilder.builder()
                    // 设置统一错误码
                    .errCode("9999")
                    // 设置输入对象
                    .input(customer)
                    // age未customer对象的属性，要求age必须有get函数才能获取值
                    .numberLessThan("age", "年龄最小需要30岁", 30)
                    // 数字类型比较时需要注意使用统一数据类型，如果不确定数据类型简易转成字符串在进行比较，例如a = 1(int) b = 2(byte) a.equals(b)会认为不相等
                    .equal("gender", "性别可选参考值【0，1】", OKObjectUtil.toArray(0, 1))
                    .notEmpty("computers", "候选人必须有电脑")
                    .stringMinLen("username", "名字必须为2个字以上", 2)
                    .stringRegex("password", "密码必须为6位数字", "^[0-9]{6}$")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    /**
     * 自定义验证规则
     */
    public void testCustomer() {
        try {
            Customer customer = Customer.builder()
                    .age((short) 79)
                    .build();
            OKObjectValidationBuilder.builder()
                    .input(customer)
                    .addValidation(new CustomValidation(customer))
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }




}
