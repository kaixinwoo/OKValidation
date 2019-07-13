package org.ok.validation.annotation;

import org.ok.validation.unit.EmailValidation;

import java.lang.annotation.*;

/**
 * 要求被验证的数据必须为邮件地址
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Email {

    String errCode() default "";

    String errMsg() default "";
}
