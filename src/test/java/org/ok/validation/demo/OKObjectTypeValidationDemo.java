package org.ok.validation.demo;

import org.ok.validation.OKObjectValidationBuilder;
import org.ok.validation.demo.custom.CustomValidation;
import org.ok.validation.demo.entity.Customer;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.math.BigDecimal;

/**
 * 对象字段验证
 */
public class OKObjectTypeValidationDemo {

    public static final Customer customer = Customer.builder()
            .userId("1234567890")
            .username("王二狗")
            .password("1234567890111")
            .gender((byte)1)
            .age((short)100)
            .height(175)
            .weight(160)
            .email("123@123.com")
            .deposit(new BigDecimal("1875"))
            .computers(OKObjectUtil.toList("thinkpad", "macbook"))
            .build();

    public static void main(String[] args) {
        OKObjectTypeValidationDemo demo = new OKObjectTypeValidationDemo();

        demo.demo();
        demo.notNull();
        demo.notEmpty();
        demo.equal();
        demo.numberLessThan();
        demo.numberGreaterThan();
        demo.numberRange();
        demo.stringRegex();
        demo.requiredNumber();
        demo.stringMinLen();
        demo.stringMaxLen();
        demo.stringRangeLen();
        demo.custom();
        demo.email();
    }

    public void demo() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .notEmpty("friends", "没朋友可不行")
                    .notEmpty("username", "必需得起个名字")
                    .equal("age", "年龄必须在18-25岁", OKObjectUtil.toArray(18, 19, 20, 21, 22, 23, 24, 25))
                    .numberRange("height", "体重在50-80KG", 50, 80)
                    // ....
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    /**
     * 对象类型验证
     */
    public void notNull() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .notEmpty("friends", "没朋友可不行")
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void notEmpty() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .notEmpty("username", "必需得起个名字")
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void equal() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .equal("age", "年龄必须在18-25岁", OKObjectUtil.toArray(18, 19, 20, 21, 22, 23, 24, 25))
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void numberLessThan() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .numberLessThan("age", "年龄不得小于18岁", 18)
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void numberGreaterThan() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .numberGreaterThan("age", "年龄不得大于30岁", 30)
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void numberRange() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .numberRange("height", "体重在50-80KG", 50, 80)
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringRegex() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .stringRegex("userId", "UserId必须为数字", "^[0-9]{1,}$")
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void requiredNumber() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .requiredNumber("password", "密码必须为数字")
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringMinLen() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .stringMinLen("password", "密码长度不能小于6位", 6)
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringMaxLen() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .stringMaxLen("password", "密码长度不能大于10位", 10)
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringRangeLen() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .stringRangeLen("password", "密码长度6-10位", 6, 10)
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void custom() {
        CustomValidation customValidation = new CustomValidation(customer);
        try {
            OKObjectValidationBuilder.builder().addValidation(customValidation).validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }

    }

    public void email() {
        try {
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .email("email", "无效的邮箱")
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }
}
