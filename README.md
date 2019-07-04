# OKValidation

简单验证框架
```
开发环境 jdk1.8 
依赖包：lombok 1.16.20
作者邮箱：7325356@qq.com 欢迎骚扰
maven:
<dependency>
	<groupId>org.okvalidation</groupId>
	<artifactId>okvalidation</artifactId>
	<version>1.0.0</version>
<dependency>
```
``` 

    public static void main(String[] args) {
        new ObjectDemo1().test();
    }

    public void test() {
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
                .build();
        try {
            OKObjectValidationBuilder.builder()
                    .defaultErrCode("9999")
                    .input(customer)
                    .lessThan("age", "年龄最小需要30岁", 30)
                    .equal("gender", "性别可选参考值【0，1】", OKObjectUtil.toArray((byte)0, (byte)1))
                    .notEmpty("computers", "候选人必须有电脑")
                    .stringMinLen("username", "名字必须为2个字以上", 2)
                    .regularExpression("password", "密码必须为6位数字", "^[0-9]{6}$")
                    .validation();
            System.out.println(" == 验证通过 == ");
        } catch (OKValidationException e) {
            System.out.println("code:" + e.getErrCode() + " msg:" + e.getErrMsg());
        }
    }
``` 
