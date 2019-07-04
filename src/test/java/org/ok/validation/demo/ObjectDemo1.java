package org.ok.validation.demo;

import org.ok.validation.OKObjectValidationBuilder;
import org.ok.validation.demo.entity.Customer;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.util.OKObjectUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象字段验证
 */
public class ObjectDemo1 {

    public static void main(String[] args) {
        new ObjectDemo1().test();
    }

    public void test() {
//        Map<String, Customer> map = new HashMap<>();
//        map.put("张三", Customer.builder()
//                .userId("1234567891")
//                .username("张三")
//                .password("123456")
//                .gender((byte)1)
//                .age((short)18)
//                .height(170)
//                .weight(150)
//                .deposit(new BigDecimal("1500"))
//                .computers(OKObjectUtil.toList("thinkpad", "macbook"))
//                .build());
//        map.put("李四", Customer.builder()
//                .userId("1234567892")
//                .username("李四")
//                .password("123456")
//                .gender((byte)1)
//                .age((short)20)
//                .height(173)
//                .weight(155)
//                .deposit(new BigDecimal("2000"))
//                .computers(OKObjectUtil.toList("thinkpad", "macbook"))
//                .build());
        Customer customer = Customer.builder()
                .userId("1234567890")
                .username("王二狗")
                .password("123456")
                .gender((byte)1)
                .age((short)30)
                .height(175)
                .weight(160)
                .deposit(new BigDecimal("1875"))
                .computers(OKObjectUtil.toList("thinkpad", "macbook"))
//                .friends(map)
                .build();
        try {
            OKObjectValidationBuilder.builder()
                    .defaultErrCode("9999")
                    .input(customer)
//                    .stringRangeLen("password", "密码必须为8-20位", 8, 20)
                    .lessThan("age", "年龄最小需要30岁", 30)
                    .equal("gender", "性别可选参考值【0，1】", OKObjectUtil.toArray((byte)0, (byte)1))
                    .notEmpty("computers", "候选人必须有电脑")
//                    .notEmpty("friends", "候选人必须有朋友")
                    .stringMinLen("username", "名字必须为2个字以上", 2)
                    .regularExpression("password", "密码必须为6位数字", "^[0-9]{6}$")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }

}
