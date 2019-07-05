package org.ok.validation.demo;

import org.ok.validation.OKBasicTypeValidationBuilder;
import org.ok.validation.exception.OKValidationException;

public class OKBasicTypeValidationDemo {

    public static void main(String[] args) {
        new OKBasicTypeValidationDemo().numberDemo();
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


}
