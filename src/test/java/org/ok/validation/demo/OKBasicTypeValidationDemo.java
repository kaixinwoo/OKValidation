package org.ok.validation.demo;

import org.ok.validation.OKBasicTypeValidationBuilder;
import org.ok.validation.exception.OKValidationException;

public class OKBasicTypeValidationDemo {

    public static void main(String[] args) {
//        new OKBasicTypeValidationDemo().numberDemo();
        new OKBasicTypeValidationDemo().stringDemo();
    }

    public void numberDemo() {
        int age = 17;
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .requireNumber("年龄必须为数字", age)
                    .numberLessThan("18岁以下不要", age, 18)
                    .numberGreaterThan("60岁以上不要", age, 60)
                    .numberRange("只要25-70周岁的用户", age, 18 ,60)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

    public void stringDemo() {
        String input = "123456789a";
        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .stringMinLen("字符串长度不能小于5", input, 5)
                    .stringMaxLen("字符串长度不能大于10", input, 10)
                    .stringRangeLen("字符串长度必须在1-20之间", input, 1, 20)
                    .stringRegularExpression("必须为纯数字", input, "^[0-9]+$")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }


}