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
	<groupId>com.github.kaixinwoo</groupId>
	<artifactId>okvalidation</artifactId>
	<version>1.0.0</version>
<dependency>
```


```
// 错误码使用演示
public void errCodeDemo() {
    String password = "111111";
    try {
        OKBasicTypeValidationBuilder.builder()
                .errCode("9999")
                // 验证可以指定错误码，如果不指定使用全局错误码“9999”
                .equal("7777", "密码必须为123456", password, OKObjectUtil.toArray("123456"))
                .validation();
        System.out.println(" == 验证通过 == ");
    } catch (OKValidationException e) {
        System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
    }
}
```

```
基本数字类型验证，支持 byte short int long float double BigDecimal AtomicInteger AtomicLong
public void numberDemo() {
    int age = 18;
    try {
        OKBasicTypeValidationBuilder.builder()
                .errCode("9999")
                .requireNumber("年龄必须为数字", age)
                .numberLessThan("18岁以下不要", age, 18)
                .numberGreaterThan("60岁以上不要", age, 60)
                .numberRange("只要25-70周岁的用户", age, 18 ,60)
                .equal("必须要18、19、20岁", age, OKObjectUtil.toArray(18, 19, 20))
                .validation();
        System.out.println(" == 验证通过 == ");
    } catch (OKValidationException e) {
        System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
    }
}

字符串类型验证 
public void stringDemo() {
    String input = "1234567";
    try {
        OKBasicTypeValidationBuilder.builder()
                .errCode("9999")
                .stringMinLen("字符串长度不能小于5", input, 5)
                .stringMaxLen("字符串长度不能大于10", input, 10)
                .stringRangeLen("字符串长度必须在1-20之间", input, 1, 20)
                .stringRegex("必须为纯数字", input, "^[0-9]+$")
                .equal("密码必须为123456", input, OKObjectUtil.toArray("123456"))
                .validation();
        System.out.println(" == 验证通过 == ");
    } catch (OKValidationException e) {
        System.out.println("验证失败 code:" + e.getErrCode() + " msg:" + e.getErrMsg());
    }
}

```

``` 
对象类型验证
public void test1() {
    try {
        OKObjectValidationBuilder.builder()
                .errCode("9999")
                .input(customer)
                .lessThan("age", "年龄最小需要30岁", 30)
                .equal("gender", "性别可选参考值【0，1】", OKObjectUtil.toArray((byte)0, (byte)1))
                .notEmpty("computers", "候选人必须有电脑")
                .stringMinLen("username", "名字必须为2个字以上", 2)
                .stringRegex("password", "密码必须为6位数字", "^[0-9]{6}$")
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
                .errCode("9999")
                .notEmpty(null, "必须得有朋友")
                .validation();
        System.out.println(" == 验证通过 == ");
    } catch (OKValidationException e) {
        System.out.println("code:" + e.getErrCode() + " msg:" + e.getErrMsg());
    }
}
``` 
