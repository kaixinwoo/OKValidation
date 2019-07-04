# OKValidation

```
简单验证框架
```
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


        /**
         * 基本功能
         */
        public void test1() {
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
                        .addValidation(new CustomValidation())
                        .validation();
                System.out.println(" == 验证通过 == ");
            } catch (OKValidationException e) {
                System.out.println("验证失败  code:" + e.getErrCode() + " msg:" + e.getErrMsg());
            }
        }
    
        /**
         * 基本数据类型验证
         */
        public void testBasicDataType() {
            String password = "123456";
            try {
                OKObjectValidationBuilder.builder()
                        .requiredNumber(null, "9999", "密码必须为数字", password)
                        .regularExpression(null, "9999", "密码必须为6位数字", password, "^[0-9]{6}$")
                        .validation();
                System.out.println(" == 验证通过 == ");
            } catch (OKValidationException e) {
                System.out.println("code:" + e.getErrCode() + " msg:" + e.getErrMsg());
            }
        }
    
        /**
         * Map类型验证
         */
        public void testCollection() {
            Map<String, String> friends = new HashMap<String, String>(){{
                put("张三", "张三");
                put("李四", "李四");
            }};
            try {
                OKObjectValidationBuilder.builder()
                        .input(friends)
                        .defaultErrCode("9999")
                        .notEmpty(null, "必须得有朋友")
                        .validation();
                System.out.println(" == 验证通过 == ");
            } catch (OKValidationException e) {
                System.out.println("code:" + e.getErrCode() + " msg:" + e.getErrMsg());
            }
        }
``` 
