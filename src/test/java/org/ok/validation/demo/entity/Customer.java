package org.ok.validation.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    // 用户ID
    private String userId;
    // 姓名
    private String username;
    // 密码
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
