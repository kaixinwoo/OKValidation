package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 非空验证
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty {

    String errCode() default "";

    String errMsg() default "";
}
