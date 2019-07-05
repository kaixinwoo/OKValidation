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
    }

    // 错误码使用演示
    public void errCodeDemo() {
        String password = "111111";
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
        long age = 16;
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
        long age = 61;
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
        long age = 71;
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
                    .stringRegex(mobile, "无效的手机号", "^1[0-9]{01}$")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void requireNumber() {
        String mobile = "1234567890a";
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
        String name = "王";
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
        String name = "王二狗啦啦啦";
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
        String name = "王二狗啦啦啦";
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

//    public void numberDemo() {
//        int age = 18;
//        try {
//            OKBasicTypeValidationBuilder.builder()
//                    .errCode("9999")
//                    .requireNumber("年龄必须为数字", age)
//                    .numberLessThan("18岁以下不要", age, 18)
//                    .numberGreaterThan("60岁以上不要", age, 60)
//                    .numberRange("只要25-70周岁的用户", age, 18 ,60)
//                    .equal("必须要18、19、20岁", age, OKObjectUtil.toArray(18, 19, 20))
//                    .validation();
//            System.out.println(" == 验证通过 == ");
//        } catch (OKValidationException e) {
//            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
//        }
//    }
//
//    public void stringDemo() {
//        String input = "1234567";
//        try {
//            OKBasicTypeValidationBuilder.builder()
//                    .errCode("9999")
//                    .stringMinLen("字符串长度不能小于5", input, 5)
//                    .stringMaxLen("字符串长度不能大于10", input, 10)
//                    .stringRangeLen("字符串长度必须在1-20之间", input, 1, 20)
//                    .stringRegex("必须为纯数字", input, "^[0-9]+$")
//                    .equal("密码必须为123456", input, OKObjectUtil.toArray("123456"))
//                    .validation();
//            System.out.println(" == 验证通过 == ");
//        } catch (OKValidationException e) {
//            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
//        }
//    }


}
