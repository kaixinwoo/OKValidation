package org.ok.validation.demo;

import org.ok.validation.OKBasicTypeValidationBuilder;
import org.ok.validation.exception.OKValidationException;

public class OKBasicTypeValidationDemo {

    public static void main(String[] args) {
        new OKBasicTypeValidationDemo().demo();
    }

    public void demo() {
        String username = "王二狗";
        int age = 25;
        String password = "123456";

        try {
            OKBasicTypeValidationBuilder.builder()
                    .errCode("9999")
                    .requireNumber("年龄必须为数字", age)
                    .stringMinLen("密码最小6位", password, 6)
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }


    }
}
