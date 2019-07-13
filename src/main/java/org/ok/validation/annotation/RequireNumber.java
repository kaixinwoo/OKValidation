package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 要求被验证的数据必须为数字
 * Number类型验证通过
 * String类型要求必须为数字
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireNumber {

    String errCode() default "";

    String errMsg() default "";
}
