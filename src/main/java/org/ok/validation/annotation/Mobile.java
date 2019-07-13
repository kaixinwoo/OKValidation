package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 要求被验证的数据必须为手机号码（中国）
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mobile {

    String errCode() default "";

    String errMsg() default "";
}
