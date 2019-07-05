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
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                // 验证可以指定错误码，如果不指定使用全局错误码“9999”
                                .equal(password, "77777", "密码必须为123456", OKObjectUtil.toArray("123456"))
                                .validation();
            // 非null 和 非空验证
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .notNull(password, "无效的密码")
                                .notEmpty(password, "密码不能为空")
                                .validation();
            // 小于验证
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .numberLessThan(age, "年龄不能小于18岁", 18)
                                .validation();
            // 大于验证                    
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .numberGreaterThan(age, "年龄不能大于60岁", 60)
                                .validation();
            // 数字范围验证                    
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .numberRange(age, "年龄需要在18-60之间", 18, 60)
                                .validation();   
            // 正则表达式                    
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .stringRegex(mobile, "无效的手机号", "^1[0-9]{01}$")
                                .validation();
            // 输入必须为数字                       
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .requireNumber(mobile, "手机号必须为数字")
                                .validation();  
            // 字符串最小长度验证
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .stringMinLen(name, "姓名至少要两个字符", 2)
                                .validation(); 
            // 字符串最大长度验证
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .stringMaxLen(name, "姓名最多5个字符", 5)
                                .validation();  
            // 字符串长度范围验证                    
            OKBasicTypeValidationBuilder.builder()
                                .errCode("9999")
                                .stringRangeLen(name, "姓名2-5个字符", 2,5)
                                .validation();                                                                                                                    
            
                                
```
```
            // 对象验证示例
            OKObjectValidationBuilder.builder()
                    .errCode("9999")
                    .input(customer)
                    .notEmpty("friends", "没朋友可不行")
                    .notEmpty("username", "必需得起个名字")
                    .equal("age", "年龄必须在18-25岁", OKObjectUtil.toArray(18, 19, 20, 21, 22, 23, 24, 25))
                    .numberRange("height", "体重在50-80KG", 50, 80)
                    // ....
                    .validation();
```
