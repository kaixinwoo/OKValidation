package org.ok.validation.demo;

import org.ok.validation.builder.object.OKObjectFieldWrapper;
import org.ok.validation.builder.object.OKObjectValidation;
import org.ok.validation.demo.entity.Customer;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.math.BigDecimal;

public class OKObjectValidationDemo {

    public static void main(String[] args) {
        OKObjectValidationDemo demo = new OKObjectValidationDemo();
        demo.demo();
    }

    public static final Customer customer = Customer.builder()
            .userId("1234567890")
            .username("王二狗")
            .password("00000000")
            .gender((byte)1)
            .age((short)100)
            .height(175)
            .weight(160)
            .email("123@123.com")
            .mobile("18600000000")
            .ip("127.0.0.1")
            .deposit(new BigDecimal("1875"))
            .computers(OKObjectUtil.toList("thinkpad", "macbook"))
            .build();

    public void demo() {
        try {
            OKObjectValidation.builder()
                    .input(customer)
                    .errCode("9999")
                    .errMsg("无效的参数")
                    .wrapper(OKObjectFieldWrapper.builder("password")
                            .notEmpty()
                            .stringRangeLen(6,12))
                    .validation();
            System.out.println("== 验证通过 ==");
        } catch (OKValidationException e) {
            System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }

    }
}
