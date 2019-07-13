package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 要求被验证的数据必须为IPV4地址
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ipv4 {

    String errCode() default "";

    String errMsg() default "";
}
