package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 非NULL验证
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {

    String errCode() default "";

    String errMsg() default "";
}
