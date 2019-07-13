package org.ok.validation.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ok.validation.annotation.NotEmpty;
import org.ok.validation.annotation.NotNull;
import org.ok.validation.annotation.Regex;
import org.ok.validation.annotation.RequireNumber;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractCustomer {
    // 姓名
    private String username;
    // 密码
    @NotEmpty(errCode = "7777", errMsg = "无效的密码")
    @RequireNumber(errCode = "7777", errMsg = "密码必须为数字")
    private String password;
    // 性别
    private int gender;
    // 年龄
    private int age;
    // 身高
    private double height;
    // 体重
    private float weight;
    // 存款
    private BigDecimal deposit;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // IP地址
    private String ip;
    // 有几台电脑
    List<String> computers;
    // 朋友
    Map<String, Customer> friends;
}
