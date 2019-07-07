package org.ok.validation.demo;

import org.ok.validation.OKBasicTypeValidationBuilder;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

public class OKBasicTypeValidationDemo {

    public static void main(String[] args) {
        OKBasicTypeValidationDemo demo = new OKBasicTypeValidationDemo();

        demo.errCodeDemo();
        demo.notNull();
        demo.equal();
        demo.numberLessThan();
        demo.numberGreaterThan();
        demo.numberRange();
        demo.stringRegex();
        demo.requireNumber();
        demo.stringMinLen();
        demo.stringMaxLen();
        demo.stringRangeLen();
        demo.email();
        demo.mobile();
        demo.ipv4();
    }

    // 错误码使用演示
    public void errCodeDemo() {
        String password = "123456";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    // 验证可以指定错误码，如果不指定使用全局错误码“9999”
                    .equal(password, "77777", "密码必须为123456", OKObjectUtil.toArray("123456"))
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void notNull() {
        String password = "123";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .notNull(password, "无效的密码")
                    .notEmpty(password, "密码不能为空")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void equal() {
        String password = "123456";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .notNull(password, "无效的密码")
                    .notEmpty(password, "密码不能为空")
                    .equal(password, "密码必须等于123456", OKObjectUtil.toArray("123456"))
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void numberLessThan() {
        long age = 18;
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .numberLessThan(age, "年龄不能小于18岁", 18)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void numberGreaterThan() {
        long age = 60;
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .numberGreaterThan(age, "年龄不能大于60岁", 60)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void numberRange() {
        long age = 18;
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .numberRange(age, "年龄需要在18-60之间", 18, 60)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringRegex() {
        String mobile = "12345678901";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .stringRegex(mobile, "无效的手机号", "^1[0-9]{10}$")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void requireNumber() {
        String mobile = "12345678901";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .requireNumber(mobile, "手机号必须为数字")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringMinLen() {
        String name = "王二狗";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .stringMinLen(name, "姓名至少要两个字符", 2)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringMaxLen() {
        String name = "王二狗啦";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .stringMaxLen(name, "姓名最多5个字符", 5)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringRangeLen() {
        String name = "王二狗啦";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .stringRangeLen(name, "姓名2-5个字符", 2,5)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }

    }

    public void email() {
        String email = "7325356@qq.com";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .email(email, "无效的邮箱")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void mobile() {
        String mobile = "18600000000";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .mobile(mobile, "无效的手机号")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void ipv4() {
        String ip = "127.0.0.1";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .ipv4(ip, "无效的ip地址")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }
}
